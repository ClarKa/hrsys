package org.hrsys.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javassist.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hrsys.constants.CommonConstants;
import org.hrsys.entity.Attendance;
import org.hrsys.helpers.AttendancePK;
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
    public Attendance getAttendanceForDate(int employeeID, Date date) {
        Attendance result = new Attendance();
        try {
            AttendancePK attendancePK = new AttendancePK();
            attendancePK.setEmployeeID(employeeID);
            attendancePK.setDate(date);
            result = mgr.find(Attendance.class, attendancePK);
//            Query jpqlQuery = mgr.createQuery("SELECT a FROM Attendance a WHERE a.employeeID = :employeeID and a.date = :date")
//                    .setParameter("employeeID", employeeID)
//                    .setParameter("date", date);
//            results = jpqlQuery.getResultList();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void createAttendanceForDate(Attendance attendance) throws SQLException {
        mgr.persist(attendance);
    }

    @Override
    public void updateAttendanceForDate(Attendance attendance, int toUpdate) throws SQLException {        
        AttendancePK attendancePK = new AttendancePK();
        attendancePK.setEmployeeID(attendance.getEmployeeID());
        attendancePK.setDate(attendance.getDate());
        Attendance result = mgr.find(Attendance.class, attendancePK);

        if (result == null) {
            throw new SQLException("record not found!");
        }
        
        switch (toUpdate) {
            case CommonConstants.ATTENDANCE_SET_IN_TIME_CODE:
                result.setInTime(attendance.getInTime());
            case CommonConstants.ATTENDANCE_SET_OUT_TIME_CODE:
                result.setOutTime(attendance.getOutTime());
            case CommonConstants.ATTENDANCE_SET_COMMENT_CODE:
                result.setComment(attendance.getComment());
            default:
                ;
         }
    }
}
