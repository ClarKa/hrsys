package org.hrsys.enums;

public enum AttendanceStatus {
    Late(1, "Late"), Normal(2, "Normal"), Early_Leave(3, "Leave Early"), Absence(4, "Absence");
    
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
