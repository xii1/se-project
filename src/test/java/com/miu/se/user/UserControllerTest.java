package com.miu.se.user;

import com.miu.se.AbstractControllerTest;
import com.miu.se.common.entity.ShippingAddress;
import com.miu.se.common.entity.User;
import com.miu.se.common.entity.UserStatus;
import com.miu.se.common.util.PasswordUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


/**
 * @author XIII
 */
public class UserControllerTest extends AbstractControllerTest {

    private static final String URI = "/users";

    @Test
    void getAllUsers() throws Exception {
        final MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URI)).andReturn();
        final User[] users = objectMapper.readValue(result.getResponse().getContentAsString(), User[].class);
        Assertions.assertTrue(users.length >= 3);
    }

    @Test
    void getUserById() throws Exception {
        final User expected = new User();
        expected.setId(1L);
        expected.setEmail("test1@gmail.com");
        expected.setPassword(PasswordUtil.encode("admin"));
        expected.setStatus(UserStatus.ACTIVE);

        final MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URI + "/1")).andReturn();
        JSONAssert.assertEquals(objectMapper.writeValueAsString(expected), result.getResponse().getContentAsString(), false);
    }

    @Test
    void getShippingAddrById() throws Exception {
        final ShippingAddress expected = new ShippingAddress();
        expected.setId(2L);
        expected.setCity("FAIRFIELD2");
        expected.setState("IOWA2");
        expected.setStreet("4TH STREET2");
        expected.setZipCode("987652");

        final MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URI + "/getShippingAddrById/2")).andReturn();
        JSONAssert.assertEquals(objectMapper.writeValueAsString(expected), result.getResponse().getContentAsString(), false);
    }

    @Test
    void checkEmailExists() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.head(URI + "/exists/test1@gmail.com")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void checkEmailNotExists() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.head(URI + "/exists/unknown@gmail.com")).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void registerWithEmailExists() throws Exception {
        final User user = new User();
        user.setEmail("test1@gmail.com");
        user.setPassword("pass1");

        mockMvc.perform(MockMvcRequestBuilders.post(URI + "/register")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Email is exists"));
    }

    @Test
    void registerWithUsernameAndEmailNotExists() throws Exception {
        final User user = new User();
        user.setEmail("register@gmail.com");
        user.setPassword("register");

        mockMvc.perform(MockMvcRequestBuilders.post(URI + "/register")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("Done"));
    }

    @Test
    void loginWithUsernameAndEmailNotExists() throws Exception {
        final User user = new User();
        user.setEmail("unknown@gmail.com");
        user.setPassword("unknown");

        mockMvc.perform(MockMvcRequestBuilders.post(URI + "/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string("Email is not exists"));
    }

    @Test
    void loginWithInvalidPassword() throws Exception {
        final User user = new User();
        user.setEmail("test1@gmail.com");
        user.setPassword("test1");

        mockMvc.perform(MockMvcRequestBuilders.post(URI + "/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Password is invalid"));
    }

    @Test
    void loginSuccessful() throws Exception {
        final User user = new User();
        user.setEmail("test1@gmail.com");
        user.setPassword("admin");

        mockMvc.perform(MockMvcRequestBuilders.post(URI + "/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Done"));
    }

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
