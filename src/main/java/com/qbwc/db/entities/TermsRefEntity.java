package com.qbwc.db.entities;

import javax.persistence.*;

@Entity
@Table(name = "terms_ref", schema = "qb", catalog = "")
public class TermsRefEntity {
    private String refName;
    private String ref;

    @Id
    @Column(name = "ref_name", nullable = false, length = 80)
    public String getRefName() {
        return refName;
    }

    public void setRefName(String refName) {
        this.refName = refName;
    }

    @Basic
    @Column(name = "ref", nullable = true, length = -1)
    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TermsRefEntity that = (TermsRefEntity) o;

        if (refName != null ? !refName.equals(that.refName) : that.refName != null) return false;
        if (ref != null ? !ref.equals(that.ref) : that.ref != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = refName != null ? refName.hashCode() : 0;
        result = 31 * result + (ref != null ? ref.hashCode() : 0);
        return result;
    }
}
