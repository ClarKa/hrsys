package org.hrsys.dao;

import java.util.List;

import org.hrsys.entity.Attendance;

public interface AttendanceManager {
    public List<Attendance> getOneEmployeeAttendance(int employeeID);
}
