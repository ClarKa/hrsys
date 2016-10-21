package org.hrsys.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hrsys.constants.ValidationConstants;
import org.hrsys.entity.Bank;
import org.hrsys.enums.AccountType;

public class BankDTO {
    private int         employeeId;
    @Pattern(regexp = ValidationConstants.PURE_WORD, message = ValidationConstants.INVALID_BANK_NICKNAME)
    private String      nickname;
    private AccountType accountType;
    private int         routingNumber;
    private int         accountNumber;
    @Max(value = 100, message = ValidationConstants.INVALID_PERCENT)
    @Min(value = 0, message = ValidationConstants.INVALID_PERCENT)
    private int         percent;
    private String      error;

    public BankDTO() {

    }

    public BankDTO(Bank bank) {
        employeeId = bank.getEmployeeId();
        nickname = bank.getNickname();
        accountType = bank.getAccountType();
        routingNumber = bank.getRoutingNumber();
        accountNumber = bank.getAccountNumber();
    }

    /**
     * @return the employeeId
     */
    public int getEmployeeId() {
        return employeeId;
    }

    /**
     * @param employeeId
     *            the employeeId to set
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
     * @param nickname
     *            the nickname to set
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
     * @param accountType
     *            the accountType to set
     */
    public void setAccountType(char accountType) {
        this.accountType = AccountType.getAccountTypeById(accountType);
    }

    /**
     * @return the routingNumber
     */
    public int getRoutingNumber() {
        return routingNumber;
    }

    /**
     * @param routingNumber
     *            the routingNumber to set
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
     * @param accountNumber
     *            the accountNumber to set
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
     * @param percent
     *            the percent to set
     */
    public void setPercent(int percent) {
        this.percent = percent;
    }

    /**
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * @param error
     *            the error to set
     */
    public void setError(String error) {
        this.error = error;
    }
}
