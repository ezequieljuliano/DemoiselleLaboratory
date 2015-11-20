package org.demoiselle.samplejsfjpa.persistence;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import org.demoiselle.samplejsfjpa.base.GenericDAO;

import org.demoiselle.samplejsfjpa.domain.User;

@PersistenceController
public class UserDAO extends GenericDAO<User, Long> {

    private static final long serialVersionUID = 1L;

    public User FindByUsernameAndPassword(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
