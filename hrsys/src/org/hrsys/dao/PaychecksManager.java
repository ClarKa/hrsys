package org.hrsys.dao;

import java.sql.SQLException;

import org.hrsys.entity.Paychecks;
import org.hrsys.enums.PaymentMethod;

public interface PaychecksManager {
    public Paychecks getPaychecksForEmployee(int employeeId);
    public Paychecks updatePaychecksForEmpoyee(int employeeId, PaymentMethod pm) throws SQLException;
}
