package com.fpt.mic.micweb.model.entity;

import javax.persistence.*;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/5/15.
 */
@Entity
@Table(name = "mic_contract_type", schema = "", catalog = "mic_data")
public class ContractTypeEntity {
    private int id;
    private String name;
    private String description;
    private float pricePerYear;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "price_per_year")
    public float getPricePerYear() {
        return pricePerYear;
    }

    public void setPricePerYear(float pricePerYear) {
        this.pricePerYear = pricePerYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContractTypeEntity that = (ContractTypeEntity) o;

        if (id != that.id) return false;
        if (Float.compare(that.pricePerYear, pricePerYear) != 0) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (pricePerYear != +0.0f ? Float.floatToIntBits(pricePerYear) : 0);
        return result;
    }
}
