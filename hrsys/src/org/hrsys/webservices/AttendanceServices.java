package org.hrsys.webservices;

import java.util.List;

import org.hrsys.constants.CommonConstants;
import org.hrsys.constants.ServicePaths;
import org.hrsys.dao.AttendanceManager;
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
    
    private AttendanceFacade attendanceFacade = new AttendanceFacade();
    
    @RequestMapping(value = ServicePaths.GET_ONE_EMPLOYEE_PATH + "/{employeeid}", method = RequestMethod.GET, produces = "application/json")
    @PreAuthorize(CommonConstants.HAS_ROLE_ADMIN)
    public List<Attendance> getOneEmployeeAttendance(@PathVariable("employeeid") int employeeID) {
        return attendanceFacade.getOneEmployeeAttendace(employeeID, attendanceManager);
    }
}
