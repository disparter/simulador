package com.disparter.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class AuditableEntity<K extends Serializable> extends BaseEntity<K> implements Serializable{

    private static final long serialVersionUID = 1L;

    public static final String DELETED_PROPERTY_NAME = "auditDeleted";
    
    @Column(name="st_audit_deleted", nullable=false)
    private Boolean auditDeleted;
    
    @Column(name="str_audit_update_user_name", nullable=false, length=90)
    private String auditLastUpdateUser;
    
    @Column(name="dt_audit_last_update", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date auditLastUpdate;
    
    @Column(name="str_audit_create_user_name",nullable=false, length=90)
    private String auditCreateUser;
    
    @Column(name="dt_audit_created_on", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date auditeCreatedOn;

    public Boolean getAuditDeleted() {
        return auditDeleted;
    }

    public void setAuditDeleted(Boolean auditDeleted) {
        this.auditDeleted = auditDeleted;
    }

    public String getAuditLastUpdateUser() {
        return auditLastUpdateUser;
    }

    public void setAuditLastUpdateUser(String auditLastUpdateUser) {
        this.auditLastUpdateUser = auditLastUpdateUser;
    }

    public Date getAuditLastUpdate() {
        return auditLastUpdate;
    }

    public void setAuditLastUpdate(Date auditLastUpdate) {
        this.auditLastUpdate = auditLastUpdate;
    }

    public String getAuditCreateUser() {
        return auditCreateUser;
    }

    public void setAuditCreateUser(String auditCreateUser) {
        this.auditCreateUser = auditCreateUser;
    }

    public Date getAuditeCreatedOn() {
        return auditeCreatedOn;
    }

    public void setAuditeCreatedOn(Date auditeCreatedOn) {
        this.auditeCreatedOn = auditeCreatedOn;
    }
    
}
