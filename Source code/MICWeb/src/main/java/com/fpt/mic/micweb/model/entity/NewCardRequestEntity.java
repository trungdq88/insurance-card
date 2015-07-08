package com.fpt.mic.micweb.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/5/15.
 */
@Entity
@Table(name = "mic_new_card_request", schema = "", catalog = "mic_data")
public class NewCardRequestEntity {
    private int id;
    private Timestamp requestDate;
    private Timestamp resolveDate;
    private String note;
    private int isDeliveryRequested;
    private int isPaid;
    private String oldCardInstanceId;
    private Collection<CardInstanceEntity> micCardInstancesById;
    private CardInstanceEntity micCardInstanceByOldCardInstanceId;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "request_date")
    public Timestamp getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Timestamp requestDate) {
        this.requestDate = requestDate;
    }

    @Basic
    @Column(name = "resolve_date")
    public Timestamp getResolveDate() {
        return resolveDate;
    }

    public void setResolveDate(Timestamp resolveDate) {
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
    @Column(name = "is_delivery_requested")
    public int getIsDeliveryRequested() {
        return isDeliveryRequested;
    }

    public void setIsDeliveryRequested(int isDeliveryRequested) {
        this.isDeliveryRequested = isDeliveryRequested;
    }

    @Basic
    @Column(name = "is_paid")
    public int getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(int isPaid) {
        this.isPaid = isPaid;
    }

    @Basic
    @Column(name = "old_card_instance_id")
    public String getOldCardInstanceId() {
        return oldCardInstanceId;
    }

    public void setOldCardInstanceId(String oldCardId) {
        this.oldCardInstanceId = oldCardId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewCardRequestEntity that = (NewCardRequestEntity) o;

        if (id != that.id) return false;
        if (requestDate != null ? !requestDate.equals(that.requestDate) : that.requestDate != null) return false;
        if (resolveDate != null ? !resolveDate.equals(that.resolveDate) : that.resolveDate != null) return false;
        if (note != null ? !note.equals(that.note) : that.note != null) return false;
        if(isDeliveryRequested != that.isDeliveryRequested) return false;
        if(isPaid != that.isPaid) return false;
        if (oldCardInstanceId != null ? !oldCardInstanceId.equals(that.oldCardInstanceId) : that.oldCardInstanceId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (requestDate != null ? requestDate.hashCode() : 0);
        result = 31 * result + (resolveDate != null ? resolveDate.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        result = 31 * result + isDeliveryRequested;
        result = 31 * result + isPaid;
        result = 31 * result + (oldCardInstanceId != null ? oldCardInstanceId.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "micNewCardRequestByNewCardRequestId")
    public Collection<CardInstanceEntity> getMicCardInstancesById() {
        return micCardInstancesById;
    }

    public void setMicCardInstancesById(Collection<CardInstanceEntity> micCardsById) {
        this.micCardInstancesById = micCardsById;
    }

    @ManyToOne
    @JoinColumn(name = "old_card_instance_id", referencedColumnName = "card_id", nullable = false, insertable = false, updatable = false)
    public CardInstanceEntity getMicCardInstanceByOldCardInstanceId() {
        return micCardInstanceByOldCardInstanceId;
    }

    public void setMicCardInstanceByOldCardInstanceId(CardInstanceEntity micCardByOldCardId) {
        this.micCardInstanceByOldCardInstanceId = micCardByOldCardId;
    }

}
