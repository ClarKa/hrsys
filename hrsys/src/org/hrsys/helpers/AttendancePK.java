package org.hrsys.helpers;

import java.io.Serializable;
import java.sql.Date;

public class AttendancePK implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Integer employeeID;
    private Date date;
    
    public AttendancePK() {}
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof AttendancePK){
            AttendancePK attendancePk = (AttendancePK) obj;
 
            if(!attendancePk.getEmployeeID().equals(employeeID)){
                return false;
            }
 
            if(!attendancePk.getDate().equals(date)){
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

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
