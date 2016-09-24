package org.hrsys.dao;

import java.sql.Date;
import java.util.List;

import org.hrsys.entity.Attendance;

public interface AttendanceManager {
    public List<Attendance> getOneEmployeeAttendance(int employeeID);
    public List<Attendance> getAttendanceForDate(int employeeID, Date date);
}
