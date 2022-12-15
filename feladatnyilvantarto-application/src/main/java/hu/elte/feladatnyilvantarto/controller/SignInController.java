package hu.elte.feladatnyilvantarto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SignInController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping("/signin")
    public String signIn()
    {
        return "signin";
    }
}
