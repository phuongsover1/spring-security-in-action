package com.learn_security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.ResultMatcher.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;


import java.net.http.HttpRequest;

@SpringBootTest
@AutoConfigureMockMvc
public class MainTests {
   @Autowired
   private MockMvc mvc;

   @Test
   public void helloUnauthenticated() throws Exception {
     mvc.perform(get("/hello"))
         .andExpect(status().isUnauthorized());
   }

   @Test
   @WithMockUser()
   public void helloAuthenticated() throws Exception {
      mvc.perform(get("/hello"))
          .andExpect(content().string("Hello World"))
          .andExpect(status().isOk());
   }


   @Test
   @WithMockUser(username = "mary")
   public void helloAuthenticatedMary() throws Exception {
      mvc.perform(get("/hello"))
          .andExpect(content().string("Hello, mary!"))
          .andExpect(status().isOk());
   }

   @Test
   public void helloAuthenticatedWithUserUsingRequestPostProcessor() throws Exception {
      mvc.perform(
          get("/hello")
              .with(user("phuong"))
      )
          .andExpect(content().string("Hello, phuong!"))
          .andExpect(status().isOk());
   }
}
