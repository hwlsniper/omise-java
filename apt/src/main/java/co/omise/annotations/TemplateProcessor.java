package co.omise.annotations;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.*;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

public class TemplateProcessor extends AbstractProcessor {
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> set = new HashSet<>();
        set.add(GenerateMe.class.getCanonicalName());
        return set;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        return processTemplate(new GenerateMeTemplate(), roundEnv);
    }

    private <T extends Annotation> boolean processTemplate(Template<T> template, RoundEnvironment roundEnv) {
        return roundEnv.getElementsAnnotatedWith(template.annotationClass())
                .stream()
                .filter((element) -> element.getKind() == ElementKind.CLASS)
                .map((element) -> processOneElement(template, this.processingEnv, element))
                .reduce((lhs, rhs) -> lhs && rhs)
                .orElse(false);
    }

    private <T extends Annotation> boolean processOneElement(Template<T> template, ProcessingEnvironment processEnv, Element element) {
        Filer filer = processEnv.getFiler();
        Messager messager = processEnv.getMessager();

        T annotation = element.getAnnotation(template.annotationClass());
        String filename = template.buildOutputFilename(element, annotation);
        Object scope = template.buildScope(element, annotation);

        try {
            JavaFileObject sourceFile = filer.createSourceFile(filename);
            try (OutputStream output = sourceFile.openOutputStream()) {
                renderTemplate(template, scope, output);
                output.flush();
                output.close();
            }

            return true;

        } catch (IOException e) {
            messager.printMessage(Diagnostic.Kind.ERROR, e.getMessage());
            return false;
        }
    }

    private void renderTemplate(Template template, Object context, OutputStream output) throws IOException {
        try (
                InputStream input = getClass().getClassLoader().getResourceAsStream(template.templateName());
                Reader reader = new InputStreamReader(input);
                Writer writer = new OutputStreamWriter(output)
        ) {
            MustacheFactory factory = new DefaultMustacheFactory();
            Mustache mustache = factory.compile(reader, template.templateName());
            reader.close();

            mustache.execute(writer, context);
            writer.flush();
            writer.close();
        }
    }
}
