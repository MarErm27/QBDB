package com.qbwc.db.entities;

import javax.persistence.*;

@Entity
@Table(name = "sales_receipt", schema = "qb", catalog = "")
public class SalesReceiptEntity {
    private String memo;
    private String refNumber;

    @Id
    @Column(name = "memo", nullable = false, length = 80)
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Basic
    @Column(name = "ref_number", nullable = true, length = 128)
    public String getRefNumber() {
        return refNumber;
    }

    public void setRefNumber(String refNumber) {
        this.refNumber = refNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SalesReceiptEntity that = (SalesReceiptEntity) o;

        if (memo != null ? !memo.equals(that.memo) : that.memo != null) return false;
        if (refNumber != null ? !refNumber.equals(that.refNumber) : that.refNumber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = memo != null ? memo.hashCode() : 0;
        result = 31 * result + (refNumber != null ? refNumber.hashCode() : 0);
        return result;
    }
}
