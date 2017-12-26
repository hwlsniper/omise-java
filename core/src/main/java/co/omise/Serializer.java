package co.omise;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Serializer provides serialization and de-serialization functionality
 * for converting most Omise API models to and from JSON.
 */
public final class Serializer {
    private static Serializer sharedInstance;

    static {
        sharedInstance = new Serializer();
    }

    public static Serializer sharedInstance() {
        return sharedInstance;
    }

    private Serializer() {
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }
}
