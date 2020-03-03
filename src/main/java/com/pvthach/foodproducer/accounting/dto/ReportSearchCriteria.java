package com.pvthach.foodproducer.accounting.dto;


import java.io.Serializable;

/**
 * Created by THACH-PC
 */

public class ReportSearchCriteria implements Serializable {

    private Integer forYear;

    private Integer forMonth;

    public ReportSearchCriteria() {
    }

    public Integer getForYear() {
        return forYear;
    }

    public void setForYear(Integer forYear) {
        this.forYear = forYear;
    }

    public Integer getForMonth() {
        return forMonth;
    }

    public void setForMonth(Integer forMonth) {
        this.forMonth = forMonth;
    }
}