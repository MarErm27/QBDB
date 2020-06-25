package com.qbwc.db.entities;

import javax.persistence.*;

@Entity
@Table(name = "customer_ref", schema = "qb", catalog = "")
public class CustomerRefEntity {
    private String customerRefName;
    private String customerRef;

    @Id
    @Column(name = "customer_ref_name", nullable = false, length = 80)
    public String getCustomerRefName() {
        return customerRefName;
    }

    public void setCustomerRefName(String customerRefName) {
        this.customerRefName = customerRefName;
    }

    @Basic
    @Column(name = "customer_ref", nullable = true, length = -1)
    public String getCustomerRef() {
        return customerRef;
    }

    public void setCustomerRef(String customerRef) {
        this.customerRef = customerRef;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerRefEntity that = (CustomerRefEntity) o;

        if (customerRefName != null ? !customerRefName.equals(that.customerRefName) : that.customerRefName != null)
            return false;
        if (customerRef != null ? !customerRef.equals(that.customerRef) : that.customerRef != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = customerRefName != null ? customerRefName.hashCode() : 0;
        result = 31 * result + (customerRef != null ? customerRef.hashCode() : 0);
        return result;
    }
}
