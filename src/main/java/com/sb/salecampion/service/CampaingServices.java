package com.sb.salecampion.service;

import com.sb.salecampion.model.Campaign;
import com.sb.salecampion.model.CampingDiscount;
import com.sb.salecampion.repo.CampaingRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaingServices {

    private final CampaingRepo campaingRepo;

    public CampaingServices(CampaingRepo campaingRepo) {
        this.campaingRepo = campaingRepo;
    }

    public Campaign saveCampaing(Campaign campaing) {
        for (CampingDiscount discount : campaing.getCampingDiscounts()){
            discount.setCampaign(campaing);
        }
        return campaingRepo.save(campaing);
    }

    // Save multiple campaigns
    public List<Campaign> saveAllCampaigns(List<Campaign> campaigns) {
        return campaingRepo.saveAll(campaigns);
    }

    // Get all campaigns
    public List<Campaign> getAllCampaing() {
        return campaingRepo.findAll();
    }

    // Get campaign by ID
    public Campaign getCampaignById(int id) {
        return campaingRepo.findById(id).orElse(null);
    }

    public Campaign createCampaign(Campaign campaign) {
        // Set the campaign reference for each discount
        for (CampingDiscount discount : campaign.getCampingDiscounts()) {
            discount.setCampaign(campaign);
        }
        return campaingRepo.save(campaign);
    }
}
