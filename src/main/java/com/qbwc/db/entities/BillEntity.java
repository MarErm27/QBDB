package com.qbwc.db.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "bill", schema = "qb", catalog = "")
public class BillEntity {
    private int billId;
    private String billNumber;
    private String billData;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @Id
    @Column(name = "bill_id", nullable = false)
    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    @Basic
    @Column(name = "bill_number", nullable = true, length = 128)
    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    @Basic
    @Column(name = "bill_data", nullable = true, length = 4096)
    public String getBillData() {
        return billData;
    }

    public void setBillData(String billData) {
        this.billData = billData;
    }

    @CreationTimestamp
    @Basic
    @Column(name = "created_at", nullable = true, updatable=false)
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @UpdateTimestamp
    @Basic
    @Column(name = "updated_at", nullable = true)
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BillEntity that = (BillEntity) o;

        if (billId != that.billId) return false;
        if (billNumber != null ? !billNumber.equals(that.billNumber) : that.billNumber != null) return false;
        if (billData != null ? !billData.equals(that.billData) : that.billData != null) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(that.updatedAt) : that.updatedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = billId;
        result = 31 * result + (billNumber != null ? billNumber.hashCode() : 0);
        result = 31 * result + (billData != null ? billData.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
    }
}
