package org.hrsys.helpers;

import java.io.Serializable;

public class BankPK implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer employeeId;
    private Integer accountId;

    public BankPK() {
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BankPK) {
            BankPK bankPK = (BankPK) obj;

            if (!bankPK.getEmployeeId().equals(employeeId)) {
                return false;
            }

            if (!bankPK.getAccountId().equals(accountId)) {
                return false;
            }

            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return employeeId.hashCode() + accountId.hashCode();
    }

    /**
     * @return the employeeId
     */
    public Integer getEmployeeId() {
        return employeeId;
    }

    /**
     * @param employeeId
     *            the employeeID to set
     */
    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * @return the accountId
     */
    public Integer getAccountId() {
        return accountId;
    }

    /**
     * @param accountId the accountId to set
     */
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
}
