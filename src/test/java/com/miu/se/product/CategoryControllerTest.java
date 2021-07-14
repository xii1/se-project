package com.miu.se.product;

import com.miu.se.AbstractControllerTest;
import org.junit.jupiter.api.Disabled;

@Disabled
public class CategoryControllerTest extends AbstractControllerTest {
//    @BeforeAll
//    private void setup() throws Exception {
//        token = loginAdmin();
//    }
//
//    @Test
//    public void testCreateCategory() throws Exception {
//        Category category = new Category();
//        category.setName("Test Category");
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/categories")
//                .header(AUTHORIZATION, token)
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .content(asJsonString(category)))
//                .andReturn();
//        Category response = objectMapper.readValue(result.getResponse().getContentAsString(), Category.class);
//
//        Assertions.assertEquals(response.getName(), category.getName());
//        Assertions.assertNotNull(response.getId());
//    }
//
//    @Test
//    public void testCreateProduct() throws Exception {
//        Category category = new Category();
//        category.setName("Test Category");
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/categories")
//                .header(AUTHORIZATION, token)
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .content(asJsonString(category)))
//                .andReturn();
//        Category response = objectMapper.readValue(result.getResponse().getContentAsString(), Category.class);
//        Product p = new Product();
//        p.setName("123");
//        result = mockMvc.perform(MockMvcRequestBuilders.post("/categories/" + response.getId() + "/products").header(AUTHORIZATION, token).contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(p))).andReturn();
//        Product pResult = objectMapper.readValue(result.getResponse().getContentAsString(), Product.class);
//
//        Assertions.assertEquals(pResult.getName(), p.getName());
//        Assertions.assertNotNull(pResult.getId());
//    }
//
//    @Test
//    public void testGetCategories() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/categories"))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }
}
