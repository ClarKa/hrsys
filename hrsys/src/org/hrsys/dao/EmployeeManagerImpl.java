/*
* @Author: kaifu
* @Date:   2016-03-16 14:28:56
* @Last Modified by:   kaifu
* @Last Modified time: 2016-03-24 14:53:30
*/
package org.hrsys.dao;

import java.util.List;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hrsys.dto.EmployeeDTO;
import org.hrsys.entity.*;

@Repository
@Transactional
public class EmployeeManagerImpl implements EmployeeManager {
    @PersistenceContext
    private EntityManager mgr;

    @Override
    public List<Employee> getAllEmployee() {
        Query jpqlQuery = mgr.createQuery("select e from Employee e LEFT JOIN FETCH e.department");
        List<Employee> results = jpqlQuery.getResultList();

        return results;
    }

    @Override
    public List<Employee> getAvailableEmployee() {
        Query jpqlQuery = mgr.createQuery("select e from Employee e where e.department is null");
        List<Employee> results = jpqlQuery.getResultList();

        return results;
    }

    @Override
    public void createEmployee(Employee emp) {
        mgr.persist(emp);
    }
    
    @Override
    public EmployeeDTO getOneEmployee(int employeeID) {
        Employee employee = mgr.find(Employee.class, employeeID);
        EmployeeDTO employeeDto = new EmployeeDTO(employee);
        return employeeDto;
    }
}