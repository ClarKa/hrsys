package org.hrsys.dao;

import java.sql.SQLException;
import java.util.List;

import org.hrsys.entity.Bank;

public interface BankManager {
    public List<Bank> getEmployeBanks(int employeeId);
    public Bank createBankForEmployee(int employeeId, String nickname);
    public Bank updateBankForEmployee(Bank bank) throws SQLException;
    public Bank deleteBankForEmployee(int employeeId, String nickname) throws SQLException;
}
