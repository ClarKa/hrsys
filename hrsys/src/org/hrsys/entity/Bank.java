package org.hrsys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.hrsys.enums.AccountType;
import org.hrsys.helpers.BankPK;

@Entity
@Table(name = "bank")
@IdClass(value = BankPK.class)
public class Bank {
    @Id
    @Column(name = "bk_employee_id", unique = true, nullable = false, insertable = true, updatable = true)
    private int employeeId;
    
    @Id
    @Column(name = "bk_nickname", unique = true, nullable = false, insertable = true, updatable = true)
    private String nickname;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "bk_account_type", unique = false, nullable = false, insertable = true, updatable = true)
    private AccountType accountType;
    
    @Column(name = "bk_routing_number", unique = false, nullable = false, insertable = true, updatable = true)
    private int routingNumber;
    
    @Column(name = "bk_account_number", unique = false, nullable = false, insertable = true, updatable = true)
    private int accountNumber;
    
    @Column(name = "bk_percent", unique = false, nullable = true, insertable = true, updatable = true)
    private int percent;

    /**
     * @return the employeeId
     */
    public int getEmployeeId() {
        return employeeId;
    }

    /**
     * @param employeeId the employeeId to set
     */
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname the nickname to set
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return the accountType
     */
    public AccountType getAccountType() {
        return accountType;
    }

    /**
     * @param accountType the accountType to set
     */
    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    /**
     * @return the routingNumber
     */
    public int getRoutingNumber() {
        return routingNumber;
    }

    /**
     * @param routingNumber the routingNumber to set
     */
    public void setRoutingNumber(int routingNumber) {
        this.routingNumber = routingNumber;
    }

    /**
     * @return the accountNumber
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * @param accountNumber the accountNumber to set
     */
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * @return the percent
     */
    public int getPercent() {
        return percent;
    }

    /**
     * @param percent the percent to set
     */
    public void setPercent(int percent) {
        this.percent = percent;
    }
}
