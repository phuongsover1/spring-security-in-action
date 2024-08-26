package com.learnsecurity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureWebTestClient
public class MainTests {

  @Autowired
  private WebTestClient client;

  @Test
  @WithUserDetails("john")
  void testCallHelloWorldWithValidUser() {
    client.get()
        .uri("/hello")
        .exchange()
        .expectStatus().isOk();
  }

  @Test
  @WithMockUser
  void testCiaoWithValidUser() {
    client.get()
        .uri("/ciao")
        .exchange()
        .expectStatus().isOk();
  }


}
