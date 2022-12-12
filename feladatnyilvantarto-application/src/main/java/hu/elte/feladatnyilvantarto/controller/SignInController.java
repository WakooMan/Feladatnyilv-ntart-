package hu.elte.feladatnyilvantarto.controller;

import hu.elte.feladatnyilvantarto.domain.Credentials;
import hu.elte.feladatnyilvantarto.service.SignInService;
import hu.elte.feladatnyilvantarto.webdomain.SignInForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        if(!model.containsAttribute("signinform")){
        model.addAttribute("signinform",new SignInForm());
        }
        return "signin";
    }

    @PostMapping("/signin/signinaction")
    public String signInAction(@Valid SignInForm signinform, BindingResult bindingResult, RedirectAttributes redirectAttributes)
    {
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.signinform",bindingResult);
            redirectAttributes.addFlashAttribute("signinform",signinform);
            return "redirect:/signin";
        }
        signInService.signIn(new Credentials(signinform.getUsername(), passwordEncoder.encode(signinform.getPassword())));
        return "redirect:/signin";
    }
}
