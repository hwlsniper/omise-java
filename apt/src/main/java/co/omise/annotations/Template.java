package co.omise.annotations;

import javax.lang.model.element.Element;
import java.lang.annotation.Annotation;

public interface Template<T extends Annotation> {
    Class<T> annotationClass();
    String templateName();

    String buildOutputFilename(Element element, T annotation);
    Object buildScope(Element element, T annotation);
}
