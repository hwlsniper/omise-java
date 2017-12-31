package co.omise.generators;

import io.swagger.v3.oas.models.media.Schema;

class AttributeContext extends TemplateContext {
    private final String name;
    private final Schema schema;

    AttributeContext(String name, Schema schema) {
        this.name = name;
        this.schema = schema;
    }

    public String name() {
        return name;
    }

    public String getterName() {
        return classifyString(name);
    }

    public String setterName() {
        return classifyString(name);
    }

    public String javaType() throws GeneratorException {
        return mapJavaType(schema.getType(), schema.getFormat());
    }
}
