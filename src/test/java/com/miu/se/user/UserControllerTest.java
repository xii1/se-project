package com.miu.se.user;

import com.miu.se.AbstractControllerTest;
import org.junit.jupiter.api.Disabled;


/**
 * @author XIII
 */
@Disabled
public class UserControllerTest extends AbstractControllerTest {
//    private static final String URI = "/users";
//
//    @BeforeAll
//    private void setUp() throws Exception {
//        token = loginAdmin();
//    }
//
//    @Test
//    void getAllUsers() throws Exception {
//        final MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URI).header(AUTHORIZATION, token)).andReturn();
//        final User[] users = objectMapper.readValue(result.getResponse().getContentAsString(), User[].class);
//        Assertions.assertTrue(users.length >= 5);
//    }
//
//    @Test
//    void getUserById() throws Exception {
//        final User expected = new User();
//        expected.setId(2L);
//        expected.setEmail("test2@gmail.com");
//        expected.setPassword("pass2");
//        expected.setStatus(UserStatus.INACTIVE);
//        expected.setAddresses(new ArrayList<>());
//        expected.setRoles(new HashSet<>());
//        expected.setCartItems(new ArrayList<>());
//
//        final MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URI + "/2").header(AUTHORIZATION, token)).andReturn();
//        JSONAssert.assertEquals(objectMapper.writeValueAsString(expected), result.getResponse().getContentAsString(), true);
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
//    void editProfile() throws Exception {
//        //Edit Profile to userId = 4
//        User user = new User();
//        user.setNickname("Teddyzzz");
//        user.setBio("Wanderlustzzz");
//        user.setDob("31/10/1991zzz");
//        user.setGender("Malezzz");
//        user.setPhoneNumber("27364782364zzz");
//
//        mockMvc.perform(MockMvcRequestBuilders.patch(URI + "/editProfile/4")
//                .content(asJsonString(user))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON).header(AUTHORIZATION, token)).andReturn();
//
//        //call to compare
//        final MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URI + "/4").header(AUTHORIZATION, token)).andReturn();
//        User resultUser = objectMapper.readValue(result.getResponse().getContentAsString(), User.class);
//
//        Assertions.assertEquals(resultUser.getId(), 4);
//        Assertions.assertEquals(resultUser.getNickname(), user.getNickname());
//        Assertions.assertEquals(resultUser.getBio(), user.getBio());
//        Assertions.assertEquals(resultUser.getDob(), user.getDob());
//        Assertions.assertEquals(resultUser.getGender(), user.getGender());
//        Assertions.assertEquals(resultUser.getPhoneNumber(), user.getPhoneNumber());
//    }
//
//    @Test
//    void editShippingAddr() throws Exception {
//        //Edit shipping_address to userId = 4
//        ShippingAddress shippingAddr = new ShippingAddress();
//        shippingAddr.setCity("Chicago");
//        shippingAddr.setState("IL");
//        shippingAddr.setStreet("5th North");
//        shippingAddr.setZipCode("12345");
//
//        mockMvc.perform(MockMvcRequestBuilders.patch(URI + "/4/editShippingAddr/1")
//                .content(asJsonString(shippingAddr))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON).header(AUTHORIZATION, token)).andReturn();
//
//        //expected shipping_address of userId = 4 after editing
//        final ShippingAddress expected = new ShippingAddress();
//        expected.setId(1L);
//        expected.setCity("Chicago");
//        expected.setState("IL");
//        expected.setStreet("5th North");
//        expected.setZipCode("12345");
//
//        //call to compare  /{shippingAddrId}
//        final MvcResult result2 = mockMvc.perform(MockMvcRequestBuilders.get(URI + "/getShippingAddrById/1").header(AUTHORIZATION, token)).andReturn();
//
//        //AssertEqual
//        JSONAssert.assertEquals(objectMapper.writeValueAsString(expected), result2.getResponse().getContentAsString(), false);
//    }
//
//    @Test
//    void getShippingAddrById() throws Exception {
//        final ShippingAddress expected = new ShippingAddress();
//        expected.setId(2L);
//        expected.setCity("FAIRFIELD2");
//        expected.setState("IOWA2");
//        expected.setStreet("4TH STREET2");
//        expected.setZipCode("987652");
//
//        final MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URI + "/getShippingAddrById/2").header(AUTHORIZATION, token)).andReturn();
//        JSONAssert.assertEquals(objectMapper.writeValueAsString(expected), result.getResponse().getContentAsString(), false);
//    }
//
//    @Test
//    void checkEmailExists() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.head(URI + "/exists/test1@gmail.com").header(AUTHORIZATION, token)).andExpect(MockMvcResultMatchers.status().isOk());
//    }
//
//    @Test
//    void checkEmailNotExists() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.head(URI + "/exists/unknown@gmail.com").header(AUTHORIZATION, token)).andExpect(MockMvcResultMatchers.status().isNotFound());
//    }
//
//    @Test
//    void registerWithEmailExists() throws Exception {
//        final User user = new User();
//        user.setEmail("test1@gmail.com");
//        user.setPassword("pass1");
//
//        mockMvc.perform(MockMvcRequestBuilders.post(URI + "/register").header(AUTHORIZATION, token)
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .content(objectMapper.writeValueAsString(user)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string("Email is exists"));
//    }
//
//    @Test
//    void registerWithUsernameAndEmailNotExists() throws Exception {
//        final User user = new User();
//        user.setEmail("register@gmail.com");
//        user.setPassword("register");
//
//        mockMvc.perform(MockMvcRequestBuilders.post(URI + "/register").header(AUTHORIZATION, token)
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .content(objectMapper.writeValueAsString(user)))
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andExpect(MockMvcResultMatchers.content().string("Done"));
//    }

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
//
//    @Test
//    public void getTheWorstProductsHavingLowestRatingGivenCategoryId() throws Exception {
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/getTheWorstProductsHavingLowestRatingGivenCategoryId?categoryId=901")
//                .header(AUTHORIZATION, token))
//                .andReturn();
//
//        Product[] products = objectMapper.readValue(result.getResponse().getContentAsString(), Product[].class);
//
//        Product expect = new Product();
//        expect.setId(201L);
//        expect.setAvailableQuantity(1);
//        expect.setName("TV");
//        expect.setUnitPrice(5);
//
//        Assertions.assertEquals(Arrays.stream(products).map(p->p.getName()).collect(Collectors.toList()), expect);
//    }
}
