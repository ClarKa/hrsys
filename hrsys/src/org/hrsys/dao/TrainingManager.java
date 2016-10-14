package org.hrsys.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.hrsys.entity.Training;

public interface TrainingManager {
    public List<Training> getOneEmployeeTrainingRecord(int employeeId);
    public Training getTrainingRecordForDate(int employeeID, Date date);
    public void createTrainingRecordForDate(Training training) throws SQLException;
    public void updateTrainingRecordForDate(Training training) throws SQLException;
}
