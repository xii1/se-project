package com.miu.se.sample;

import com.miu.se.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author XIII
 */
public class SampleControllerTests extends AbstractControllerTest {

    private static final String URI = "/sample";

    @Test
    void testHello() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(URI + "/hello"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Hello, this is sample controller."))
                .andReturn();
    }
}
