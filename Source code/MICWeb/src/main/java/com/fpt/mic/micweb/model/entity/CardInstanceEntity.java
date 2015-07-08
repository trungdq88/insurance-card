package com.fpt.mic.micweb.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/5/15.
 */
@Entity
@Table(name = "mic_card_instance", schema = "", catalog = "mic_data")
public class CardInstanceEntity implements Serializable {
    private int id;
    private String cardId;
    private Timestamp activatedDate;
    private Timestamp deactivatedDate;
    private String contractCode;
    private Integer newCardRequestId;
    private CardEntity micCardByCardId;
    private ContractEntity micContractByContractCode;
    private NewCardRequestEntity micNewCardRequestByNewCardRequestId;
    private Collection<CardAccessLogEntity> micCardAccessLogsByCardId;
    private Collection<NewCardRequestEntity> micNewCardRequestsByCardId;

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
    @Column(name = "card_id")
    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    @Basic
    @Column(name = "activated_date")
    public Timestamp getActivatedDate() {
        return activatedDate;
    }

    public void setActivatedDate(Timestamp activatedDate) {
        this.activatedDate = activatedDate;
    }

    @Basic
    @Column(name = "deactivated_date")
    public Timestamp getDeactivatedDate() {
        return deactivatedDate;
    }

    public void setDeactivatedDate(Timestamp deactivatedDate) {
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

        CardInstanceEntity that = (CardInstanceEntity) o;

        if (cardId != null ? !cardId.equals(that.cardId) : that.cardId != null) return false;
        if (activatedDate != null ? !activatedDate.equals(that.activatedDate) : that.activatedDate != null)
            return false;
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
        result = 31 * result + (activatedDate != null ? activatedDate.hashCode() : 0);
        result = 31 * result + (deactivatedDate != null ? deactivatedDate.hashCode() : 0);
        result = 31 * result + (contractCode != null ? contractCode.hashCode() : 0);
        result = 31 * result + (newCardRequestId != null ? newCardRequestId.hashCode() : 0);
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

    @ManyToOne
    @JoinColumn(name = "new_card_request_id", referencedColumnName = "id", insertable = false, updatable = false)
    public NewCardRequestEntity getMicNewCardRequestByNewCardRequestId() {
        return micNewCardRequestByNewCardRequestId;
    }

    public void setMicNewCardRequestByNewCardRequestId(NewCardRequestEntity micNewCardRequestByNewCardRequestId) {
        this.micNewCardRequestByNewCardRequestId = micNewCardRequestByNewCardRequestId;
    }

    @OneToMany(mappedBy = "micCardInstanceByCardInstanceId")
    public Collection<CardAccessLogEntity> getMicCardAccessLogsByCardId() {
        return micCardAccessLogsByCardId;
    }

    public void setMicCardAccessLogsByCardId(Collection<CardAccessLogEntity> micCardAccessLogsByCardId) {
        this.micCardAccessLogsByCardId = micCardAccessLogsByCardId;
    }

    @OneToMany(mappedBy = "micCardInstanceByOldCardInstanceId")
    public Collection<NewCardRequestEntity> getMicNewCardRequestsByCardId() {
        return micNewCardRequestsByCardId;
    }

    public void setMicNewCardRequestsByCardId(Collection<NewCardRequestEntity> micNewCardRequestsByCardId) {
        this.micNewCardRequestsByCardId = micNewCardRequestsByCardId;
    }

    @ManyToOne
    @JoinColumn(name = "card_id", referencedColumnName = "card_id", insertable = false, updatable = false)
    public CardEntity getMicCardByCardId() {
        return micCardByCardId;
    }

    public void setMicCardByCardId(CardEntity micCardByCardId) {
        this.micCardByCardId = micCardByCardId;
    }
}
