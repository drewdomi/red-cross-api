package com.drewdomi.redcross.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("auth")
public class AuthController extends Object {

    // @Autowired
    // AuthenticationManager authenticationManager;
}
