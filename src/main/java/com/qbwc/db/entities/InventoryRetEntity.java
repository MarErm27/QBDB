package com.qbwc.db.entities;

import javax.persistence.*;

@Entity
@Table(name = "inventory_ret", schema = "qb", catalog = "")
public class InventoryRetEntity {
    private String inventoryRetName;
    private String inventoryRet;

    @Id
    @Column(name = "inventory_ret_name", nullable = false, length = 80)
    public String getInventoryRetName() {
        return inventoryRetName;
    }

    public void setInventoryRetName(String inventoryRetName) {
        this.inventoryRetName = inventoryRetName;
    }

    @Basic
    @Column(name = "inventory_ret", nullable = true, length = -1)
    public String getInventoryRet() {
        return inventoryRet;
    }

    public void setInventoryRet(String inventoryRet) {
        this.inventoryRet = inventoryRet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InventoryRetEntity that = (InventoryRetEntity) o;

        if (inventoryRetName != null ? !inventoryRetName.equals(that.inventoryRetName) : that.inventoryRetName != null)
            return false;
        if (inventoryRet != null ? !inventoryRet.equals(that.inventoryRet) : that.inventoryRet != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = inventoryRetName != null ? inventoryRetName.hashCode() : 0;
        result = 31 * result + (inventoryRet != null ? inventoryRet.hashCode() : 0);
        return result;
    }
}
