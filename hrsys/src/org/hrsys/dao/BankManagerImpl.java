package org.hrsys.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hrsys.entity.Bank;
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
    public Bank createBankForEmployee(int employeeId, String nickname) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Bank updateBankForEmployee(Bank bank) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Bank deleteBankForEmployee(int employeeId, String nickname)
            throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

}
