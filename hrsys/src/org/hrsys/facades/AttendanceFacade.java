package org.hrsys.facades;

import java.util.List;

import org.hrsys.dao.AttendanceManager;
import org.hrsys.entity.Attendance;

public class AttendanceFacade {
    public List<Attendance> getOneEmployeeAttendace(int employeeID, AttendanceManager attendanceManager) {
        return attendanceManager.getOneEmployeeAttendance(employeeID);
    }
    
}
