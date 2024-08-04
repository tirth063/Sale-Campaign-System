package com.sb.salecampion.model;

import jakarta.persistence.*;

@Entity
@Table(name = "campaigndiscount_tbl")
public class CampingDiscount {

    @Id
    private int productId;
    private int discount;

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;

    public CampingDiscount() {
    }

    public CampingDiscount(int productId, int discount) {
        this.productId = productId;
        this.discount = discount;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }
}