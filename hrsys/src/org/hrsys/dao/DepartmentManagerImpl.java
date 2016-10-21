package org.hrsys.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hrsys.entity.*;

@Repository
@Transactional
public class DepartmentManagerImpl implements DepartmentManager {
    @PersistenceContext
    private EntityManager mgr;

    @SuppressWarnings("unchecked")
    @Override
    public List<Department> getAllDepartment() {
        List<Department> results = new ArrayList<>();
        try {
            Query jpqlQuery = mgr.createQuery("SELECT d FROM Department d");
            results = jpqlQuery.getResultList();
//          org.hibernate.Query hquery = jpqlQuery.unwrap(org.hibernate.Query.class);
//          hquery.setCacheable(true);
        } catch(Exception e) {
            e.printStackTrace();
        }
//        List<Department> results = hquery.list();

        return results;
    }

    @Override
    public List<Employee> getEmployeeForOneDepartment(int deptId) throws SQLException {
        Department dept = mgr.find(Department.class, deptId);
        dept.getDepartmentEmployees().size();

        List<Employee> emps = dept.getDepartmentEmployees();

        return emps;
    }

    @Override
    public void createDepartment(Department dept, String[] empIdList) throws SQLException {
        mgr.persist(dept);

        if (empIdList != null) {
            for (String i : empIdList) {
                Employee emp = mgr.find(Employee.class, Integer.parseInt(i));
                emp.setDepartment(dept);
            }
        }
    }
    
    @Override
    public void deleteDepartment(Department department) throws SQLException {
    };
    
    @Override
    public void updateDepartment(Department department) throws SQLException {
    };
}
