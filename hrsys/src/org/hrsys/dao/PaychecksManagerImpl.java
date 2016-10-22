package org.hrsys.dao;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hrsys.entity.Paychecks;
import org.hrsys.enums.PaymentMethod;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PaychecksManagerImpl implements PaychecksManager {
    @PersistenceContext
    private EntityManager mgr;

    @Override
    public Paychecks getPaychecksForEmployee(int employeeId) {
        Paychecks paychecks = mgr.find(Paychecks.class, employeeId);
        return paychecks;
    }

    @Override
    public Paychecks updatePaychecksForEmpoyee(int employeeId, PaymentMethod pm)
            throws SQLException {
        Paychecks paychecks = mgr.find(Paychecks.class, employeeId);
        paychecks.setPaymentMethod(pm);
        return paychecks;
    }

}
