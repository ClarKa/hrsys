package org.hrsys.dto;

import java.sql.Date;
import java.sql.Time;

import org.hrsys.entity.Attendance;
import org.hrsys.entity.Employee;

/**
 * @author kaifu
 *
 */
public class AttendanceDTO {
    private int employeeID;
    private String name;
    private Date date;
    private Time inTime;
    private Time outTime;
    private String comment;
    private Date enrollmentDate;
    private String error;
    
    public AttendanceDTO() {
        
    }
    
    public AttendanceDTO(Attendance attendance, Employee employee) {
        this.employeeID = attendance.getEmployeeID();
        this.name = employee.getFirstname() + " " + employee.getLastname();
        this.date = attendance.getDate();
        this.inTime = attendance.getInTime();
        this.outTime = attendance.getOutTime();
        this.comment = attendance.getComment();
        this.enrollmentDate = employee.getEnrollmentDate();
    }
    
    /**
     * @return the employeeID
     */
    public int getEmployeeID() {
        return employeeID;
    }
    /**
     * @param employeeID the employeeID to set
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
     * @param name the name to set
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
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }
    /**
     * @return the inTime
     */
    public Time getInTime() {
        return inTime;
    }
    /**
     * @param inTime the inTime to set
     */
    public void setInTime(Time inTime) {
        this.inTime = inTime;
    }
    /**
     * @return the outTime
     */
    public Time getOutTime() {
        return outTime;
    }
    /**
     * @param outTime the outTime to set
     */
    public void setOutTime(Time outTime) {
        this.outTime = outTime;
    }
    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }
    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
    /**
     * @return the enrollmentDate
     */
    public Date getEnrollmentDate() {
        return enrollmentDate;
    }
    /**
     * @param enrollmentDate the enrollmentDate to set
     */
    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
    /**
     * @return the error
     */
    public String getError() {
        return error;
    }
    /**
     * @param error the error to set
     */
    public void setError(String error) {
        this.error = error;
    }
}
