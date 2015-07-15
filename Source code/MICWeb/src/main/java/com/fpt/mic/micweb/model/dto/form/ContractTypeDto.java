package com.fpt.mic.micweb.model.dto.form;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

/**
 * Created by TriPQM on 07/15/2015.
 */
public class ContractTypeDto {
    @NotEmpty(message = "Tên loại hợp đồng không được để trống")
    private String name;
    @NotEmpty(message = "Miêu tả loại hợp đồng không được để trống")
    private String description;
    @Range(min = 0,message = "Phí hằng năm phải lớn hơn hoặc bằng 0")
    private Integer pricePerYear;

    public ContractTypeDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPricePerYear() {
        return pricePerYear;
    }

    public void setPricePerYear(Integer pricePerYear) {
        this.pricePerYear = pricePerYear;
    }
}
