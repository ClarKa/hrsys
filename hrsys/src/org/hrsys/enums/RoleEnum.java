package org.hrsys.enums;

import java.util.HashMap;
import java.util.Map;

public enum RoleEnum {
    USER(1, "ROLE_USER"), ADMIN(2, "ROLE_ADMIN"), SUPERADMIN(3, "ROLE_SUPERADMIN"), VISITOR(4, "ROLE_VISITOR");
    
    private final int id;
    private final String description;
    
    private static Map<Integer, RoleEnum> IdMap = new HashMap<Integer, RoleEnum>();
    
    static {
        for(RoleEnum role : RoleEnum.values()) {
            IdMap.put(role.id, role);
        }
    }
    
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
    
    public static RoleEnum byId(int id) {
        return IdMap.get(id);
    }
}
