package com.qbwc.db.entities;

import javax.persistence.*;

@Entity
@Table(name = "account_ref", schema = "qb", catalog = "")
public class AccountRefEntity {
    private String fullName;
    private String listId;

    @Id
    @Column(name = "full_name", nullable = false, length = 80)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

        AccountRefEntity that = (AccountRefEntity) o;

        if (fullName != null ? !fullName.equals(that.fullName) : that.fullName != null) return false;
        if (listId != null ? !listId.equals(that.listId) : that.listId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fullName != null ? fullName.hashCode() : 0;
        result = 31 * result + (listId != null ? listId.hashCode() : 0);
        return result;
    }
}
