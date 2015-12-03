package org.demoiselle.samplejsfjpa.persistence;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import java.util.List;
import javax.persistence.TypedQuery;
import org.demoiselle.samplejsfjpa.base.GenericDAO;

import org.demoiselle.samplejsfjpa.domain.User;

@PersistenceController
public class UserDAO extends GenericDAO<User, Long> {

    private static final long serialVersionUID = 1L;

    public User FindByUsernameAndPassword(String username, String password) {
        StringBuilder jpql = new StringBuilder();
        jpql.append(" select ");
        jpql.append("    u ");
        jpql.append(" from User u ");
        jpql.append(" where ");
        jpql.append("   (u.username = :username) and (u.password = :password) ");

        TypedQuery<User> query = getEntityManager().createQuery(jpql.toString(), User.class);
        query.setParameter("username", username);
        query.setParameter("password", password);

        List<User> result = query.getResultList();
        if (!result.isEmpty()) {
            return result.get(0);
        } else {
            return null;
        }
    }

}
