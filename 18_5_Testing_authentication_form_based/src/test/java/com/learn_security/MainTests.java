package com.learn_security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.*;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MainTests {
  @Autowired
  private MockMvc mvc;

  @Test
  public void loggingInWithWrongAuthority() throws Exception {
    mvc.perform(
      formLogin()
          .user("phuong")
          .password("12345")
    )
        .andExpect(redirectedUrl("/error"))
        .andExpect(status().isFound())
        .andExpect(authenticated());
  }

  @Test
  public void loggingInWithCorrectAuthority() throws Exception {
    mvc.perform(
        formLogin()
            .user("john")
            .password("123456")
    )
        .andExpect(redirectedUrl("/home"))
        .andExpect(status().isFound())
        .andExpect(authenticated());
  }
}
