package co.omise.generators;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import io.swagger.v3.parser.core.models.SwaggerParseResult;

import java.io.*;
import java.nio.file.Path;
import java.util.Objects;
import java.util.stream.Collectors;

abstract class Generator {
    private static final String OAS_RESOURCE_PATH = "/co/omise/omise-api.yml";

    abstract void generate(Path outputPath) throws IOException;

    String loadResource(String name) throws IOException {
        try (InputStream inputStream = getClass().getResourceAsStream(name)) {
            Objects.requireNonNull(inputStream, "template " + name + " not found");

            try (InputStreamReader reader = new InputStreamReader(inputStream);
                 BufferedReader bufferedReader = new BufferedReader(reader)) {
                return bufferedReader
                        .lines()
                        .collect(Collectors.joining("\n"));
            }
        }
    }

    void renderTemplate(Writer writer, String name, Object context) throws IOException {
        String source = loadResource(name);
        MustacheFactory factory = new DefaultMustacheFactory();
        try (Reader reader = new StringReader(source)) {
            Mustache mustache = factory.compile(reader, name);
            Objects.requireNonNull(mustache, "mustache template for " + name + " not found");
            mustache.execute(writer, context);
            writer.flush();
        }
    }

    OpenAPI loadApiDefinitions() throws IOException {
        String sourceYml = loadResource(OAS_RESOURCE_PATH);

        OpenAPIV3Parser parser = new OpenAPIV3Parser();
        SwaggerParseResult result = parser.readContents(sourceYml, null, null);
        for (String message : result.getMessages()) {
            System.out.println(message);
        }

        return result.getOpenAPI();
    }
}
