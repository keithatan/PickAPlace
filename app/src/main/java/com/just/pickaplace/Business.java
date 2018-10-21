package com.just.pickaplace;

public class Business {
    String bName;
    String bUrl;
    String bImageUrl;
    String bRating;
    String bPhone;
    String bCategory;
    String bCost;

    public Business(String bCost, String bName, String bUrl, String bImageUrl, String bRating, String bPhone, String bCategory) {
        this.bName = bName;
        this.bUrl = bUrl;
        this.bImageUrl = bImageUrl;
        this.bRating = bRating;
        this.bPhone = bPhone;
        this.bCategory = bCategory;
        this.bCost = bCost;
    }

    public String getbCost() {
        return bCost;
    }

    public void setbCost(String bCost) {
        this.bCost = bCost;
    }

    public Business() {

    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public String getbUrl() {
        return bUrl;
    }

    public void setbUrl(String bUrl) {
        this.bUrl = bUrl;
    }

    public String getbImageUrl() {
        return bImageUrl;
    }

    public void setbImageUrl(String bImageUrl) {
        this.bImageUrl = bImageUrl;
    }

    public String getbRating() {
        return bRating;
    }

    public void setbRating(String bRating) {
        this.bRating = bRating;
    }

    public String getbPhone() {
        return bPhone;
    }

    public void setbPhone(String bPhone) {
        this.bPhone = bPhone;
    }

    public String getbCategory() {
        return bCategory;
    }

    public void setbCategory(String bCategory) {
        this.bCategory = bCategory;
    }
}
