package org.hrsys.dao;

import java.sql.SQLException;

import org.hrsys.entity.CustomUser;

public interface UserManager {
    public void createUser(CustomUser user) throws SQLException;
    public CustomUser getOneUser(String username); 
}
