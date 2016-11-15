package org.demoiselle.jsfjpa.business;

import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.Strings;
import org.demoiselle.jsfjpa.template.CrudBC;
import org.demoiselle.jsfjpa.domain.enums.Role;
import org.demoiselle.jsfjpa.domain.User;
import org.demoiselle.jsfjpa.exception.AppException;
import org.demoiselle.jsfjpa.persistence.UserDAO;
import org.demoiselle.jsfjpa.util.Hash;

@BusinessController
public class UserBC extends CrudBC<User, Long, UserDAO> {

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
