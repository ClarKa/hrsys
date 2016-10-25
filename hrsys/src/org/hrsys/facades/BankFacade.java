package org.hrsys.facades;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
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
    
    public BankDTO createBankForEmployee(int employeeId, BankDTO bankDTO, BankManager bankManager) {
        bankDTO.setEmployeeId(employeeId);
        Bank bank = setBankFromBankDTO(bankDTO);
        try {
            bankManager.createBankForEmployee(bank);
        } catch(Exception e) {
            bankDTO.setError(ExceptionUtils.getRootCause(e).getLocalizedMessage());
            return bankDTO;
        }
        
        return bankDTO;
    }
    
    private Bank setBankFromBankDTO(BankDTO bankDTO) {
        Bank bank = new Bank();
        bank.setAccountId(bankDTO.getAccountId());
        bank.setAccountNumber(bankDTO.getAccountNumber());
        bank.setAccountType(bankDTO.getAccountType());
        bank.setEmployeeId(bankDTO.getEmployeeId());
        bank.setNickname(bankDTO.getNickname());
        bank.setPercent(bankDTO.getPercent());
        bank.setRoutingNumber(bankDTO.getRoutingNumber());
        return bank;
    }
}
