package com.fpt.mic.micweb.model.entity.helper;

import com.fpt.mic.micweb.model.entity.StaffEntity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 7/3/15.
 */
@Entity
@Table(name = "mic_notification", schema = "", catalog = "mic_data")
public class NotificationEntity {
    private int id;
    private String content;
    private String relatedLink;
    private Timestamp createdDate;
    private Timestamp resolvedDate;
    private String resolvedStaff;
    private StaffEntity micStaffByResolvedStaff;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "related_link")
    public String getRelatedLink() {
        return relatedLink;
    }

    public void setRelatedLink(String relatedLink) {
        this.relatedLink = relatedLink;
    }

    @Basic
    @Column(name = "created_date")
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @Column(name = "resolved_date")
    public Timestamp getResolvedDate() {
        return resolvedDate;
    }

    public void setResolvedDate(Timestamp resolvedDate) {
        this.resolvedDate = resolvedDate;
    }

    @Basic
    @Column(name = "resolved_staff")
    public String getResolvedStaff() {
        return resolvedStaff;
    }

    public void setResolvedStaff(String resolvedStaff) {
        this.resolvedStaff = resolvedStaff;
    }

    @ManyToOne
    @JoinColumn(name = "resolved_staff", referencedColumnName = "staff_code", insertable = false, updatable = false)
    public StaffEntity getMicStaffByResolvedStaff() {
        return micStaffByResolvedStaff;
    }

    public void setMicStaffByResolvedStaff(StaffEntity resolvedStaff) {
        this.micStaffByResolvedStaff = resolvedStaff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NotificationEntity that = (NotificationEntity) o;

        if (id != that.id) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (relatedLink != null ? !relatedLink.equals(that.relatedLink) : that.relatedLink != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (resolvedDate != null ? !resolvedDate.equals(that.resolvedDate) : that.resolvedDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (relatedLink != null ? relatedLink.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (resolvedDate != null ? resolvedDate.hashCode() : 0);
        return result;
    }
}
