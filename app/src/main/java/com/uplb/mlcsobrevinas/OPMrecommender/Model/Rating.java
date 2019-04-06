package com.uplb.mlcsobrevinas.OPMrecommender.Model;

public class Rating {
    private String userPhone;
    private String bandsId;
    private String rateValue;
    private String comment;
    public Rating(){

    }

    public Rating(String userPhone, String bandsId, String rateValue, String comment){
        this.userPhone = userPhone;
        this.bandsId = bandsId;
        this.rateValue = rateValue;
        this.comment = comment;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getBandsId() {
        return bandsId;
    }

    public void setBandsId(String bandsId) {
        this.bandsId = bandsId;
    }

    public String getRateValue()
    {
        return rateValue;
    }

    public void setRateValue(String rateValue) {
        this.rateValue = rateValue;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
