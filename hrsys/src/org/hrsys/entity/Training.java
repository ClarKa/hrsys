package org.hrsys.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.hrsys.helpers.TrainingPK;

@Entity
@Table(name = "training")
@IdClass(value = TrainingPK.class)
public class Training {
    @Id
    @Column(name = "tr_employee_id", unique = true, nullable = false, insertable = true, updatable = true)
    private int employeeID;

    @Id
    @Column(name = "tr_date", unique = true, nullable = false, insertable = true, updatable = true)
    private Date date;

    @Column(name = "tr_hour", unique = false, nullable = false, insertable = true, updatable = true)
    private int hour;

    @Column(name = "tr_approved", unique = false, nullable = false, insertable = true, updatable = true)
    private boolean approved;

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
     * @return the hour
     */
    public int getHour() {
        return hour;
    }

    /**
     * @param hour the hour to set
     */
    public void setHour(int hour) {
        this.hour = hour;
    }

    /**
     * @return the approved
     */
    public boolean getApproved() {
        return approved;
    }

    /**
     * @param approved the approved to set
     */
    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
