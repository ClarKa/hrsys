package org.hrsys.constants;

public class ValidationConstants {
    public static final String PURE_WORD = "\\w+";
    public static final String PURE_WORD_OR_EMPTY = "\\w*";
    public static final String WORDS_WITH_NUMBER = "^[a-zA-Z0-9\\s]*$";
    public static final String PURE_NUMBER = "^[0-9]*$";
    public static final String GENDER = "Male|Female";
    public static final String PHONE = "[0-9]+"; 
    public static final String INVALID_EMPLOYEE_NAME = "Employee name cannot contain special characters.";
    public static final String INVALID_EMAIL = "Invalid email address";
    public static final String INVALID_GENDER = "Gender must be either Male or Female";
    public static final String INVALID_POSITION = "Position cannot contain special characters.";
    public static final String INVALID_PHONE = "Phone number must be pure number.";
    public static final String INVALID_PHONE_LENGTH = "Phone number must have a length of 11 or 10.";
    public static final String INVALID_TRAINING_HOUR = "Valid training hour must be 0 to 8 hours per day.";
    public static final String INVALID_BANK_NICKNAME = "Bank nickname cannot be empty and/or contain special characters.";
    public static final String INVALID_PERCENT = "Valid percent must be 0 to 100.";
    public static final String INVALID_ACCOUNT_NUMBER = "Account number must be pure numbers";
    public static final String INVALID_ROUTING_NUMBER = "Valid routing number must be 9-digit pure numbers.";
    public static final String UNMATCHED_ACCOUNT_NUMBER = "Account numbers does not match.";
    public static final String NULL_ACCOUNT_TYPE = "Pleas select an account type.";
}
