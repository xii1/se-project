package com.miu.se.product;

import com.miu.se.AbstractControllerTest;
import org.junit.jupiter.api.Disabled;

@Disabled
public class ProductControllerTest extends AbstractControllerTest {
//    private final String URI = "/products";
//    private Product product;
//
//    @BeforeAll
//    private void setUp() throws Exception {
//        token = loginAdmin();
//        Category category = new Category();
//        category.setName("Test Category");
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/categories")
//                .header(AUTHORIZATION, token)
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .content(asJsonString(category)))
//                .andReturn();
//        category = objectMapper.readValue(result.getResponse().getContentAsString(), Category.class);
//        Product p = new Product();
//        p.setName("123");
//        result = mockMvc.perform(MockMvcRequestBuilders.post("/categories/" + category.getId() + "/products").header(AUTHORIZATION, token).contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(p))).andReturn();
//        product = objectMapper.readValue(result.getResponse().getContentAsString(), Product.class);
//    }
//
//    @Test
//    public void testGetAll() throws Exception {
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/products").header(AUTHORIZATION, token)).andReturn();
//        Product[] products = objectMapper.readValue(result.getResponse().getContentAsString(), Product[].class);
//        Assertions.assertTrue(products.length >= 2);
//    }
//
//    @Test
//    public void testCreateReview() throws Exception {
//        Review review = new Review();
//        review.setComment("test test");
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/products/" + product.getId() + "/reviews").contentType(MediaType.APPLICATION_JSON_VALUE).header(AUTHORIZATION, token).content(asJsonString(review))).andReturn();
//        Review resultReview = objectMapper.readValue(result.getResponse().getContentAsString(), Review.class);
//        Assertions.assertEquals(review.getComment(), resultReview.getComment());
//        Assertions.assertNotNull(resultReview.getId());
//    }

//    @Test
//    public void testGetTopRatingProducts() throws Exception {
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/products/topRatingProducts")
//                .header(AUTHORIZATION, token)
//                .queryParam("year", "2021")
//                .queryParam("limit", "1"))
//                .andReturn();
//        String resultString = result.getResponse().getContentAsString();
//        Assertions.assertEquals(resultString, "[{\"productName\":\"Men Shirt 2\",\"rating\":5.0}]");
//    }
//
//    @Test
//    public void testCreateReview() throws Exception {
//        Review review = new Review();
//        review.setComment("test test");
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/products/" + product.getId() + "/reviews").contentType(MediaType.APPLICATION_JSON_VALUE).header(AUTHORIZATION, token).content(asJsonString(review))).andReturn();
//        Review resultReview = objectMapper.readValue(result.getResponse().getContentAsString(), Review.class);
//        Assertions.assertEquals(review.getComment(), resultReview.getComment());
//        Assertions.assertNotNull(resultReview.getId());
//    }
//
//
//    void changePWD() throws Exception {
//        String newPassword = "11111111111";
//        mockMvc.perform(MockMvcRequestBuilders.patch(URI + "/changePWD/1").content("11111111111").header(AUTHORIZATION,token)).andReturn();
//        final MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URI + "/1").header(AUTHORIZATION, token)).andReturn();
//        User user = objectMapper.readValue(result.getResponse().getContentAsString(), User.class);
//        Assertions.assertEquals(user.getPassword(), "ADBC91A43E988A3B5B745B8529A90B61");
//    }
//
//    @Test
//    public void getProductsHavingTheWorstReviewTest() throws Exception {
//        ProductsHavingWorstReviewRequest request = new ProductsHavingWorstReviewRequest(2020,1);
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/products/getProductsHavingTheWorstReview?year=2020&limit=1")
//                .header(AUTHORIZATION, token))
//                .andReturn();
//
//        Product[] products = objectMapper.readValue(result.getResponse().getContentAsString(), Product[].class);
//
//        List<String> expect = Arrays.asList("Boiler");
//
//        Assertions.assertEquals(Arrays.stream(products).map(p->p.getName()).collect(Collectors.toList()), expect);
//    }
//
//    @Test
//    public void getUsersHavingLowestRatingGivenProductId() throws Exception {
//        //UsersHavingLowestRatingGivenProductIdRequest request = new UsersHavingLowestRatingGivenProductIdRequest(201L,1);
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/products/getUsersHavingLowestRatingGivenProductId?productId=201&k=1")
//                .header(AUTHORIZATION, token))
//                .andReturn();
//
//        Product[] products = objectMapper.readValue(result.getResponse().getContentAsString(), Product[].class);
//
//        List<String> expect = Arrays.asList("admin@gmail.com");
//
//        Assertions.assertEquals(Arrays.stream(products).map(p->p.getName()).collect(Collectors.toList()), expect);
//    }
}
