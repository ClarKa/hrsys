package org.hrsys.constants;

public class CommonConstants {
    public static final String HAS_ROLE_ADMIN = "hasRole('ADMIN')";
    public static final String HAS_ROLE_USER = "hasRole('USER')";
    public static final String HAS_ROLE_AUTHENTICATED = "isAuthenticated()";
    public static final String EMPLOYEE_ID_MATCH_OR_HAS_ROLE_ADMIN = "#employeeID == principal.employeeID or " + HAS_ROLE_ADMIN;
    public static final String EMPLOYEE_ID_MATCH = "#employeeID == principal.employeeID";
    public static final int ATTENDANCE_SET_IN_TIME_CODE = 1;
    public static final int ATTENDANCE_SET_OUT_TIME_CODE = 2;
    public static final int ATTENDANCE_SET_COMMENT_CODE = 3;
    public static final String APPROVE_ALL_TRAINING_RECORDS_ACTION = "approve all";
    public static final String CANNOT_UPDATE_APPROVED_TRAINING_RECORDS = "Cannot change approved training records.";
    public static final String NO_CHANGE = "No change has been made. Request has not been submitted.";
    public static final String REQUEST_ALREADY_EXISTS = "There is still a pending request waiting for processing. Please cancel the current request before submitting a new request.";
    public static final String DEFAULT_PASSWORD = "changeme";
}
