package com.learn_security.authorization;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTests {
  @Autowired
  private MockMvc mvc;

  @Test
  public void demoEndpointSuccessfulAuthenticationTest() throws Exception {
    mvc.perform(
        get("/demo")
            .with(SecurityMockMvcRequestPostProcessors.jwt())
    ).andExpect(status().isOk());
  }

  @Test
  public void demoEndpointUnauthorizedTest() throws Exception {
    mvc.perform(
        get("/demo")
    ).andExpect(status().isUnauthorized());
  }
}
