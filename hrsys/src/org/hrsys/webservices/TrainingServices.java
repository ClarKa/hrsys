package org.hrsys.webservices;

import java.sql.Date;
import java.util.List;

import javax.validation.Valid;

import org.hrsys.constants.ServicePaths;
import org.hrsys.dao.EmployeeManager;
import org.hrsys.dao.TrainingManager;
import org.hrsys.dto.TrainingDTO;
import org.hrsys.facades.TrainingFacade;
import org.hrsys.helpers.MetaAnnotations.EmployeeIdMatch;
import org.hrsys.helpers.MetaAnnotations.EmployeeIdMatchOrIsAdmin;
import org.hrsys.helpers.MetaAnnotations.IsAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = ServicePaths.GET_TRAINING_RECORD_PATH)
public class TrainingServices {
    @Autowired
    TrainingManager trainingManager;

    @Autowired
    EmployeeManager employeeManager;

    private TrainingFacade trainingFacade = new TrainingFacade();

    @RequestMapping(value = ServicePaths.GET_ONE_EMPLOYEE_PATH
            + "/{employeeid}", method = RequestMethod.GET, produces = "application/json")
    @EmployeeIdMatchOrIsAdmin
    public List<TrainingDTO> getOneEmployeeTrainingRecord(
            @PathVariable("employeeid") int employeeID, Boolean approved) {
        return trainingFacade.getOneEmployeeTrainingRecord(employeeID,
                trainingManager, employeeManager, approved);
    }

    @RequestMapping(value = ServicePaths.GET_ONE_EMPLOYEE_PATH
            + "/{employeeid}" + "/{recorddate}", method = RequestMethod.POST, produces = "application/json")
    @EmployeeIdMatch
    public TrainingDTO handleTrainingRecordForDate(@Valid TrainingDTO trainingDto,
            BindingResult result, @PathVariable("employeeid") int employeeID,  @PathVariable("recorddate") Date recordDate) {
        if (result.hasErrors()) {
            trainingDto.setError(result.getAllErrors().get(0).getDefaultMessage());
            return trainingDto;
        }
        return trainingFacade.handleTrainingRecordForDate(trainingDto,
                employeeID, recordDate, trainingManager);
    }
    
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @IsAdmin
    public List<TrainingDTO> approveTrainingRecordForEmployee(int employeeId, Date date) {
        if (date == null) {
            return trainingFacade.approveAllTrainingRecordForEmployee(employeeId, trainingManager);
        } else {
            return trainingFacade.approveTrainingRecordForDate(employeeId, date, trainingManager);
        }

    }
}
