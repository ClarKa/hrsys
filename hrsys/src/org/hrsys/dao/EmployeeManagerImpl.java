package org.hrsys.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hrsys.entity.*;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class EmployeeManagerImpl implements EmployeeManager {
    @PersistenceContext
    private EntityManager mgr;

    @Override
    public List<Employee> getAllEmployee() {
        Query jpqlQuery = mgr.createQuery(
                "select e from Employee e LEFT JOIN FETCH e.department");
        List<Employee> results = jpqlQuery.getResultList();
        return results;
    }

    @Override
    public List<Employee> getAvailableEmployee() {
        Query jpqlQuery = mgr.createQuery(
                "select e from Employee e where e.department is null");
        List<Employee> results = jpqlQuery.getResultList();

        return results;
    }

    @Override
    public void createEmployee(Employee employee) throws SQLException {
        mgr.persist(employee);
    }

    @Override
    public Employee getOneEmployee(int employeeID) {
        Employee employee = mgr.find(Employee.class, employeeID);
        return employee;
    }

    @Override
    public Employee deleteOneEmployee(int employeeID) throws SQLException {
        Employee employee = mgr.find(Employee.class, employeeID);

        if (employee != null) {
            mgr.remove(employee);
            return employee;
        } else {
            throw new SQLException(
                    "No record found for employee id " + employeeID);
        }
    }

    @Override
    public void updateOneEmployee(int employeeID, Employee employee)
            throws SQLException {
        Employee currentEmployee = mgr.find(Employee.class, employeeID);

        if (currentEmployee != null) {
            mgr.merge(employee);
        } else {
            throw new SQLException(
                    "No record found for employee id " + employeeID);
        }
    }

    @Override
    public List<Employee> getFiltered(Map<String, Object> filters) {
        CriteriaBuilder cb = mgr.getCriteriaBuilder();
        CriteriaQuery<Employee> cQuery = cb.createQuery(Employee.class);
        Root<Employee> emp = cQuery.from(Employee.class);
        Join<Employee, CustomUser> user = emp.join("user", JoinType.LEFT);
        cQuery.select(emp);

        for (Entry<String, Object> entry : filters.entrySet()) {
            if (entry.getKey().equals("hasAccount")) {
                if (entry.getValue().equals("false")) {
                    cQuery.where(user.isNull());
                } else {
                    cQuery.where(user.isNotNull());
                }
            } else {
                cQuery.where(cb.equal(emp.get(entry.getKey()), entry.getValue()));
            }
        }

        TypedQuery<Employee> tq = mgr.createQuery(cQuery);
        List<Employee> results = tq.getResultList();
        return results;
    }
}