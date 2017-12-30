package co.omise.generators;

import com.squareup.javapoet.AnnotationSpec;
import v2.io.swagger.models.Swagger;
import v2.io.swagger.parser.SwaggerParser;

import javax.annotation.Generated;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.stream.Collectors;

abstract class Generator {
    private static final String SWAGGER_RESOURCE_PATH = "/co/omise/swagger.yml";

    abstract void generate(Path outputPath) throws IOException;

    AnnotationSpec buildGeneratedAnnotation() {
        return AnnotationSpec.builder(Generated.class)
                .addMember("value", "$S", this.getClass().getCanonicalName())
                .build();
    }

    String getSwaggerYml() throws IOException {
        try (InputStream inputStream = getClass().getResourceAsStream(SWAGGER_RESOURCE_PATH);
             InputStreamReader reader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            return bufferedReader
                    .lines()
                    .collect(Collectors.joining("\n"));
        }
    }

    Swagger parseSwagger() throws IOException {
        String yml = getSwaggerYml();
        return new SwaggerParser().parse(yml);
    }
}
