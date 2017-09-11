package com.disparter.util;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class PublicId {

    public static final String SEPARADOR_PUBLICID = "-";

    private Long referencia1Id;

    private Long referencia2Id;

    public PublicId() {
        super();
    }

    public PublicId(Long referencia1Id, Long referencia2Id) {
        super();
        this.referencia1Id = referencia1Id;
        this.referencia2Id = referencia2Id;
    }

    public Long getReferencia1Id() {
        return referencia1Id;
    }

    public void setReferencia1Id(Long referencia1Id) {
        this.referencia1Id = referencia1Id;
    }

    public Long getReferencia2Id() {
        return referencia2Id;
    }

    public void setReferencia2Id(Long referencia2Id) {
        this.referencia2Id = referencia2Id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (super.getClass() != obj.getClass()) {
            return false;
        }
        PublicId other = (PublicId) obj;
        return new EqualsBuilder().append(getReferencia2Id(), other.getReferencia2Id())
                .append(getReferencia1Id(), other.getReferencia1Id()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getReferencia2Id()).append(getReferencia1Id()).toHashCode();
    }
}
