package org.hrsys.facades;

import java.util.ArrayList;
import java.util.List;

import org.hrsys.dao.EmployeeManager;
import org.hrsys.dao.TrainingManager;
import org.hrsys.dto.TrainingDTO;
import org.hrsys.entity.Employee;
import org.hrsys.entity.Training;

public class TrainingFacade {
    public List<TrainingDTO> getOneEmployeeTrainingRecord(int employeeId,
            TrainingManager trainingManager, EmployeeManager employeeManager) {
        List<Training> trainings = trainingManager.getOneEmployeeTrainingRecord(employeeId);
        List<TrainingDTO> trainingDtos = new ArrayList<TrainingDTO>();
        
        Employee employee =  employeeManager.getOneEmployee(employeeId);
        
        for (Training training : trainings) {
            trainingDtos.add(new TrainingDTO(training, employee));
        }
        
        return trainingDtos;
    }
}
