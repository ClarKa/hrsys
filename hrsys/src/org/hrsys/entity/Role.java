package org.hrsys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @Column(name = "rl_role_id", unique = true, nullable = false, insertable = true, updatable = true)
    private int roleId;
    
    @Column(name = "rl_role_name", unique = true, nullable = false, insertable = true, updatable = true)
    private String roleName;

    /**
     * @return the roleId
     */
    public int getRoleId() {
        return roleId;
    }

    /**
     * @param roleId the roleId to set
     */
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    /**
     * @return the role
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * @param role the role to set
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
