package org.hrsys.dto;

import org.hrsys.entity.Paychecks;
import org.hrsys.enums.PaymentMethod;

public class PaychecksDTO {
    private int employeeId;
    private PaymentMethod paymentMethod;
    private String error;
    
    public PaychecksDTO(Paychecks paychecks) {
        employeeId = paychecks.getEmployeeId();
        paymentMethod = paychecks.getPaymentMethod();
    }
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
     * @return the paymentMethod
     */
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
    /**
     * @param paymentMethod the paymentMethod to set
     */
    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    /**
     * @return the error
     */
    public String getError() {
        return error;
    }
    /**
     * @param error the error to set
     */
    public void setError(String error) {
        this.error = error;
    }
}
