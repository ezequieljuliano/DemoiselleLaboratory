package org.demoiselle.samplejsfjpa.security;

import br.gov.frameworkdemoiselle.security.Authorizer;
import br.gov.frameworkdemoiselle.security.SecurityContext;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import org.demoiselle.samplejsfjpa.domain.Role;
import org.demoiselle.samplejsfjpa.exception.AppException;

@SessionScoped
public class AppAuthorizer implements Authorizer {

    private static final long serialVersionUID = 1L;

    @Inject
    private AppCredential credential;

    @Inject
    private SecurityContext securityContext;

    @Override
    public boolean hasRole(String role) throws Exception {
        boolean authorized = false;
        if (securityContext != null) {
            if (securityContext.isLoggedIn()) {
                try {
                    Role r = Role.valueOf(role);
                    if (r != null) {
                        authorized = credential.getUser().getRole() == r;
                    }
                } catch (Exception e) {
                    throw new AppException("Usuário não autenticado. Erro: " + e.getMessage());
                }
            } else {
                throw new AppException("Usuário não autenticado");
            }
        } else {
            throw new AppException("Usuário não autenticado");
        }
        return authorized;
    }

    @Override
    public boolean hasPermission(String resource, String operation) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
