package com.miu.se.common.request;

import com.miu.se.common.entity.ShippingAddress;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author duong
 */
public class ProcessPaymentRequestTest {
    @Test
    void testValues() {
        ProcessPaymentRequest request = new ProcessPaymentRequest();
        request.setUserId((long)1234);
        ShippingAddress address = new ShippingAddress();
        address.setZipCode("1234");
        address.setStreet("1000N, 4th street");
        address.setId((long)1234);
        address.setCity("Fairfield");
        address.setState("Iowa");
        request.setShippingAddress(address);

        Assertions.assertEquals(request.getUserId().longValue(), (long)1234);
        Assertions.assertNotNull(request.getShippingAddress());
        Assertions.assertEquals(request.getShippingAddress().getCity(), address.getCity());
        Assertions.assertEquals(request.getShippingAddress().getId(), address.getId());
        Assertions.assertEquals(request.getShippingAddress().getState(), address.getState());
        Assertions.assertEquals(request.getShippingAddress().getZipCode(), address.getZipCode());
        Assertions.assertEquals(request.getShippingAddress().getStreet(), address.getStreet());
    }
}
