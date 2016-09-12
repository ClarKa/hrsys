package org.hrsys.enums;

public enum Attendance {
    Late(1), Normal(2), Early_Leave(3), Absence(4);
    
    private final int value;
    
    private Attendance(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
