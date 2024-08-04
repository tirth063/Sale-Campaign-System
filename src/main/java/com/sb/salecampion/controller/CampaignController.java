package com.sb.salecampion.controller;
import com.sb.salecampion.model.Campaign;
import com.sb.salecampion.service.CampaingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/campaigns")
public class CampaignController {

    @Autowired
    private CampaingServices campaingServices;

    @PostMapping
    public Campaign createCampaign(@RequestBody Campaign campaign) {
        return campaingServices.saveCampaing(campaign);
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<Campaign>> createCampaigns(@RequestBody List<Campaign> campaigns) {
        List<Campaign> savedCampaigns = campaingServices.saveAllCampaigns(campaigns);
        return ResponseEntity.ok(savedCampaigns);
    }

    @GetMapping
    public List<Campaign> getAllCampaigns() {
        return campaingServices.getAllCampaing();
    }

    @GetMapping("/{id}")
    public Campaign getCampaignById(@PathVariable int id) {
        return campaingServices.getCampaignById(id);
    }
}
