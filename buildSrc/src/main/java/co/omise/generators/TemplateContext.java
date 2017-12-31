package co.omise.generators;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;

import java.time.ZonedDateTime;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

abstract class TemplateContext {
    String classifyString(String name) {
        Iterable<String> words = Splitter
                .on(CharMatcher.forPredicate(Character::isUpperCase)
                        .and(CharMatcher.is('_')))
                .omitEmptyStrings()
                .split(name);

        return StreamSupport.stream(words.spliterator(), false)
                .map((word) -> word.charAt(0) + word.substring(1))
                .collect(Collectors.joining());
    }

    String mapJavaType(String type, String format) {
        return mapJavaClass(type, format)
                .getCanonicalName();
    }

    private Class mapJavaClass(String type, String format) {
        switch (type) {
            case "integer":
                return mapIntegerFormat(format);
            case "string":
                return mapStringFormat(format);
            default:
                throw new GeneratorException("unsupported object type: " + type);
        }
    }

    private Class mapIntegerFormat(String format) {
        if (format == null) return Integer.class;

        switch (format) {
            case "int64":
                return Long.class;
            default:
                throw new GeneratorException("unsupported integer format: " + format);
        }
    }

    private Class mapStringFormat(String format) {
        if (format == null) return String.class;

        switch (format) {
            case "date-time":
                return ZonedDateTime.class;
            case "email":
                return String.class; // TODO: special markers?
            default:
                throw new Error("unsupported string format: " + format);
        }
    }
}
