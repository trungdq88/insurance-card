package com.fpt.mic.micweb.model.entity;

import javax.persistence.*;

/**
 * Created by dinhquangtrung on 6/2/15.
 */
@Entity
@Table(name = "mic_card", schema = "", catalog = "mic_data")
public class CardEntity {
    private String cardId;
    private int activatedDate;
    private Integer deactivatedDate;
    private String contractCode;
    private Integer newCardRequestId;

    @Id
    @Column(name = "card_id")
    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    @Basic
    @Column(name = "activated_date")
    public int getActivatedDate() {
        return activatedDate;
    }

    public void setActivatedDate(int activatedDate) {
        this.activatedDate = activatedDate;
    }

    @Basic
    @Column(name = "deactivated_date")
    public Integer getDeactivatedDate() {
        return deactivatedDate;
    }

    public void setDeactivatedDate(Integer deactivatedDate) {
        this.deactivatedDate = deactivatedDate;
    }

    @Basic
    @Column(name = "contract_code")
    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    @Basic
    @Column(name = "new_card_request_id")
    public Integer getNewCardRequestId() {
        return newCardRequestId;
    }

    public void setNewCardRequestId(Integer newCardRequestId) {
        this.newCardRequestId = newCardRequestId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardEntity that = (CardEntity) o;

        if (activatedDate != that.activatedDate) return false;
        if (cardId != null ? !cardId.equals(that.cardId) : that.cardId != null) return false;
        if (deactivatedDate != null ? !deactivatedDate.equals(that.deactivatedDate) : that.deactivatedDate != null)
            return false;
        if (contractCode != null ? !contractCode.equals(that.contractCode) : that.contractCode != null) return false;
        if (newCardRequestId != null ? !newCardRequestId.equals(that.newCardRequestId) : that.newCardRequestId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cardId != null ? cardId.hashCode() : 0;
        result = 31 * result + activatedDate;
        result = 31 * result + (deactivatedDate != null ? deactivatedDate.hashCode() : 0);
        result = 31 * result + (contractCode != null ? contractCode.hashCode() : 0);
        result = 31 * result + (newCardRequestId != null ? newCardRequestId.hashCode() : 0);
        return result;
    }
}
