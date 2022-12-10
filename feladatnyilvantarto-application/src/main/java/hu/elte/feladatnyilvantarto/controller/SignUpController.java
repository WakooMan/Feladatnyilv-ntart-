package hu.elte.feladatnyilvantarto.controller;

import hu.elte.feladatnyilvantarto.service.SignInService;
import hu.elte.feladatnyilvantarto.service.SignUpService;
import hu.elte.feladatnyilvantarto.webdomain.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {
    @Autowired
    private SignUpService signUpService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @RequestMapping("signup")
    public String signUp(Model model)
    {
        model.addAttribute("userform",new UserForm());
        return "signup";
    }

    @PostMapping("signup/signupaction")
    public String signUpAction(@ModelAttribute("userform") UserForm userform, BindingResult bindingResult)
    {
        if(!bindingResult.hasErrors()) {
            signUpService.signUp(userform.getUsername(), passwordEncoder.encode(userform.getPassword()), userform.getUsername());
            return "redirect:/signin";
        }
        return "redirect:/signup";
    }
}
