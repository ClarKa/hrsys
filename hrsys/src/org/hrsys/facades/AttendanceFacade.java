package org.hrsys.facades;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hrsys.dao.AttendanceManager;
import org.hrsys.dao.EmployeeManager;
import org.hrsys.dto.AttendanceDTO;
import org.hrsys.dto.UserDTO;
import org.hrsys.entity.Attendance;
import org.hrsys.entity.Employee;
import org.hrsys.enums.Role;
import org.springframework.security.core.context.SecurityContextHolder;

public class AttendanceFacade {
    public List<AttendanceDTO> getOneEmployeeAttendance(int employeeID, AttendanceManager attendanceManager, EmployeeManager employeeManager) {
        UserDTO userDto = (UserDTO) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String roleText = userDto.getAuthorities().toArray()[0].toString();
        if (!roleText.equals(Role.ADMIN.getDescription()) && employeeID != userDto.getEmployee().getEmployeeID()) {
            return new ArrayList<AttendanceDTO>();
        } 
        
        List<Attendance> attendanceList = attendanceManager.getOneEmployeeAttendance(employeeID);
        List<AttendanceDTO> attendanceDtoList = new ArrayList<AttendanceDTO>();
        
        Employee employee = employeeManager.getOneEmployee(employeeID);
        
        for (Attendance attendance : attendanceList) {
            attendanceDtoList.add(new AttendanceDTO(attendance, employee));
        }
        return attendanceDtoList;
    }
    
    public AttendanceDTO getAttendanceForDate(int employeeID, Date date, AttendanceManager attendanceManager, EmployeeManager employeeManager) {
        Employee employee = employeeManager.getOneEmployee(employeeID);
        List<Attendance> attendance = attendanceManager.getAttendanceForDate(employeeID, date);
        if (attendance == null || attendance.isEmpty()) {
            AttendanceDTO attendanceForDate = new AttendanceDTO();
            attendanceForDate.setError("No record found for date " + date);
            return attendanceForDate;
        } else {
            return new AttendanceDTO(attendance.get(0), employee);
        }
    }
}
