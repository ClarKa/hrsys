package org.hrsys.facades;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hrsys.dao.EmployeeManager;
import org.hrsys.dao.TrainingManager;
import org.hrsys.dto.TrainingDTO;
import org.hrsys.entity.Training;

public class TrainingFacade {
    public List<TrainingDTO> getOneEmployeeTrainingRecord(int employeeId,
            TrainingManager trainingManager, EmployeeManager employeeManager) {
        List<Training> trainings = trainingManager
                .getOneEmployeeTrainingRecord(employeeId);
        List<TrainingDTO> trainingDtos = new ArrayList<TrainingDTO>();

        for (Training training : trainings) {
            trainingDtos.add(new TrainingDTO(training));
        }

        return trainingDtos;
    }

    public TrainingDTO createTrainingRecordForDate(TrainingDTO trainingDto,
            int employeeId, TrainingManager trainingManager) {
        trainingDto.setEmployeeID(employeeId);
        
        Training training = setTrainingFromTrainingDTO(trainingDto);

        try {
            trainingManager.createTrainingRecordForDate(training);
        } catch (Exception e) {
            trainingDto.setError(
                    ExceptionUtils.getRootCause(e).getLocalizedMessage());
        }
        return trainingDto;
    }

    private Training setTrainingFromTrainingDTO(TrainingDTO trainingDto) {
        Training training = new Training();
        training.setDate(trainingDto.getDate());
        training.setEmployeeID(trainingDto.getEmployeeID());
        training.setApproved(trainingDto.isApproved());
        training.setHour(trainingDto.getHour());

        return training;
    }
}
