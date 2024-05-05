package com.drewdomi.redcross.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

  @GetMapping("")
  public String test() {
    return "Welcome to Red Cross API";
  }
}
