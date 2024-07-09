package com.learn_security.authorization.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {
    @Value("${keySetURI}")
    private String keySetURI;
}
