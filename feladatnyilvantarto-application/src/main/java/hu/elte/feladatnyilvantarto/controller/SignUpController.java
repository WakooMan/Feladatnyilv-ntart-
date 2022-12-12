package hu.elte.feladatnyilvantarto.controller;

import hu.elte.feladatnyilvantarto.service.SignUpException;
import hu.elte.feladatnyilvantarto.service.SignUpService;
import hu.elte.feladatnyilvantarto.webdomain.SignUpForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class SignUpController {
    @Autowired
    private SignUpService signUpService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @RequestMapping("signup")
    public String signUp(Model model)
    {
        if(!model.containsAttribute("signupform")) {
            model.addAttribute("signupform", new SignUpForm());
        }
        return "signup";
    }

    @PostMapping("signup/signupaction")
    public String signUpAction(@Valid SignUpForm signupform,BindingResult bindingResult, RedirectAttributes redirectAttributes)
    {
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.signupform",bindingResult);
            redirectAttributes.addFlashAttribute("signupform",signupform);
            return "redirect:/signup";
        }
        try {
            signUpService.signUp(signupform.getName(), passwordEncoder.encode(signupform.getPassword()), signupform.getUsername());
        }catch (SignUpException exception)
        {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.signupform",bindingResult);
            redirectAttributes.addFlashAttribute("signupform",signupform);
            redirectAttributes.addFlashAttribute("errormessage",exception.getMessage());
            return "redirect:/signup";
        }
        redirectAttributes.addFlashAttribute("signupmessage","Registration was successful!");
        return "redirect:/signin";
    }
}
