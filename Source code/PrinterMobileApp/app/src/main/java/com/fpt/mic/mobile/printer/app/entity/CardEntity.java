package com.fpt.mic.mobile.printer.app.entity;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/5/15.
 */
public class CardEntity implements Parcelable {
    public String cardId;
    public Timestamp activatedDate;
    public Timestamp deactivatedDate;
    public String contractCode;
    public Integer newCardRequestId;
    public ContractEntity micContractByContractCode;
    public Object micNewCardRequestByNewCardRequestId;
    public Collection<Object> micCardAccessLogsByCardId;
    public Collection<Object> micNewCardRequestsByCardId;

    public CardEntity() {

    }

    public CardEntity(Parcel in) {
        ObjectMapper mapper = new ObjectMapper();
        CardEntity cardEntity = new CardEntity();
        try {
            cardEntity = mapper.readValue(in.readString(), CardEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.cardId = cardEntity.cardId;
        this.activatedDate = cardEntity.activatedDate;
        this.deactivatedDate = cardEntity.deactivatedDate;
        this.contractCode = cardEntity.contractCode;
        this.newCardRequestId = cardEntity.newCardRequestId;
        this.micContractByContractCode = cardEntity.micContractByContractCode;
        this.micNewCardRequestByNewCardRequestId = cardEntity.micNewCardRequestByNewCardRequestId;
        this.micCardAccessLogsByCardId = cardEntity.micCardAccessLogsByCardId;
        this.micNewCardRequestsByCardId = cardEntity.micNewCardRequestsByCardId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            parcel.writeString(mapper.writeValueAsString(this));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public CardEntity createFromParcel(Parcel in) {
            return new CardEntity(in);
        }

        public CardEntity[] newArray(int size) {
            return new CardEntity[size];
        }
    };
}
