package org.hrsys.dao;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hrsys.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserManagerImpl implements UserManager {
    @PersistenceContext
    private EntityManager mgr;

    @Override
    public void createUser(User user) throws SQLException {

    }

    @Override
    public User getOneUser(String username) {
        return mgr.find(User.class, username);
    }

}
