package org.hrsys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hrsys.enums.PaymentMethod;

@Entity
@Table(name = "paychecks")
public class Paychecks {
    @Id
    @Column(name = "pc_employee_id", unique = true, nullable = false, insertable = true, updatable = true)
    private int employeeId;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "pc_payment_method", unique = false, nullable = false, insertable = true, updatable = true)
    private PaymentMethod paymentMethod;

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
}
