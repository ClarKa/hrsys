package org.hrsys.webservices;

import java.util.List;

import org.hrsys.constants.ServicePaths;
import org.hrsys.dao.EmployeeManager;
import org.hrsys.dao.TrainingManager;
import org.hrsys.dto.TrainingDTO;
import org.hrsys.facades.TrainingFacade;
import org.hrsys.helpers.MetaAnnotations.EmployeeIdMatchOrIsAdmin;
import org.springframework.beans.factory.annotation.Autowired;
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
            @PathVariable("employeeid") int employeeId) {
        return trainingFacade.getOneEmployeeTrainingRecord(employeeId,
                trainingManager, employeeManager);
    }
}
