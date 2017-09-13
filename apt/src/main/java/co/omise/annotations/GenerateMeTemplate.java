package co.omise.annotations;

import javax.lang.model.element.Element;

public final class GenerateMeTemplate implements Template<GenerateMe> {
    @Override
    public Class<GenerateMe> annotationClass() {
        return GenerateMe.class;
    }

    @Override
    public String templateName() {
        return "co/omise/templates/GenerateMe.mustache";
    }

    @Override
    public String buildOutputFilename(Element element, GenerateMe annotation) {
        return "co.omise." + buildClassName(element, annotation);
    }

    @Override
    public Object buildScope(Element element, GenerateMe annotation) {
        return new Scope(element, annotation);
    }

    private class Scope {
        public final String name;
        public final String value;

        public Scope(Element element, GenerateMe annotation) {
            this.name = buildClassName(element, annotation);
            this.value = annotation.value();
        }
    }

    private String buildClassName(Element element, GenerateMe annotation) {
        return element.getSimpleName() + "And" + annotation.name();
    }
}
