package co.omise;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RequestTest extends OmiseTest {
    @Test
    void testCtor() {
        Request request = new Request(
                "POST",
                HttpUrl.parse("https://api.omise.co/charges"),
                ResponseBody.create(MediaType.parse("application/json; charset=utf-8"), "{\"object\":\"charge\"}")
        );

        assertEquals("POST", request.getMethod());
        assertNotNull(request.getUrl());
        assertEquals("https://api.omise.co/charges", request.getUrl().toString());
        assertNotNull(request.getPayload());
        assertEquals("application/json; charset=utf-8", request.getPayload().contentType().toString());
    }
}
