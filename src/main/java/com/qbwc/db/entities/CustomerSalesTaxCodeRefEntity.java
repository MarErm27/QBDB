package com.qbwc.db.entities;

import javax.persistence.*;

@Entity
@Table(name = "customer_sales_tax_code_ref", schema = "qb", catalog = "")
public class CustomerSalesTaxCodeRefEntity {
    private String customerName;
    private String listId;

    @Id
    @Column(name = "customer_name", nullable = false, length = 80)
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Basic
    @Column(name = "list_id", nullable = true, length = 128)
    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerSalesTaxCodeRefEntity that = (CustomerSalesTaxCodeRefEntity) o;

        if (customerName != null ? !customerName.equals(that.customerName) : that.customerName != null) return false;
        if (listId != null ? !listId.equals(that.listId) : that.listId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = customerName != null ? customerName.hashCode() : 0;
        result = 31 * result + (listId != null ? listId.hashCode() : 0);
        return result;
    }
}
