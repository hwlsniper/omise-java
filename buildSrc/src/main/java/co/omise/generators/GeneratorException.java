package co.omise.generators;

// Extends RuntimeException since we made this to use inside template context
// classes which are used by the Mustache engine, and not directly by us, thus
// there is no way to nicely add this as a checked exception.
public class GeneratorException extends RuntimeException {
    GeneratorException() {
        super();
    }

    GeneratorException(String message) {
        super(message);
    }

    GeneratorException(String message, Throwable cause) {
        super(message, cause);
    }
}
