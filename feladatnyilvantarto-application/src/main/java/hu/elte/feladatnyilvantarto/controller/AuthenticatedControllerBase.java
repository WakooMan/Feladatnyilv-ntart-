package hu.elte.feladatnyilvantarto.controller;

import hu.elte.feladatnyilvantarto.domain.User;
import hu.elte.feladatnyilvantarto.service.UserService;
import hu.elte.feladatnyilvantarto.webdomain.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class AuthenticatedControllerBase {
    @Autowired
    private UserService userService;
    protected User GetAuthenticatedUser()
    {
        return userService.findUserById(((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getId());
    }
}
