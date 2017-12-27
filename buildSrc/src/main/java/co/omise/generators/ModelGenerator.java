package co.omise.generators;

import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.nio.file.Paths;

public class ModelGenerator extends Generator {
    @Override
    void generate(String rootPath) throws IOException {
        MethodSpec helloMethod = MethodSpec.methodBuilder("hello")
                .returns(String.class)
                .addCode(CodeBlock.builder()
                        .addStatement("return $S;", "Hello World")
                        .build())
                .build();

        TypeSpec yeyClass = TypeSpec.classBuilder("Yey")
                .addMethod(helloMethod)
                .build();

        JavaFile javaFile = JavaFile.builder("co.omise.models", yeyClass)
                .build();

        javaFile.writeTo(Paths.get(rootPath));
    }
}
