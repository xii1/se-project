package com.miu.se.query;

import com.miu.se.AbstractControllerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * @author duong
 */
public class QueryTest extends AbstractControllerTest {

    @Test
    public void testGetTopRatingProducts() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/products/topRatingProducts")
                .queryParam("year", "2021")
                .queryParam("limit", "1"))
                .andReturn();
        String resultString = result.getResponse().getContentAsString();
        Assertions.assertEquals("", resultString);
    }

    @Test
    public void testVipUsers() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/users/vipUsers")
                .queryParam("year", "2021")
                .queryParam("limit", "1"))
                .andReturn();
        String resultString = result.getResponse().getContentAsString();
        Assertions.assertEquals("", resultString);
    }

    @Test
    public void testGetTopSoldCategories() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/categories/topSoldCategories")
                .queryParam("year", "2021")
                .queryParam("limit", "1"))
                .andReturn();
        String resultString = result.getResponse().getContentAsString();
        Assertions.assertEquals("", resultString);
    }
}
