package org.hrsys.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hrsys.entity.Employee;
import org.hrsys.entity.EmployeeTemp;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository(value = "employeeTemp")
@Transactional
@SuppressWarnings("unchecked")
public class EmployeeTempManagerImpl implements EmployeeTempManager {
    @PersistenceContext
    private EntityManager mgr;

    @Override
    public EmployeeTemp getOneRequest(int employeeID, boolean fetchEmployee) {
        EmployeeTemp temp = mgr.find(EmployeeTemp.class, employeeID);
        if (fetchEmployee) {
            temp.getEmployee().getEmployeeID();
        }
        
        return temp;
    }

    @Override
    public EmployeeTemp deleteOneRequest(int employeeID) throws SQLException {
        EmployeeTemp employee = mgr.find(EmployeeTemp.class, employeeID);
        if (employee != null) {
            Query jpqlQuery = mgr
                    .createQuery(
                            "DELETE from EmployeeTemp e WHERE e.employeeID = :employeeId")
                    .setParameter("employeeId", employeeID);
            jpqlQuery.executeUpdate();

            return employee;
        } else {
            throw new SQLException(
                    "No prending request found for employee id " + employeeID);
        }
    }

    @Override
    public void updateOneRequest(int employeeID, Employee employee)
            throws SQLException {
        EmployeeTemp currentEmployee = mgr.find(EmployeeTemp.class, employeeID);
        
        if (currentEmployee != null) {
            mgr.merge(employee);
        } else {
            throw new SQLException(
                    "No prending request found for employee id " + employeeID);
        }
    }

    @Override
    public List<EmployeeTemp> getAllTempEmployee() {
        Query jpqlQuery = mgr.createQuery(
                "select e from EmployeeTemp e LEFT JOIN FETCH e.department");
        List<EmployeeTemp> results = jpqlQuery.getResultList();
        return results;
    }

    @Override
    public void createTempEmployee(EmployeeTemp employee) throws SQLException {
        mgr.persist(employee);
    }
}
