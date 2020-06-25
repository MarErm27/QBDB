package com.qbwc.db.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "inventory_adjustments", schema = "qb", catalog = "")
public class InventoryAdjustmentsEntity {
    private String inventoryAdjustments;

    @Id
    @Column(name = "inventory_adjustments", nullable = false, length = 128)
    public String getInventoryAdjustments() {
        return inventoryAdjustments;
    }

    public void setInventoryAdjustments(String inventoryAdjustments) {
        this.inventoryAdjustments = inventoryAdjustments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InventoryAdjustmentsEntity that = (InventoryAdjustmentsEntity) o;

        if (inventoryAdjustments != null ? !inventoryAdjustments.equals(that.inventoryAdjustments) : that.inventoryAdjustments != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return inventoryAdjustments != null ? inventoryAdjustments.hashCode() : 0;
    }
}
