package org.hrsys.enums;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape= JsonFormat.Shape.OBJECT)
public enum PaymentMethod {
    PC("pc", "Paper Check"), DD("dd", "Direct Deposit");
    
    private final String id;
    private final String description;
    private static Map<String, PaymentMethod> IdMap = new HashMap<String, PaymentMethod>();
    private static Map<String, String> descriptionMap = new HashMap<String, String>();
    
    static {
        for(PaymentMethod method : PaymentMethod.values()) {
            IdMap.put(method.id, method);
            descriptionMap.put(method.description, method.id);
        }
    }
    
    private PaymentMethod(String id, String description) {
        this.id = id;
        this.description = description;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    
    public static PaymentMethod byId(String id) {
        return IdMap.get(id);
    }
    
    public static String byDescription(String description) {
        return descriptionMap.get(description);
    }
}
