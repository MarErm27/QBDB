package com.qbwc.db.entities;

import javax.persistence.*;

@Entity
@Table(name = "item_ref", schema = "qb", catalog = "")
public class ItemRefEntity {
    private String itemRefName;
    private String itemRef;

    @Id
    @Column(name = "item_ref_name", nullable = false, length = 80)
    public String getItemRefName() {
        return itemRefName;
    }

    public void setItemRefName(String itemRefName) {
        this.itemRefName = itemRefName;
    }

    @Basic
    @Column(name = "item_ref", nullable = true, length = -1)
    public String getItemRef() {
        return itemRef;
    }

    public void setItemRef(String itemRef) {
        this.itemRef = itemRef;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemRefEntity that = (ItemRefEntity) o;

        if (itemRefName != null ? !itemRefName.equals(that.itemRefName) : that.itemRefName != null) return false;
        if (itemRef != null ? !itemRef.equals(that.itemRef) : that.itemRef != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = itemRefName != null ? itemRefName.hashCode() : 0;
        result = 31 * result + (itemRef != null ? itemRef.hashCode() : 0);
        return result;
    }
}
