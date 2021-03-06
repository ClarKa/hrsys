package org.hrsys.facades;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hrsys.dao.TrainingManager;
import org.hrsys.dto.TrainingDTO;
import org.hrsys.entity.Training;

public class TrainingFacade {
    public List<TrainingDTO> getOneEmployeeTrainingRecord(int employeeId,
            TrainingManager trainingManager, Boolean approved) {
        if (approved == null) {
            List<Training> trainings = trainingManager
                    .getOneEmployeeTrainingRecord(employeeId);
            List<TrainingDTO> trainingDtos = new ArrayList<TrainingDTO>();

            for (Training training : trainings) {
                trainingDtos.add(new TrainingDTO(training));
            }

            return trainingDtos;
        } else {
            List<Training> trainings = trainingManager
                    .getOneEmployeeTrainingRecordByApproved(employeeId,
                            approved);
            List<TrainingDTO> trainingDtos = new ArrayList<TrainingDTO>();

            for (Training training : trainings) {
                trainingDtos.add(new TrainingDTO(training));
            }

            return trainingDtos;
        }

    }

    public TrainingDTO handleTrainingRecordForDate(TrainingDTO trainingDto,
            int employeeId, Date date, TrainingManager trainingManager) {
        trainingDto.setEmployeeID(employeeId);

        if (trainingManager.getTrainingRecordForDate(employeeId,
                date) == null) {
            trainingDto = createTrainingRecordForDate(trainingDto,
                    trainingManager);
        } else {
            trainingDto = updateTrainingRecordForDate(trainingDto,
                    trainingManager);
        }
        return trainingDto;
    }

    public TrainingDTO createTrainingRecordForDate(TrainingDTO trainingDto,
            TrainingManager trainingManager) {
        Training training = setTrainingFromTrainingDTO(trainingDto);

        try {
            trainingManager.createTrainingRecordForDate(training);
        } catch (Exception e) {
            trainingDto.setError(e.getMessage());
            return trainingDto;
        }

        return trainingDto;
    }

    public TrainingDTO updateTrainingRecordForDate(TrainingDTO trainingDto,
            TrainingManager trainingManager) {
        Training training = setTrainingFromTrainingDTO(trainingDto);

        try {
            trainingManager.updateTrainingRecordForDate(training);
        } catch (Exception e) {
            trainingDto.setError(e.getMessage());
            return trainingDto;
        }

        return trainingDto;
    }

    public List<TrainingDTO> approveAllTrainingRecordForEmployee(int employeeId,
            TrainingManager trainingManager) {
        List<TrainingDTO> trainingDtos = new ArrayList<TrainingDTO>();
        try {
            List<Training> results = trainingManager
                    .approveAllTrainingRecordsForEmployee(employeeId);
            for (Training result : results) {
                trainingDtos.add(new TrainingDTO(result));
            }
        } catch (Exception e) {
            return null;
        }

        return trainingDtos;
    }

    public List<TrainingDTO> approveTrainingRecordForDate(int employeeId,
            Date date, TrainingManager trainingManager) {
        List<TrainingDTO> trainingDtos = new ArrayList<TrainingDTO>();

        try {
            Training result = trainingManager
                    .approveTrainingRecordsForDate(employeeId, date);
            trainingDtos.add(new TrainingDTO(result));
        } catch (Exception e) {
            return null;
        }

        return trainingDtos;
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
