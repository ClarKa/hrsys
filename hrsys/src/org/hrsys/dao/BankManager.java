package org.hrsys.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hrsys.entity.Bank;
import org.hrsys.helpers.BankPK;

public interface BankManager {
    public List<Bank> getEmployeBanks(int employeeId);
    public Bank getOneBankForEmployee(BankPK pk);
    public void createBankForEmployee(Bank bank) throws SQLException;
    public void updateBankForEmployee(Bank bank) throws SQLException;
    public void deleteBankForEmployee(BankPK pk) throws SQLException;
    public void updateDistributionForEmployee(Map<String, String> map, List<Integer> list, int employeeId) throws SQLException;
}
