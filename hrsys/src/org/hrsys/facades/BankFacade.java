package org.hrsys.facades;

import java.util.ArrayList;
import java.util.List;

import org.hrsys.dao.BankManager;
import org.hrsys.dto.BankDTO;
import org.hrsys.entity.Bank;

public class BankFacade {
    public List<BankDTO> getEmployeeBanks(int employeeId, BankManager bankManager) {
        List<Bank> banks = bankManager.getEmployeBanks(employeeId);
        List<BankDTO> bankDTOs = new ArrayList<BankDTO>();
        
        for (Bank bank : banks) {
            bankDTOs.add(new BankDTO(bank));
        }
        
        return bankDTOs;
    }
}
