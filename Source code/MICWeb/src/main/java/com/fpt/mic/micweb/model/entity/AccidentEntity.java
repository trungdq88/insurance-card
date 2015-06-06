package com.fpt.mic.micweb.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/5/15.
 */
@Entity
@Table(name = "mic_accident", schema = "", catalog = "mic_data")
public class AccidentEntity {
    private int id;
    private String title;
    private String attachment;
    private Timestamp createdDate;
    private String contractCode;
    private ContractEntity micContractByContractCode;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "attachment")
    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
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
    @Column(name = "contract_code")
    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccidentEntity that = (AccidentEntity) o;

        if (id != that.id) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (attachment != null ? !attachment.equals(that.attachment) : that.attachment != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (contractCode != null ? !contractCode.equals(that.contractCode) : that.contractCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (attachment != null ? attachment.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (contractCode != null ? contractCode.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "contract_code", referencedColumnName = "contract_code", nullable = false, insertable = false, updatable = false)
    public ContractEntity getMicContractByContractCode() {
        return micContractByContractCode;
    }

    public void setMicContractByContractCode(ContractEntity micContractByContractCode) {
        this.micContractByContractCode = micContractByContractCode;
    }
}
