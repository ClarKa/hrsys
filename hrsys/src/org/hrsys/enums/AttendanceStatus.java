package org.hrsys.enums;

public enum AttendanceStatus {
    Normal(1, "Normal"), LESS_HOURS(2, "Less Hours"), OVERTIME(3, "Overtime"); 
    
    private final int id;
    private final String description;
    
    private AttendanceStatus(int id, String description) {
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
