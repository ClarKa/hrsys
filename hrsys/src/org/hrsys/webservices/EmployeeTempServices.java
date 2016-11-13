package org.hrsys.webservices;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.hrsys.constants.CommonConstants;
import org.hrsys.constants.ServicePaths;
import org.hrsys.dao.EmployeeManager;
import org.hrsys.dao.EmployeeTempManager;
import org.hrsys.dto.EmployeeDTO;
import org.hrsys.dto.EmployeeTempDTO;
import org.hrsys.entity.Employee;
import org.hrsys.entity.EmployeeTemp;
import org.hrsys.facades.EmployeeTempFacade;
import org.hrsys.helpers.MetaAnnotations.EmployeeIdMatch;
import org.hrsys.helpers.MetaAnnotations.EmployeeIdMatchOrIsAdmin;
import org.hrsys.helpers.MetaAnnotations.IsAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = ServicePaths.GET_TEMP_EMPLOYEE_PATH)
public class EmployeeTempServices {
    @Autowired
    EmployeeTempManager employeeTempManager;

    @Autowired
    EmployeeManager employeeManager;

    private EmployeeTempFacade employeeTempFacade = new EmployeeTempFacade();

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @IsAdmin
    public List<EmployeeDTO> getAllTempRequest() {
        return null;
    }

    @RequestMapping(value = "/{employeeid}", method = RequestMethod.POST, produces = "application/json")
    @EmployeeIdMatch
    public EmployeeTempDTO createTempRequest(EmployeeTempDTO tempDto,
            @PathVariable("employeeid") int employeeID)
            throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {
        return employeeTempFacade.createTemp(employeeID, tempDto,
                employeeTempManager, employeeManager);
    }

    @RequestMapping(value = "/{employeeid}", method = RequestMethod.DELETE, produces = "application/json")
    @EmployeeIdMatchOrIsAdmin
    public EmployeeTempDTO deleteTempRequest(
            @PathVariable("employeeid") int employeeID) {
        return employeeTempFacade.deleteRequest(employeeID,
                employeeTempManager);
    }

    @RequestMapping(value = "/{employeeid}", method = RequestMethod.PUT, produces = "application/json")
    @IsAdmin
    public EmployeeTempDTO approveTempRequest(
            @PathVariable("employeeid") int employeeID)
            throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException, SQLException {
        return employeeTempFacade.approveRequest(employeeID,
                employeeTempManager, employeeManager);
    }
}
