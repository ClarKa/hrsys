package org.hrsys.webservices;

import java.util.List;

import org.hrsys.constants.ServicePaths;
import org.hrsys.dao.DepartmentManager;
import org.hrsys.dao.EmployeeManager;
import org.hrsys.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = ServicePaths.GET_DEPARTMENT_PATH)
public class DepartmentServices {
    @Autowired
    EmployeeManager empManager;

    @Autowired
    DepartmentManager departmentManager;
    
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Department> getAllDepartment() {
	return departmentManager.getAllDepartment();
    }
}
