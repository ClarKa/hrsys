package org.hrsys.dto;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hrsys.constants.ValidationConstants;
import org.hrsys.entity.Bank;
import org.hrsys.enums.AccountType;

public class BankDTO {
    private int         employeeId;
    private int         accountId;
    @Pattern(regexp = ValidationConstants.WORDS_WITH_NUMBER, message = ValidationConstants.INVALID_BANK_NICKNAME)
    private String      nickname;
    @NotNull(message = ValidationConstants.NULL_ACCOUNT_TYPE)
    private AccountType accountType;
    @Pattern(regexp = ValidationConstants.PURE_NUMBER, message = ValidationConstants.INVALID_ROUTING_NUMBER)
    @Size(max = 9, min = 9, message = ValidationConstants.INVALID_ROUTING_NUMBER)
    private String      routingNumber;
    @Pattern(regexp = ValidationConstants.PURE_NUMBER, message = ValidationConstants.INVALID_ACCOUNT_NUMBER)
    private String      accountNumber;
    @Pattern(regexp = ValidationConstants.PURE_NUMBER, message = ValidationConstants.INVALID_ACCOUNT_NUMBER)
    private String      accountNumberConfirm;
    @Max(value = 100, message = ValidationConstants.INVALID_PERCENT)
    @Min(value = 0, message = ValidationConstants.INVALID_PERCENT)
    private Integer         percent;
    private String      error;

    public BankDTO() {

    }

    public BankDTO(Bank bank) {
        employeeId = bank.getEmployeeId();
        accountId = bank.getAccountId();
        nickname = bank.getNickname();
        accountType = bank.getAccountType();
        routingNumber = bank.getRoutingNumber();
        accountNumber = bank.getAccountNumber();
        percent = bank.getPercent();
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
     * @return the accountId
     */
    public int getAccountId() {
        return accountId;
    }

    /**
     * @param accountId
     *            the accountId to set
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
        this.accountType = AccountType.byId(accountType);
    }

    /**
     * @return the routingNumber
     */
    public String getRoutingNumber() {
        return routingNumber;
    }

    /**
     * @param routingNumber
     *            the routingNumber to set
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
     * @param accountNumber
     *            the accountNumber to set
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * @return the accountNumberConfirm
     */
    public String getAccountNumberConfirm() {
        return accountNumberConfirm;
    }

    /**
     * @param accountNumberConfirm the accountNumberConfirm to set
     */
    public void setAccountNumberConfirm(String accountNumberConfirm) {
        this.accountNumberConfirm = accountNumberConfirm;
    }

    /**
     * @return the percent
     */
    public Integer getPercent() {
        return percent;
    }

    /**
     * @param percent
     *            the percent to set
     */
    public void setPercent(Integer percent) {
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

    @AssertTrue(message = ValidationConstants.UNMATCHED_ACCOUNT_NUMBER)
    private boolean isValid() {
        if (this.accountNumberConfirm == null) {
            return true;
        } else {
            return this.accountNumber.equals(this.accountNumberConfirm);
        }
    }
}
