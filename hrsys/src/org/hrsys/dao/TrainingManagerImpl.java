package org.hrsys.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hrsys.constants.CommonConstants;
import org.hrsys.entity.Training;
import org.hrsys.helpers.TrainingPK;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class TrainingManagerImpl implements TrainingManager {
    @PersistenceContext
    private EntityManager mgr;

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
    public Training getTrainingRecordForDate(int employeeId, Date date) {
        TrainingPK trainingPK = new TrainingPK();
        trainingPK.setEmployeeID(employeeId);
        trainingPK.setDate(date);
        Training result = mgr.find(Training.class, trainingPK);

        return result;

    }

    @Override
    public void createTrainingRecordForDate(Training training) throws SQLException {
        mgr.persist(training);
    }

    @Override
    public void updateTrainingRecordForDate(Training training) throws SQLException {
        TrainingPK trainingPK = new TrainingPK();
        trainingPK.setEmployeeID(training.getEmployeeID());
        trainingPK.setDate(training.getDate());
        Training result = mgr.find(Training.class, trainingPK);
        
        if (result.getApproved()) {
            throw new SQLException(CommonConstants.CANNOT_UPDATE_APPROVED_TRAINING_RECORDS);
        } else {
            result.setHour(training.getHour());
        }
    }
    
    @Override
    public List<Training> approveAllTrainingRecordsForEmployee(int employeeId) {
        List<Training> results = new ArrayList<>();
        Query jpqlQuery = mgr.createQuery("SELECT t FROM Training t WHERE t.employeeID = :employeeID AND t.approved = false")
                .setParameter("employeeID", employeeId);
        results = jpqlQuery.getResultList();
        for (Training training : results ) {
            training.setApproved(true);
        }

        return results;
    }

    @Override
    public Training approveTrainingRecordsForDate(int employeeId, Date date) throws SQLException{
        TrainingPK trainingPK = new TrainingPK();
        trainingPK.setEmployeeID(employeeId);
        trainingPK.setDate(date);
        Training result = mgr.find(Training.class, trainingPK);
        
        if (result.getApproved()) {
            throw new SQLException(CommonConstants.CANNOT_UPDATE_APPROVED_TRAINING_RECORDS);
        } else {
            result.setApproved(true);
        }
        return null;
    }

}
