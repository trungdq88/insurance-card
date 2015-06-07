package com.fpt.mic.mobile.printer.app.dto;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpt.mic.mobile.printer.app.entity.ContractEntity;
import com.fpt.mic.mobile.printer.app.entity.CustomerEntity;

import java.io.IOException;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/6/15.
 */
public class ContractSearchResult implements Parcelable {
    public Integer id;
    public ContractEntity contractEntity;
    public CustomerEntity customerEntity;

    public ContractSearchResult() {

    }

    public ContractSearchResult(Parcel in) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            ContractEntity contractEntity = mapper.readValue(in.readString(), ContractEntity.class);
            CustomerEntity customerEntity = mapper.readValue(in.readString(), CustomerEntity.class);
            this.contractEntity = contractEntity;
            this.customerEntity = customerEntity;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            parcel.writeString(mapper.writeValueAsString(contractEntity));
            parcel.writeString(mapper.writeValueAsString(customerEntity));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public ContractSearchResult createFromParcel(Parcel in) {
            return new ContractSearchResult(in);
        }

        public ContractSearchResult[] newArray(int size) {
            return new ContractSearchResult[size];
        }
    };
}
