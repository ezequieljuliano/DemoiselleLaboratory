package org.demoiselle.samplejsfjpa.security;

import java.io.Serializable;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import org.demoiselle.samplejsfjpa.domain.User;

@SessionScoped
@Named("credential")
public class AppCredential implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

    private User user;

    public AppCredential() {
        super();
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void clear() {
        this.username = null;
        this.password = null;
        this.user = null;
    }

}
