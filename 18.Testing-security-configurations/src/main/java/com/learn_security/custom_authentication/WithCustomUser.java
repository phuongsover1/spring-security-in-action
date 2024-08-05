package com.learn_security.custom_authentication;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface WithCustomUser {
  String username();
}
