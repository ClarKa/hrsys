package org.hrsys.facades;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hrsys.constants.CommonConstants;
import org.hrsys.dao.EmployeeManager;
import org.hrsys.dao.EmployeeTempManager;
import org.hrsys.dto.EmployeeDTO;
import org.hrsys.dto.EmployeeTempDTO;
import org.hrsys.entity.Department;
import org.hrsys.entity.Employee;
import org.hrsys.entity.EmployeeTemp;

public class EmployeeTempFacade {

    public EmployeeTempDTO createTemp(int employeeId, EmployeeTempDTO tempDto,
            EmployeeTempManager employeeTempManager,
            EmployeeManager employeeManager) throws IllegalAccessException,
            InvocationTargetException, NoSuchMethodException {
        if (employeeTempManager.getOneRequest(employeeId, false) != null) {
            tempDto.setError(CommonConstants.REQUEST_ALREADY_EXISTS);
            return tempDto;
        }

        EmployeeTemp temp = new EmployeeTemp();
        temp.setEmployeeID(employeeId);
        tempDto.setEmployeeID(employeeId);

        EmployeeDTO emp = new EmployeeDTO(
                employeeManager.getOneEmployee(employeeId));
        BeanMap map = new BeanMap(tempDto);
        PropertyUtilsBean propUtils = new PropertyUtilsBean();

        boolean isNew = false;
        for (Object prop : map.keySet()) {

            String propName = (String) prop;
            if (propName.equals("error") || propName.equals("employeeID")
                    || propName.equals("class")) {
                continue;
            }
            Object oldProp = propUtils.getProperty(emp, propName);
            Object newProp = propUtils.getProperty(tempDto, propName);

            if (newProp != null && !newProp.equals(oldProp)) {
                isNew = true;
                propUtils.setProperty(temp, propName, newProp);
            }
        }

        if (isNew) {
            try {
                employeeTempManager.createTempEmployee(temp);
            } catch (Exception e) {
                tempDto.setError(e.getLocalizedMessage());
                ;
            }
        } else {
            tempDto.setError(CommonConstants.NO_CHANGE);
        }

        return tempDto;
    }

    public EmployeeTempDTO deleteRequest(int employeeId,
            EmployeeTempManager employeeTempManager) {
        EmployeeTempDTO employeeTempDTO = new EmployeeTempDTO();
        try {
            employeeTempDTO = new EmployeeTempDTO(
                    employeeTempManager.deleteOneRequest(employeeId));
        } catch (SQLException e) {
            employeeTempDTO.setError(e.getLocalizedMessage());
        }
        return employeeTempDTO;
    }

    public EmployeeTempDTO approveRequest(int employeeId,
            EmployeeTempManager employeeTempManager,
            EmployeeManager employeeManager) throws IllegalAccessException,
            InvocationTargetException, NoSuchMethodException, SQLException {
        EmployeeTemp temp = employeeTempManager.getOneRequest(employeeId, true);
        Employee emp = temp.getEmployee();

        BeanMap map = new BeanMap(temp);
        PropertyUtilsBean propUtils = new PropertyUtilsBean();

        for (Object prop : map.keySet()) {
            String propName = (String) prop;
            if (propName.equalsIgnoreCase("employeeID")
                    || propName.equalsIgnoreCase("class")
                    || propName.equalsIgnoreCase("employee")) {
                continue;
            }

            if (propName.equalsIgnoreCase("departmentid")) {
                Object newProp = propUtils.getProperty(temp, propName);

                if (newProp != null) {
                    Integer deptId = (Integer) newProp;

                    if (deptId != emp.getDepartment().getDepartmentID()) {
                        Department department = new Department();
                        department.setDepartmentID(deptId);

                        propUtils.setProperty(emp, "department", department);
                    }
                }
                continue;
            }

            Object oldProp = propUtils.getProperty(emp, propName);
            Object newProp = propUtils.getProperty(temp, propName);

            if (newProp != null && !newProp.equals(oldProp)) {
                propUtils.setProperty(emp, propName, newProp);
            }
        }
        
        EmployeeTempDTO employeeTempDto = new EmployeeTempDTO();
        
        try {
            employeeManager.updateOneEmployee(employeeId, emp);
        } catch (SQLException e) {
            employeeTempDto.setError(e.getLocalizedMessage());
            return employeeTempDto;
        } catch (Exception e) {
            employeeTempDto.setError(ExceptionUtils.getRootCause(e).getLocalizedMessage());
            return employeeTempDto;
        }
        
        employeeTempManager.deleteOneRequest(employeeId);
        return employeeTempDto;
    }
}
