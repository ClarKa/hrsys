package org.hrsys.dao;

import java.sql.SQLException;
import java.util.List;

import org.hrsys.entity.Bank;

public interface BankManager {
    public List<Bank> getEmployeBanks(int employeeId);
    public void createBankForEmployee(Bank bank) throws SQLException;
    public void updateBankForEmployee(Bank bank) throws SQLException;
    public void deleteBankForEmployee(int employeeId, String nickname) throws SQLException;
}
