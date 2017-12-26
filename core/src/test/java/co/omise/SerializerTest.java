package co.omise;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SerializerTest extends OmiseTest {
    @Test
    void testSharedInstance() {
        assertEquals(Serializer.sharedInstance(), Serializer.sharedInstance());
    }
}
