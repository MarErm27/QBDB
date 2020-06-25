package com.qbwc.db.entities;

import javax.persistence.*;

@Entity
@Table(name = "ref_number", schema = "qb", catalog = "")
public class RefNumberEntity {
    private String poNumber;
    private String refNumber;

    @Id
    @Column(name = "po_number", nullable = false, length = 80)
    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
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

        RefNumberEntity that = (RefNumberEntity) o;

        if (poNumber != null ? !poNumber.equals(that.poNumber) : that.poNumber != null) return false;
        if (refNumber != null ? !refNumber.equals(that.refNumber) : that.refNumber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = poNumber != null ? poNumber.hashCode() : 0;
        result = 31 * result + (refNumber != null ? refNumber.hashCode() : 0);
        return result;
    }
}
