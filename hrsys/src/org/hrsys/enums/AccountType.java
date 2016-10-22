package org.hrsys.enums;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape= JsonFormat.Shape.OBJECT)
public enum AccountType {
    CHECKING('C', "Checking"), SAVING('S', "Savings");
    
    private final char id;
    private final String description;
    private static Map<Character, AccountType> IdMap = new HashMap<Character, AccountType>();
    private static Map<String, Character> descriptionMap = new HashMap<String, Character>();
    
    static {
        for (AccountType type : AccountType.values()) {
            IdMap.put(type.id, type);
            descriptionMap.put(type.description, type.id);
        }
    }
    
    private AccountType(char id, String description) {
        this.id = id;
        this.description = description;
    }

    public char getId() {
        return id;
    }
    
    public String getDescription() {
        return description;
    }
    
    public static AccountType byId(char id) {
        return IdMap.get(id);
    }
    
    public static char byDescription(String description) {
        return descriptionMap.get(description);
    }
}
