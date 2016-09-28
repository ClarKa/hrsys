package org.hrsys.dao;

import java.sql.SQLException;

import org.hrsys.entity.User;

public interface UserManager {
    public void createUser(User user) throws SQLException;
    public User getOneUser(String username); 
}
