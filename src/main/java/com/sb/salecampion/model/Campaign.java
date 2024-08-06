package com.sb.salecampion.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "campaign_tbl")
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campaign_id")
    private int id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String title;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "campaign")
    @JsonManagedReference
    private List<CampingDiscount> campingDiscounts;

    public Campaign() {
    }

    public Campaign(int id, LocalDate startDate, LocalDate endDate, String title, List<CampingDiscount> campingDiscounts) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.campingDiscounts = campingDiscounts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CampingDiscount> getCampingDiscounts() {
        return campingDiscounts;
    }

    public void setCampingDiscounts(List<CampingDiscount> campingDiscounts) {
        this.campingDiscounts = campingDiscounts;
    }
}
