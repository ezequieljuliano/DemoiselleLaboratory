package org.demoiselle.samplejsfjpa.business;

import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.Strings;
import org.demoiselle.samplejsfjpa.base.GenericBC;
import org.demoiselle.samplejsfjpa.domain.Role;

import org.demoiselle.samplejsfjpa.domain.User;
import org.demoiselle.samplejsfjpa.exception.AppException;
import org.demoiselle.samplejsfjpa.persistence.UserDAO;
import org.demoiselle.samplejsfjpa.util.Hash;

@BusinessController
public class UserBC extends GenericBC<User, Long, UserDAO> {

    private static final long serialVersionUID = 1L;

    @Startup
    @Transactional
    public void load() {
        if (findAll().isEmpty()) {
            User adminUser = new User();
            adminUser.setUsername("admin");
            adminUser.setPassword("admin");
            adminUser.setRole(Role.ADMIN);
            insert(adminUser);

            User guestUser = new User();
            guestUser.setUsername("guest");
            guestUser.setPassword("guest");
            guestUser.setRole(Role.GUEST);
            insert(guestUser);
        }
    }

    public User FindByUsernameAndPassword(String username, String password) {
        return getDelegate().FindByUsernameAndPassword(username, password);
    }

    @Override
    protected void onAfterInsert(User bean) {
        String password = bean.getPassword();
        bean.setPassword(Hash.fromString(password, Hash.MD5));
    }

    public void changePassword(User bean, String newPassword) throws AppException {
        if (!Strings.isEmpty(newPassword)) {
            String pw = newPassword;
            bean.setPassword(Hash.fromString(pw, Hash.MD5));
            update(bean);
        } else {
            throw new AppException("Nenhum password foi informado!");
        }
    }

}
