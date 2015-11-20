package org.demoiselle.samplejsfjpa.business;

import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import org.demoiselle.samplejsfjpa.base.GenericBC;

import org.demoiselle.samplejsfjpa.domain.User;
import org.demoiselle.samplejsfjpa.persistence.UserDAO;

@BusinessController
public class UserBC extends GenericBC<User, Long, UserDAO> {

    private static final long serialVersionUID = 1L;

    @Startup
    @Transactional
    public void load() {
        if (findAll().isEmpty()) {
            User user = new User();
            user.setId(Long.valueOf(1));
            user.setUsername("admin");
            user.setPassword("admin");
            insert(user);
        }
    }

    public User FindByUsernameAndPassword(String username, String password) {
        return getDelegate().FindByUsernameAndPassword(username, password);
    }

}
