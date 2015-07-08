package com.fpt.mic.micweb.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/5/15.
 */
@Entity
@Table(name = "mic_card_access_log", schema = "", catalog = "mic_data")
public class CardAccessLogEntity {
    private int id;
    private Timestamp accessDate;
    private String device;
    private String requestService;
    private String responseContent;
    private String cardInstanceId;
    private CardInstanceEntity micCardInstanceByCardInstanceId;

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
    @Column(name = "access_date")
    public Timestamp getAccessDate() {
        return accessDate;
    }

    public void setAccessDate(Timestamp accessDate) {
        this.accessDate = accessDate;
    }

    @Basic
    @Column(name = "device")
    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    @Basic
    @Column(name = "request_service")
    public String getRequestService() {
        return requestService;
    }

    public void setRequestService(String requestService) {
        this.requestService = requestService;
    }

    @Basic
    @Column(name = "response_content")
    public String getResponseContent() {
        return responseContent;
    }

    public void setResponseContent(String responseContent) {
        this.responseContent = responseContent;
    }

    @Basic
    @Column(name = "card_instance_id")
    public String getCardInstanceId() {
        return cardInstanceId;
    }

    public void setCardInstanceId(String cardId) {
        this.cardInstanceId = cardId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardAccessLogEntity that = (CardAccessLogEntity) o;

        if (id != that.id) return false;
        if (accessDate != null ? !accessDate.equals(that.accessDate) : that.accessDate != null) return false;
        if (device != null ? !device.equals(that.device) : that.device != null) return false;
        if (requestService != null ? !requestService.equals(that.requestService) : that.requestService != null)
            return false;
        if (responseContent != null ? !responseContent.equals(that.responseContent) : that.responseContent != null)
            return false;
        if (cardInstanceId != null ? !cardInstanceId.equals(that.cardInstanceId) : that.cardInstanceId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (accessDate != null ? accessDate.hashCode() : 0);
        result = 31 * result + (device != null ? device.hashCode() : 0);
        result = 31 * result + (requestService != null ? requestService.hashCode() : 0);
        result = 31 * result + (responseContent != null ? responseContent.hashCode() : 0);
        result = 31 * result + (cardInstanceId != null ? cardInstanceId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "card_instance_id", referencedColumnName = "card_id", nullable = false, insertable = false, updatable = false)
    public CardInstanceEntity getMicCardInstanceByCardInstanceId() {
        return micCardInstanceByCardInstanceId;
    }

    public void setMicCardInstanceByCardInstanceId(CardInstanceEntity micCardByCardId) {
        this.micCardInstanceByCardInstanceId = micCardByCardId;
    }
}
