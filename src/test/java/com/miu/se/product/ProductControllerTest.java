package com.miu.se.product;

import com.miu.se.AbstractControllerTest;
import com.miu.se.common.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class ProductControllerTest extends AbstractControllerTest {

    private static final String URI = "/products";

    @Test
    public void testGetAll() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/products")).andReturn();
        Product[] products = objectMapper.readValue(result.getResponse().getContentAsString(), Product[].class);
        Assertions.assertTrue(products.length >= 2);
    }
}
