package org.hrsys.facades;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.hrsys.constants.CommonConstants;
import org.hrsys.dao.AttendanceManager;
import org.hrsys.dao.EmployeeManager;
import org.hrsys.dto.AttendanceDTO;
import org.hrsys.entity.Attendance;
import org.hrsys.entity.Employee;
import org.hrsys.enums.AttendanceStatus;

public class AttendanceFacade {
    public List<AttendanceDTO> getOneEmployeeAttendance(int employeeID,
            AttendanceManager attendanceManager,
            EmployeeManager employeeManager) {
        List<Attendance> attendanceList = attendanceManager
                .getOneEmployeeAttendance(employeeID);
        List<AttendanceDTO> attendanceDtoList = new ArrayList<AttendanceDTO>();

        Employee employee = employeeManager.getOneEmployee(employeeID);

        for (Attendance attendance : attendanceList) {
            attendanceDtoList.add(new AttendanceDTO(attendance, employee));
        }
        return attendanceDtoList;
    }

    public AttendanceDTO getAttendanceForDate(int employeeID, Date date,
            AttendanceManager attendanceManager,
            EmployeeManager employeeManager) {
        Employee employee = employeeManager.getOneEmployee(employeeID);
        Attendance attendance = attendanceManager
                .getAttendanceForDate(employeeID, date);
        if (attendance == null) {
            AttendanceDTO attendanceForDate = new AttendanceDTO();
            attendanceForDate.setError("No record found for date " + date);
            return attendanceForDate;
        } else {
            return new AttendanceDTO(attendance, employee);
        }
    }

    public AttendanceDTO createAttendance(int employeeID,
            AttendanceManager attendanceManager,
            EmployeeManager employeeManager) throws ParseException {
        if (employeeID == 0) {
            AttendanceDTO attendanceDto = new AttendanceDTO();
            attendanceDto.setError(
                    "No employee is currently connected to this user account.");
        }

        Date date = new Date(System.currentTimeMillis());
        Time time = new Time(System.currentTimeMillis());

        Attendance attendance = attendanceManager
                .getAttendanceForDate(employeeID, date);
        Employee employee = employeeManager.getOneEmployee(employeeID);
        AttendanceDTO attendanceDto = null;

        // Record in time.
        if (attendance == null) {
            Attendance newAttendance = new Attendance();
            newAttendance.setEmployeeID(employeeID);
            newAttendance.setDate(date);
            newAttendance.setInTime(time);
            attendanceDto = new AttendanceDTO(newAttendance, employee);

            try {
                attendanceManager.createAttendanceForDate(newAttendance);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                attendanceDto.setError(e.getMessage());
            }
        } else {
            // Record out time.
            if (attendance.getOutTime() == null) {
                SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                java.util.Date date1 = format.parse(attendance.getInTime().toString());
                java.util.Date date2 = format.parse(time.toString());
                long difference = date2.getTime() - date1.getTime(); 
                
                long normalHour = CommonConstants.NORMAL_WORK_HOUR_IN_MS;
                
                Attendance newAttendance = new Attendance();
                newAttendance.setEmployeeID(employeeID);
                newAttendance.setDate(date);
                newAttendance.setOutTime(time);
                
                if (difference > normalHour) {
                    newAttendance.setStatus(AttendanceStatus.OVERTIME.getDescription());
                } else if (difference < normalHour) {
                    newAttendance.setStatus(AttendanceStatus.LESS_HOURS.getDescription());
                } else {
                    newAttendance.setStatus(AttendanceStatus.Normal.getDescription());
                }
                
                attendanceDto = new AttendanceDTO(newAttendance, employee);
                
                try {
                    attendanceManager.updateAttendanceForDate(newAttendance,
                            CommonConstants.ATTENDANCE_SET_OUT_TIME_CODE);
                    attendanceManager.updateAttendanceForDate(newAttendance,
                            CommonConstants.ATTENDANCE_SET_STATUS_CODE);
                } catch (Exception e) {
                    attendanceDto.setError(e.getMessage());
                }
            }
        }

        return attendanceDto;
    }
}
