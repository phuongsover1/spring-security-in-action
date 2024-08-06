package com.learn_security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TestingCSRFProtection {
  @Autowired
  private MockMvc mvc;

  @Test
  void testHelloPOST() throws Exception {
    mvc.perform(
        post("/hello")
    )
        .andExpect(status().isForbidden());
  }

  @Test
  void testHelloPOSTWithCSRF() throws Exception {
    mvc.perform(
        post("/hello").with(csrf())
    )
        .andExpect(status().isOk());
  }
}
