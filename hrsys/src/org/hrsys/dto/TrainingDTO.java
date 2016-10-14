package org.hrsys.dto;

import java.sql.Date;

import org.hrsys.entity.Employee;
import org.hrsys.entity.Training;

public class TrainingDTO {
    private int     employeeID;
    private String  name;
    private Date    date;
    private int     hour;
    private boolean approved;
    private String  error;

    public TrainingDTO() {
    }

    public TrainingDTO(Training training, Employee employee) {
        this.employeeID = training.getEmployeeID();
        this.name = employee.getFirstname() + " " + employee.getLastname();
        this.date = training.getDate();
        this.hour = training.getHour();
        this.approved = training.getApproved();
    }

    /**
     * @return the employeeID
     */
    public int getEmployeeID() {
        return employeeID;
    }

    /**
     * @param employeeID
     *            the employeeID to set
     */
    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date
     *            the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the hour
     */
    public int getHour() {
        return hour;
    }

    /**
     * @param hour
     *            the hour to set
     */
    public void setHour(int hour) {
        this.hour = hour;
    }

    /**
     * @return the approved
     */
    public boolean isApproved() {
        return approved;
    }

    /**
     * @param approved
     *            the approved to set
     */
    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    /**
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * @param error
     *            the error to set
     */
    public void setError(String error) {
        this.error = error;
    }
}
