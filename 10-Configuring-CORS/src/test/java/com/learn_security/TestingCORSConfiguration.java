package com.learn_security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class TestingCORSConfiguration {

  @Autowired
  private MockMvc mvc;

  @Test
  void testCORSForTestEndpoint() throws Exception {
    mvc.perform(
        options("/tests")
            .header("Access-Control-Request-Method", "POST")
            .header("Origin", "http://localhost:8080")
    )
        .andExpect(header().exists("Access-Control-Allow-Origin"))
        .andExpect(header().string("Access-Control-Allow-Origin", "http://localhost:8080"))
        .andExpect(header().exists("Access-Control-Allow-Methods"))
        .andExpect(header().string("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE"))
        .andExpect(status().isOk());
  }
}
