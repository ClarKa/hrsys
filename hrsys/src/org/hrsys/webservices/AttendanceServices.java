package org.hrsys.webservices;

import java.sql.Date;
import java.util.List;

import org.hrsys.constants.CommonConstants;
import org.hrsys.constants.ServicePaths;
import org.hrsys.dao.AttendanceManager;
import org.hrsys.dao.EmployeeManager;
import org.hrsys.dto.AttendanceDTO;
import org.hrsys.entity.Attendance;
import org.hrsys.facades.AttendanceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = ServicePaths.GET_ATTENDANCE_PATH)
public class AttendanceServices {
    @Autowired
    AttendanceManager attendanceManager;
    
    @Autowired
    EmployeeManager employeeManager;
    
    private AttendanceFacade attendanceFacade = new AttendanceFacade();
    
    @RequestMapping(value = ServicePaths.GET_ONE_EMPLOYEE_PATH + "/{employeeid}", method = RequestMethod.GET, produces = "application/json")
    @PreAuthorize(CommonConstants.HAS_ROLE_ADMIN)
    public List<AttendanceDTO> getOneEmployeeAttendance(@PathVariable("employeeid") int employeeID) {
        return attendanceFacade.getOneEmployeeAttendance(employeeID, attendanceManager, employeeManager);
    }
    
    @RequestMapping(value = ServicePaths.GET_ONE_EMPLOYEE_PATH + "/{employeeid}" + "/{date}", method = RequestMethod.GET, produces = "application/json")
    @PreAuthorize(CommonConstants.HAS_ROLE_ADMIN)
    public AttendanceDTO getOneEmployeeAttendance(@PathVariable("employeeid") int employeeID, @PathVariable("date") Date date) {
        return attendanceFacade.getAttendanceForDate(employeeID, date, attendanceManager, employeeManager);
    }
}
