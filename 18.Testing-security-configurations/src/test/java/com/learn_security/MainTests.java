package com.learn_security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.ResultMatcher.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


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
}
