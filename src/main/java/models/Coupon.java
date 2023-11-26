package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Coupon {
    @JsonProperty("promo_name")
    private String promoName = null;

    @JsonProperty("promo_value")
    private Integer promoValue= null;

    @JsonProperty("promo_massage")
    private String promoMassage= null;

    @JsonProperty("status")
    private boolean status= false;

    @JsonProperty("work_once")
    private boolean workOnce= false;

    @JsonProperty("limits")
    private String limits= null;

    public void setPromoName(String promoName) {
        this.promoName = promoName;
    }

    public void setPromoValue(Integer promoValue) {
        this.promoValue = promoValue;
    }

    public void setPromoMassage(String promoMassage) {
        this.promoMassage = promoMassage;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setWorkOnce(boolean workOnce) {
        this.workOnce = workOnce;
    }

    public void setLimits(String limits) {
        this.limits = limits;
    }

    @Override
    public String toString() {
        return "\"CouponData\" :{" +
                "\"promoName\"=\"" + promoName + '\'' +
                ", \"promoValue\"=" + promoValue +
                ", \"promoMassage\"=\"" + promoMassage + '\"' +
                ", \"status\"=" + status +
                ", \"workOnce\"=" + workOnce +
                ", \"limits\"=\"" + limits + '\"' +
                '}';
    }
}