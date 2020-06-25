package com.qbwc.db.entities;

import javax.persistence.*;

@Entity
@Table(name = "vendor_ref", schema = "qb", catalog = "")
public class VendorRefEntity {
    private String vendorRefName;
    private String vendorRef;

    @Id
    @Column(name = "vendor_ref_name", nullable = false, length = 80)
    public String getVendorRefName() {
        return vendorRefName;
    }

    public void setVendorRefName(String vendorRefName) {
        this.vendorRefName = vendorRefName;
    }

    @Basic
    @Column(name = "vendor_ref", nullable = true, length = -1)
    public String getVendorRef() {
        return vendorRef;
    }

    public void setVendorRef(String vendorRef) {
        this.vendorRef = vendorRef;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VendorRefEntity that = (VendorRefEntity) o;

        if (vendorRefName != null ? !vendorRefName.equals(that.vendorRefName) : that.vendorRefName != null)
            return false;
        if (vendorRef != null ? !vendorRef.equals(that.vendorRef) : that.vendorRef != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = vendorRefName != null ? vendorRefName.hashCode() : 0;
        result = 31 * result + (vendorRef != null ? vendorRef.hashCode() : 0);
        return result;
    }
}
