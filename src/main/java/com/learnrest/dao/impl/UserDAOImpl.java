package com.learnrest.dao.impl;

import com.learnrest.dao.GenericDAO;
import com.learnrest.dao.UserDAO;
import com.learnrest.model.User;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

/**
 *
 * @author fernando
 */
@ApplicationScoped
public class UserDAOImpl extends GenericDAO<User, Long> implements UserDAO {

    public UserDAOImpl() {
        super(User.class);
    }

    @Override
    public User findByUsername(String username) {
        Query query = getEntityManager().createNamedQuery("User.findByUsername", User.class);
        query.setParameter("username", username);
        List<User> users = query.getResultList();

        if (users == null || users.isEmpty()) {
            return null;
        } else if (users.size() > 1) {
            throw new NonUniqueResultException();
        } else {
            return users.get(0);
        }
    }

    @Override
    public User findByCredentials(String username, String password) {
        Query query = getEntityManager().createNamedQuery("User.findByCredentials", User.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        List<User> users = query.getResultList();

        if (users == null || users.isEmpty()) {
            return null;
        } else if (users.size() > 1) {
            throw new NonUniqueResultException();
        } else {
            return users.get(0);
        }
    }

}
