package org.hrsys.dao;

import java.sql.Date;
import java.util.List;

import org.hrsys.entity.Attendance;

public interface AttendanceManager {
    public List<Attendance> getOneEmployeeAttendance(int employeeID);
    public Attendance getAttendanceForDate(int employeeID, Date date);
}
