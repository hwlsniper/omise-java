package co.omise.generators;

import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Path;

public class ModelGenerator extends Generator {
    @Override
    void generate(Path outputPath) throws IOException {
        MethodSpec helloMethod = MethodSpec.methodBuilder("hello")
                .addModifiers(Modifier.PUBLIC)
                .returns(String.class)
                .addCode(CodeBlock.builder()
                        .addStatement("return $S", "Hello World")
                        .build())
                .build();

        TypeSpec yeyClass = TypeSpec.classBuilder("Yey")
                .addAnnotation(buildGeneratedAnnotation())
                .addModifiers(Modifier.PUBLIC)
                .addMethod(helloMethod)
                .build();

        JavaFile javaFile = JavaFile.builder("co.omise.models", yeyClass)
                .indent("    ")
                .build();

        javaFile.writeTo(outputPath);
    }
}
