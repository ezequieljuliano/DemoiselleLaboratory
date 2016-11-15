package org.demoiselle.jsfjpa.view;

import br.gov.frameworkdemoiselle.security.SecurityContext;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import java.io.Serializable;
import javax.inject.Inject;
import org.demoiselle.jsfjpa.domain.enums.Role;
import org.demoiselle.jsfjpa.domain.User;

@ViewController
public class SecurityMB implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private SecurityContext securityContext;

    public SecurityMB() {
    }

    public boolean isLoggedIn() {
        return securityContext.isLoggedIn();
    }

    public boolean hasAdminRole() {
        boolean hasRole = false;
        if (isLoggedIn()) {
            hasRole = securityContext.hasRole(Role.ADMIN.name());
        }
        return hasRole;
    }

    public boolean hasGuestRole() {
        boolean hasRole = false;
        if (isLoggedIn()) {
            hasRole = securityContext.hasRole(Role.ADMIN.name()) || securityContext.hasRole(Role.GUEST.name());
        }
        return hasRole;
    }

    public User getLoggedUser() {
        return (User) securityContext.getUser();
    }

}
