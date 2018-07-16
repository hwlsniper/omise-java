package co.omise.requests;

import co.omise.models.OmiseException;
import co.omise.models.Refund;
import co.omise.models.ScopedList;
import co.omise.resources.RefundResource;
import co.omise.resources.ResourceTest;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RefundRequestTest extends RequestTest {
    public static final String CHARGE_ID = "chrg_test_4yq7duw15p9hdrjp8oq";
    public static final String REFUND_ID = "rfnd_test_4yqmv79ahghsiz23y3c";

    @Test
    public void testList() throws IOException, OmiseException {
        ScopedList<Refund> list = resource().list();
        assertRequested("GET", "/charges/" + CHARGE_ID + "/refunds", 200);

        assertEquals(1, list.getTotal());
        assertEquals(20, list.getLimit());

        Refund refund = list.getData().get(0);
        assertEquals(REFUND_ID, refund.getId());
        assertEquals(10000L, refund.getAmount());
        assertEquals("thb", refund.getCurrency());
    }

    @Test
    public void testGet() throws IOException, OmiseException {
        Request<Refund> request = new Refund.GetRequestBuilder(CHARGE_ID, REFUND_ID).build();

        Refund refund = getTestRequester().sendRequest(request, Refund.class);

        assertRequested("GET", "/charges/" + CHARGE_ID + "/refunds/" + REFUND_ID, 200);
        assertEquals(REFUND_ID, refund.getId());
        assertEquals(10000L, refund.getAmount());
        assertEquals("thb", refund.getCurrency());
        assertEquals("trxn_test_4yqmv79fzpy0gmz5mmq", refund.getTransaction());
    }

    @Test
    public void testCreate() throws IOException, OmiseException {
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("description", "DESCRIPTION");
        metadata.put("invoice_id", "inv_N1ayTWJ2FV");
        Request<Refund> request = new Refund.CreateRequestBuilder(CHARGE_ID).amount(10000L).metadata(metadata).build();

        Refund refund = getTestRequester().sendRequest(request, Refund.class);

        assertRequested("POST", "/charges/" + CHARGE_ID + "/refunds", 200);
        assertEquals(REFUND_ID, refund.getId());
        assertEquals(10000L, refund.getAmount());
        assertEquals("DESCRIPTION", refund.getMetadata().get("description"));
        assertEquals("inv_N1ayTWJ2FV", refund.getMetadata().get("invoice_id"));
    }

    private RefundResource resource() {
        return new RefundResource(testClient(), CHARGE_ID);
    }
}