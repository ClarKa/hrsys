package org.hrsys.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hrsys.entity.Training;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class TrainingManagerImpl implements TrainingManager {
    @PersistenceContext
    private EntityManager mgr;

    @SuppressWarnings("unchecked")
    @Override
    public List<Training> getOneEmployeeTrainingRecord(int employeeId) {
        List<Training> results = new ArrayList<>();
        try {
            Query jpqlQuery = mgr.createQuery("SELECT t FROM Training t WHERE t.employeeID = :employeeID")
                    .setParameter("employeeID", employeeId);
            results = jpqlQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }

    @Override
    public Training getTrainingRecordForDate(int employeeID, Date date) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void createTrainingRecordForDate(Training training) throws SQLException {
        mgr.persist(training);
    }

    @Override
    public void updateTrainingRecordForDate(Training training) throws SQLException {
        // TODO Auto-generated method stub

    }

}
