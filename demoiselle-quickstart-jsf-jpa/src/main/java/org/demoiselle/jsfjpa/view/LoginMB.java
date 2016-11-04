package org.demoiselle.jsfjpa.view;

import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.security.SecurityContext;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractPageBean;
import javax.inject.Inject;
import org.demoiselle.jsfjpa.security.AppCredential;

@ViewController
public class LoginMB extends AbstractPageBean {

    private static final long serialVersionUID = 1L;

    private String username = "";
    private String password = "";

    @Inject
    private SecurityContext securityContext;

    @Inject
    private MessageContext messageContext;

    @Inject
    private AppCredential credential;

    public LoginMB() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String doLogin() {
        try {
            credential.setUsername(getUsername());
            credential.setPassword(getPassword());
            securityContext.login();
            return "";
        } catch (Exception e) {
            messageContext.add(e.getMessage());
            return "";
        }
    }

    public void doLogout() {
        securityContext.logout();
    }

}
