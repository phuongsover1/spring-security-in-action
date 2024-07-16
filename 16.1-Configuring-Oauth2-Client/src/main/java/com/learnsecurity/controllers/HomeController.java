package com.learnsecurity.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.logging.Logger;

@Controller
public class HomeController {
    private Logger logger = Logger.getLogger(HomeController.class.getName());
    @GetMapping("/")
    public String home(OAuth2AuthenticationToken authentication, Model model) {
        model.addAttribute("user", authentication.getPrincipal().getName());
        logger.info("User " + authentication.getPrincipal().getName() + " logged in");
        return "index";
    }
}
