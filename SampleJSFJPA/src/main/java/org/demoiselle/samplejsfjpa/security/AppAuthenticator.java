package org.demoiselle.samplejsfjpa.security;

import br.gov.frameworkdemoiselle.security.AuthenticationException;
import br.gov.frameworkdemoiselle.security.Authenticator;
import java.security.Principal;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import org.demoiselle.samplejsfjpa.business.UserBC;
import org.demoiselle.samplejsfjpa.domain.User;
import org.demoiselle.samplejsfjpa.util.HashUtil;

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

        User user = userBC.FindByUsernameAndPassword(username, HashUtil.generate(password, HashUtil.MD5));
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
            return new Principal() {
                @Override
                public String getName() {
                    return credential.getUser().getUsername();
                }
            };
        } else {
            return null;
        }
    }

}
