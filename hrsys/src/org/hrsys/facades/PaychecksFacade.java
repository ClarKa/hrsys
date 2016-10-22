package org.hrsys.facades;

import org.hrsys.dao.PaychecksManager;
import org.hrsys.dto.PaychecksDTO;

public class PaychecksFacade {
    public PaychecksDTO getPaychecksForEmployee(int employeeId, PaychecksManager paychecksManager) {
        PaychecksDTO paychecksDTO = new PaychecksDTO(paychecksManager.getPaychecksForEmployee(employeeId));
        return paychecksDTO;
    }
}
