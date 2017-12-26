package co.omise;

import okhttp3.HttpUrl;
import okhttp3.ResponseBody;

/**
 * Request is a plain object to encapsulate all information needed to make an
 * Omise API request.
 */
public final class Request {
    private final String method;
    private final HttpUrl url;
    private final ResponseBody payload;

    public Request(String method, HttpUrl url, ResponseBody payload) {
        this.method = method;
        this.url = url;
        this.payload = payload;
    }

    public String getMethod() {
        return method;
    }

    public HttpUrl getUrl() {
        return url;
    }

    public ResponseBody getPayload() {
        return payload;
    }
}
