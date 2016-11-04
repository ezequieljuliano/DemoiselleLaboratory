package org.demoiselle.jsfjpa.security;

import br.gov.frameworkdemoiselle.security.AuthenticationException;
import br.gov.frameworkdemoiselle.security.Authenticator;
import java.security.Principal;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import org.demoiselle.jsfjpa.business.UserBC;
import org.demoiselle.jsfjpa.domain.User;
import org.demoiselle.jsfjpa.util.Hash;

@SessionScoped
public class AppAuthenticator implements Authenticator {

    private static final long serialVersionUID = 1L;

    @Inject
    private AppCredential credential;

    @Inject
    private UserBC userBC;

    private static boolean authenticated = false;

    @Override
    public void authenticate() throws Exception {
        String username = credential.getUsername();
        String password = credential.getPassword();

        User user = userBC.FindByUsernameAndPassword(username, Hash.fromString(password, Hash.MD5));
        if (user == null) {
            credential.clear();
            throw new AuthenticationException("Suas credenciais de acesso estão incorretas! Usuário não localizado.");
        } else {
            authenticated = true;
            credential.setUser(user);
        }
    }

    @Override
    public void unauthenticate() throws Exception {
        credential.clear();
        authenticated = false;
    }

    @Override
    public Principal getUser() {
        if ((authenticated) && (credential.getUser() != null)) {
            return credential.getUser();
        } else {
            return null;
        }
    }

}
