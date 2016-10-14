package org.hrsys.helpers;

import java.io.Serializable;
import java.sql.Date;

public class TrainingPK implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer employeeID;
    private Date    date;

    public TrainingPK() {
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TrainingPK) {
            TrainingPK trainingPK = (TrainingPK) obj;

            if (!trainingPK.getEmployeeID().equals(employeeID)) {
                return false;
            }

            if (!trainingPK.getDate().equals(date)) {
                return false;
            }

            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return employeeID.hashCode() + date.hashCode();
    }

    /**
     * @return the employeeID
     */
    public Integer getEmployeeID() {
        return employeeID;
    }

    /**
     * @param employeeID the employeeID to set
     */
    public void setEmployeeID(Integer employeeID) {
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
}
