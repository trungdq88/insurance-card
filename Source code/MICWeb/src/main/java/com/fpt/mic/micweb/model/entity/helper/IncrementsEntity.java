package com.fpt.mic.micweb.model.entity.helper;

import javax.persistence.*;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/6/15.
 */
@Entity
@Table(name = "increments", schema = "", catalog = "mic_data")
public class IncrementsEntity {
    private String tableName;
    private int increment;

    @Id
    @Column(name = "table_name")
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Basic
    @Column(name = "increment")
    public int getIncrement() {
        return increment;
    }

    public void setIncrement(int increment) {
        this.increment = increment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IncrementsEntity that = (IncrementsEntity) o;

        if (increment != that.increment) return false;
        if (tableName != null ? !tableName.equals(that.tableName) : that.tableName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tableName != null ? tableName.hashCode() : 0;
        result = 31 * result + increment;
        return result;
    }
}
