package com.learn_security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MainTests {
  @Autowired
  private MockMvc mvc;

  @Test
  public void helloAuthenticatingWithValidUser() throws Exception {
    mvc.perform(
            get("/hello")
                .with(httpBasic("john", "12345"))
        )
        .andExpect(content().string("Hello"))
        .andExpect(status().isOk());
  }

  @Test
  public void helloAuthenticationWtihInvalidUser() throws Exception {
    mvc.perform(
            get("/hello")
                .with(httpBasic("phuong", "1245"))
        )
        .andExpect(status().isUnauthorized());
  }
}
