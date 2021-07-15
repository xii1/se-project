package com.miu.se.shipping_address;

import com.miu.se.AbstractControllerTest;
import com.miu.se.common.entity.ShippingAddress;
import com.miu.se.common.interfacing.ShippingAddressService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author duong
 */
public class ShippingAddressServiceTest extends AbstractControllerTest {

    @Autowired
    ShippingAddressService shippingAddressService;

    @Test
    public void testGetShippingAddressById() {
        ShippingAddress address = shippingAddressService.getShippingAddressById(1L);
        Assertions.assertNotNull(address);
    }

    @Test
    public void testSave() {
        ShippingAddress address = new ShippingAddress();
        address.setId(3L);
        address.setCity("Fairfield");
        address.setState("Iowa");
        address.setStreet("1000 N, 4th street");
        address.setZipCode("52557");

        ShippingAddress savedAddress = shippingAddressService.save(address);
        Assertions.assertNotNull(savedAddress);
    }

    @Test
    public void getAll() {
        List<ShippingAddress> addresses = shippingAddressService.getAll();
        Assertions.assertEquals(2, addresses.size());
    }
}
