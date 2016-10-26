package org.hrsys.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hrsys.entity.Bank;
import org.hrsys.helpers.BankPK;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class BankManagerImpl implements BankManager {
    @PersistenceContext
    private EntityManager mgr;
    
    @Override
    public List<Bank> getEmployeBanks(int employeeId) {
        List<Bank> results = new ArrayList<>();
        Query jpqlQuery = mgr
                .createQuery(
                        "SELECT b FROM Bank b WHERE b.employeeId = :employeeId")
                .setParameter("employeeId", employeeId);
        results = jpqlQuery.getResultList();

        return results;
    }

    @Override
    public void createBankForEmployee(Bank bank) {
        mgr.persist(bank);
    }

    @Override
    public void updateBankForEmployee(Bank bank) throws SQLException {
        mgr.merge(bank);
    }

    @Override
    public void deleteBankForEmployee(BankPK pk)
            throws SQLException {
        Bank bank = mgr.find(Bank.class, pk);
        mgr.remove(bank);
    }

    @Override
    public Bank getOneBankForEmployee(BankPK pk) {
        Bank bank = mgr.find(Bank.class, pk);
        return bank;
    }

}
