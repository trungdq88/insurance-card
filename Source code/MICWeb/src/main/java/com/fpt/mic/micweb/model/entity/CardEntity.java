package com.fpt.mic.micweb.model.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 7/8/15.
 */
@Entity
@Table(name = "mic_card", schema = "", catalog = "mic_data")
public class CardEntity {
    public static final int STATUS_READY = 1;
    public static final int STATUS_DEACTIVATED = 0;
    private String cardId;
    private int status;
    private Collection<CardInstanceEntity> micCardInstancesByCardId;

    @Id
    @Column(name = "card_id")
    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    @Basic
    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardEntity that = (CardEntity) o;

        if (status != that.status) return false;
        if (cardId != null ? !cardId.equals(that.cardId) : that.cardId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cardId != null ? cardId.hashCode() : 0;
        result = 31 * result + status;
        return result;
    }

    @OneToMany(mappedBy = "micCardByCardId")
    public Collection<CardInstanceEntity> getMicCardInstancesByCardId() {
        return micCardInstancesByCardId;
    }

    public void setMicCardInstancesByCardId(Collection<CardInstanceEntity> micCardInstancesByCardId) {
        this.micCardInstancesByCardId = micCardInstancesByCardId;
    }
}
