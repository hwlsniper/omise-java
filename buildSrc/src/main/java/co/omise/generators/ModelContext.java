package co.omise.generators;

import io.swagger.v3.oas.models.media.Schema;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class ModelContext extends TemplateContext {
    private final String name;
    private final Schema schema;

    ModelContext(String name, Schema schema) {
        this.name = name;
        this.schema = schema;
    }

    public String name() {
        return name;
    }

    public List<AttributeContext> attributes() {
        // the entrySet() line should returns Set<Entry<String, Schema>> but
        // we get just Set<Entry> instead (swagger bug?) thus unchecked.
        @SuppressWarnings("unchecked") Set<Map.Entry<String, Schema>> entries = schema.getProperties().entrySet();
        return entries.stream()
                .map((entry) -> new AttributeContext(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
