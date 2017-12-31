package co.omise.generators;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.Schema;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class ModelGenerator extends Generator {
    private static final String MODEL_PACKAGE_PATH = "co/omise/models/";
    private static final String MODEL_TEMPLATE_PATH = "/co/omise/model.mustache";

    @Override
    void generate(Path outputPath) throws IOException {
        OpenAPI api = loadApiDefinitions();
        Objects.requireNonNull(api, "failed to OpenAPI file");

        Objects.requireNonNull(api.getComponents(), "OpenAPI file has no components section");
        Objects.requireNonNull(api.getComponents().getSchemas(), "OpenAPI file contains no schema components");

        Set<Map.Entry<String, io.swagger.v3.oas.models.media.Schema>> entries = api.getComponents().getSchemas().entrySet();
        for (Map.Entry<String, Schema> entry : entries) {
            Path modelPath = resolveModelPath(outputPath, entry.getKey());
            generateModel(modelPath, entry.getKey(), entry.getValue());
        }
    }

    private Path resolveModelPath(Path basePath, String modelName) {
        return basePath.resolve(MODEL_PACKAGE_PATH).resolve(modelName + ".java");
    }

    private void generateModel(Path path, String name, Schema schema) throws IOException {
        Objects.requireNonNull(path, "invalid model output path");
        Objects.requireNonNull(name, "no model name given in definition");
        Objects.requireNonNull(schema, "no model definition given for " + name);

        try (Writer writer = Files.newBufferedWriter(path)) {
            renderTemplate(writer, MODEL_TEMPLATE_PATH, new ModelContext(name, schema));
        }
    }

}
