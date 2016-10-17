package org.hrsys.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.hrsys.entity.Training;

public interface TrainingManager {
    public List<Training> getOneEmployeeTrainingRecord(int employeeId);
    public List<Training> getOneEmployeeTrainingRecordByApproved(int employeeId, boolean approved);
    public Training getTrainingRecordForDate(int employeeId, Date date);
    public void createTrainingRecordForDate(Training training) throws SQLException;
    public void updateTrainingRecordForDate(Training training) throws SQLException;
    public List<Training> approveAllTrainingRecordsForEmployee(int employeeId);
    public Training approveTrainingRecordsForDate(int employeeId, Date date) throws SQLException;
}
