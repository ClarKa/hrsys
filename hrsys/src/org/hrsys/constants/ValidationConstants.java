package org.hrsys.constants;

public class ValidationConstants {
    public static final String PURE_WORD = "\\w+";
    public static final String GENDER = "Male|Female";
    public static final String PHONE = "[0-9]+"; 
    
    public static final String INVALID_EMPLOYEE_NAME = "Employee name cannot contain special characters.";
    public static final String INVALID_EMAIL = "Invalid email address";
    public static final String INVALID_GENDER = "Gender must be either Male or Female";
    public static final String INVALID_POSITION = "Position cannot contain special characters.";
    public static final String INVALID_PHONE = "Phone number must be pure number.";
    public static final String INVALID_PHONE_LENGTH = "Phone number must have a length of 11 or 10.";
}
