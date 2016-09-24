package org.hrsys.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hrsys.entity.Attendance;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class AttendanceManagerImpl implements AttendanceManager {
    @PersistenceContext
    private EntityManager mgr;
    
    @Override
    public List<Attendance> getOneEmployeeAttendance(int employeeID) {
        List<Attendance> results = new ArrayList<>();
        try {
            Query jpqlQuery = mgr.createQuery("SELECT a FROM Attendance a WHERE a.employeeID = :employeeID")
                    .setParameter("employeeID", employeeID);
            results = jpqlQuery.getResultList();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return results;
    }
    
    @Override
    public List<Attendance> getAttendanceForDate(int employeeID, Date date) {
        List<Attendance> results = new ArrayList<>();
        try {
            Query jpqlQuery = mgr.createQuery("SELECT a FROM Attendance a WHERE a.employeeID = :employeeID and a.date = :date")
                    .setParameter("employeeID", employeeID)
                    .setParameter("date", date);
            results = jpqlQuery.getResultList();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return results;
    }
}
