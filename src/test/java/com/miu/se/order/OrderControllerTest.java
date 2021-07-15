package com.miu.se.order;

import com.miu.se.AbstractControllerTest;
import com.miu.se.common.entity.Order;
import com.miu.se.common.entity.OrderStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

public class OrderControllerTest extends AbstractControllerTest {
    private static final String URI = "/orders";

    @Test
    void getAllOrders() throws Exception {
        final MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URI)).andReturn();
        final Order[] orders = objectMapper.readValue(result.getResponse().getContentAsString(), Order[].class);
        Assertions.assertEquals(3, orders.length);
    }

    @Test
    void getOrderById() throws Exception {
        final Order expected = new Order();
        expected.setId(1L);
        expected.setCountedPoint(true);
        expected.setCreatedAt(null);
        expected.setNote("test1");
        //expected.setPaymentGateway(null);
        expected.setStatus(OrderStatus.PROCESSING);
        // expected.setTransactionId("1234");
        expected.setUpdatedAt(null);
        expected.setItems(new ArrayList<>());

        final MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URI + "/1")).andReturn();
        JSONAssert.assertEquals(objectMapper.writeValueAsString(expected), result.getResponse().getContentAsString(), false);
    }
}
