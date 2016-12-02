package org.hrsys.dao;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hrsys.entity.CustomUser;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserManagerImpl implements UserManager {
    @PersistenceContext
    private EntityManager mgr;

    @Override
    public void createUser(CustomUser user) throws SQLException {
        mgr.persist(user);
    }

    @Override
    public CustomUser getOneUser(String username) {
        return mgr.find(CustomUser.class, username);
    }

}
