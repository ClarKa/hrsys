package org.hrsys.enums;

public enum RoleEnum {
    USER(1, "ROLE_USER"), ADMIN(2, "ROLE_ADMIN"), SUPERADMIN(3, "ROLE_SUPERADMIN"), VISITOR(4, "ROLE_VISITOR");
    
    private final int id;
    private final String description;
    
    private RoleEnum(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getValue() {
        return id;
    }
    
    public String getDescription() {
        return description;
    }
}
