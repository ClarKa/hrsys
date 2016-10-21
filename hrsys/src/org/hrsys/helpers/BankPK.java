package org.hrsys.helpers;

import java.io.Serializable;

public class BankPK implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer employeeId;
    private String nickname;

    public BankPK() {
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BankPK) {
            BankPK bankPK = (BankPK) obj;

            if (!bankPK.getEmployeeId().equals(employeeId)) {
                return false;
            }

            if (!bankPK.getNickname().equals(nickname)) {
                return false;
            }

            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return employeeId.hashCode() + nickname.hashCode();
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
}
