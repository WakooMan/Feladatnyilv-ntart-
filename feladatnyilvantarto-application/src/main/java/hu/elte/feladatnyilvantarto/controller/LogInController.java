package hu.elte.feladatnyilvantarto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogInController {

@RequestMapping("login")
public String login()
{
    return "login";
}
}
