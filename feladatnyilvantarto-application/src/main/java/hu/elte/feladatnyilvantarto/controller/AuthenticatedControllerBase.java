package hu.elte.feladatnyilvantarto.controller;

import hu.elte.feladatnyilvantarto.domain.User;
import hu.elte.feladatnyilvantarto.webdomain.security.UserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class AuthenticatedControllerBase {
    protected User GetAuthenticatedUser()
    {
        return ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
    }
}
