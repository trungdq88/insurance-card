package com.fpt.mic.micweb.model.entity;

import javax.persistence.*;

/**
 * Created by dinhquangtrung on 6/2/15.
 */
@Entity
@Table(name = "mic_new_card_request", schema = "", catalog = "mic_data")
public class NewCardRequestEntity {
    private int id;
    private int requestDate;
    private int resolveDate;
    private String note;
    private String oldCardId;
    private String customerCode;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "request_date")
    public int getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(int requestDate) {
        this.requestDate = requestDate;
    }

    @Basic
    @Column(name = "resolve_date")
    public int getResolveDate() {
        return resolveDate;
    }

    public void setResolveDate(int resolveDate) {
        this.resolveDate = resolveDate;
    }

    @Basic
    @Column(name = "note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Basic
    @Column(name = "old_card_id")
    public String getOldCardId() {
        return oldCardId;
    }

    public void setOldCardId(String oldCardId) {
        this.oldCardId = oldCardId;
    }

    @Basic
    @Column(name = "customer_code")
    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewCardRequestEntity that = (NewCardRequestEntity) o;

        if (id != that.id) return false;
        if (requestDate != that.requestDate) return false;
        if (resolveDate != that.resolveDate) return false;
        if (note != null ? !note.equals(that.note) : that.note != null) return false;
        if (oldCardId != null ? !oldCardId.equals(that.oldCardId) : that.oldCardId != null) return false;
        if (customerCode != null ? !customerCode.equals(that.customerCode) : that.customerCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + requestDate;
        result = 31 * result + resolveDate;
        result = 31 * result + (note != null ? note.hashCode() : 0);
        result = 31 * result + (oldCardId != null ? oldCardId.hashCode() : 0);
        result = 31 * result + (customerCode != null ? customerCode.hashCode() : 0);
        return result;
    }
}
