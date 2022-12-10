package hu.elte.feladatnyilvantarto.controller;

import hu.elte.feladatnyilvantarto.domain.Credentials;
import hu.elte.feladatnyilvantarto.service.SignInException;
import hu.elte.feladatnyilvantarto.service.SignInService;
import hu.elte.feladatnyilvantarto.webdomain.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class SignInController {

    @Autowired
    private SignInService signInService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @RequestMapping("/signin")
    public String signIn(Model model)
    {
        model.addAttribute("userform",new UserForm());
        return "signin";
    }

    @PostMapping("/signin/signinaction")
    public String signInAction(@Valid @ModelAttribute("userform") UserForm userform, BindingResult bindingResult)
    {
        if(!bindingResult.hasErrors()) {
            signInService.signIn(new Credentials(userform.getUsername(), passwordEncoder.encode(userform.getPassword())));
            return "redirect:/signin";
        }
        return "redirect:/signin";
    }
}
