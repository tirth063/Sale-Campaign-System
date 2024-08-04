package com.sb.salecampion.scheduler;

import com.sb.salecampion.model.Campaign;
import com.sb.salecampion.service.CampaingServices;
import com.sb.salecampion.service.ProductsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SchedulerTasks {
    @Autowired
    private CampaingServices campaingServices;
    @Autowired
    private ProductsServices ps;

    @Scheduled(cron = "* * * * * *") // Every hour
    public void updateProductPrice(){
        List<Campaign> activeCampaigns = campaingServices.getAllCampaing().stream()
                .filter(campaign -> campaign.getStartDate().before(new Date()) && campaign.getEndDate().after(new Date()))
                .collect(Collectors.toList());

        List<Campaign> expiredCampaigns = campaingServices.getAllCampaing().stream()
                .filter(campaign -> campaign.getEndDate().before(new Date()))
                .collect(Collectors.toList());

        ps.updatePriceAtActive(activeCampaigns);
        ps.updatePriceAtExpired(expiredCampaigns);
    }
}
