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
    @Column(name = "bk_id", unique = true, nullable = false, insertable = true, updatable = true)
    private int accountId;
    
    @Column(name = "bk_nickname", unique = true, nullable = false, insertable = true, updatable = true)
    private String nickname;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "bk_account_type", unique = false, nullable = false, insertable = true, updatable = true)
    private AccountType accountType;
    
    @Column(name = "bk_routing_number", unique = false, nullable = false, insertable = true, updatable = true)
    private String routingNumber;
    
    @Column(name = "bk_account_number", unique = false, nullable = false, insertable = true, updatable = true)
    private String accountNumber;
    
    @Column(name = "bk_percent", unique = false, nullable = true, insertable = true, updatable = true)
    private Integer percent;

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
     * @return the accountId
     */
    public int getAccountId() {
        return accountId;
    }

    /**
     * @param accountId the accountId to set
     */
    public void setAccountId(int accountId) {
        this.accountId = accountId;
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
    public String getRoutingNumber() {
        return routingNumber;
    }

    /**
     * @param routingNumber the routingNumber to set
     */
    public void setRoutingNumber(String routingNumber) {
        this.routingNumber = routingNumber;
    }

    /**
     * @return the accountNumber
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * @param accountNumber the accountNumber to set
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * @return the percent
     */
    public Integer getPercent() {
        return percent;
    }

    /**
     * @param percent the percent to set
     */
    public void setPercent(Integer percent) {
        this.percent = percent;
    }
}
