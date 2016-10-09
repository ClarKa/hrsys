package org.hrsys.entity;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.hrsys.helpers.AttendancePK;

@Entity
@Table(name = "attendance")
@IdClass(value = AttendancePK.class)
public class Attendance {
    @Id
    @Column(name = "at_employee_id", unique = true, nullable = false, insertable = true, updatable = true)
    private int employeeID;
    
    @Id
    @Column(name = "at_date", unique = true, nullable = false, insertable = true, updatable = true)
    private Date date;
    
    @Column(name = "at_in_time", unique = false, nullable = true, insertable = true, updatable = true)
    private Time inTime;
    
    @Column(name = "at_out_time", unique = false, nullable = true, insertable = true, updatable = true)
    private Time outTime;
    
    @Column(name = "at_status", unique = false, nullable = true, insertable = true, updatable = true)
    private String status;
    
    @Column(name = "at_comment", unique = false, nullable = true, insertable = true, updatable = true)
    private String comment;

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
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
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
}
