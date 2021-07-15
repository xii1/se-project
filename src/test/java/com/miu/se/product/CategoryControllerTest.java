package com.miu.se.product;

import com.miu.se.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class CategoryControllerTest extends AbstractControllerTest {

//    @Test
//    public void testCreateCategory() throws Exception {
//        PostCategoryDTO category = new PostCategoryDTO();
//        category.setName("Test Category");
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/categories")
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .content(objectMapper.writeValueAsString(category)))
//                .andReturn();
//        Category response = objectMapper.readValue(result.getResponse().getContentAsString(), Category.class);
//
//        Assertions.assertEquals(response.getName(), category.getName());
//        Assertions.assertNotNull(response.getId());
//    }

    @Test
    public void testGetAllCategories() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/categories"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
