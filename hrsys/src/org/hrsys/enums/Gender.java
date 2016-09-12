package org.hrsys.enums;

public enum Gender {
	Male(1), Female(2);
	
	private final int value;
	
    private Gender(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

