package com.demo.comentoStatistic.vo;


public class HolidayVO {

    private String holDate;
    private String holName;

    public HolidayVO() {
    }

    public HolidayVO(String holDate, String holName) {
        this.holDate = holDate;
        this.holName = holName;
    }

    public String getHolDate() {
        return holDate;
    }

    public void setHolDate(String holDate) {
        this.holDate = holDate;
    }

    public String getHolName() {
        return holName;
    }

    public void setHolName(String holName) {
        this.holName = holName;
    }
}
