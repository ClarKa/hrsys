package org.hrsys.dto;

import org.hrsys.constants.ServicePaths;

public class UrlDTO {
    private final String employeeInfoUrl = ServicePaths.GET_EMPLOYEE_PATH;
    private final String oneEmployeeUrl = ServicePaths.GET_ONE_EMPLOYEE_PATH;
    private final String departmentUrl = ServicePaths.GET_DEPARTMENT_PATH;
    private final String attendanceUrl = ServicePaths.GET_ATTENDANCE_PATH;
    private final String trainingUrl = ServicePaths.GET_TRAINING_RECORD_PATH;
    private final String bankInfoUrl = ServicePaths.GET_BANK_INFO_PATH;
    private final String paychecksUrl = ServicePaths.GET_PAYCHECKS_PATH;
    
    /**
     * @return the employeeInfoUrl
     */
    public String getEmployeeInfoUrl() {
        return employeeInfoUrl;
    }
    /**
     * @return the oneEmployeeUrl
     */
    public String getOneEmployeeUrl() {
        return oneEmployeeUrl;
    }
    /**
     * @return the departmentUrl
     */
    public String getDepartmentUrl() {
        return departmentUrl;
    }
    /**
     * @return the attendanceUrl
     */
    public String getAttendanceUrl() {
        return attendanceUrl;
    }
    /**
     * @return the trainingUrl
     */
    public String getTrainingUrl() {
        return trainingUrl;
    }
    /**
     * @return the bankInfoUrl
     */
    public String getBankInfoUrl() {
        return bankInfoUrl;
    }
    /**
     * @return the paychecksUrl
     */
    public String getPaychecksUrl() {
        return paychecksUrl;
    }
}
