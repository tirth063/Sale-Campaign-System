package com.sb.salecampion.scheduler;

import com.sb.salecampion.model.Campaign;
import com.sb.salecampion.service.CampaingServices;
import com.sb.salecampion.service.ProductsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SchedulerTasks {

    @Autowired
    private CampaingServices campaingServices;

    @Autowired
    private ProductsServices ps;

    @Scheduled(cron = "0 0 0 * * *")
    public void updateProductPrice(){
        System.out.println("Updating product price... started ..");
        //step 1 save local time
        LocalDate today = LocalDate.now();
        //step 2 get data of today from campaign (At startDate)
        List<Campaign> activeCampaigns = campaingServices.getAllCampaing().stream()
                .filter(campaign -> (campaign.getStartDate().isBefore(today) || campaign.getStartDate().isEqual(today))
                        && (campaign.getEndDate().isAfter(today) || campaign.getEndDate().isEqual(today)))
                .collect(Collectors.toList());
        System.out.println();
        System.out.println(Arrays.toString(activeCampaigns.toArray()));
        System.out.println();
        //step 3 get data of past from campaign (At endDate)
        List<Campaign> expiredCampaigns = campaingServices.getAllCampaing().stream()
                .filter(campaign -> campaign.getEndDate().isBefore(today))
                .collect(Collectors.toList());
        System.out.println();
        System.out.println(Arrays.toString(expiredCampaigns.toArray()));
        System.out.println();
        //step 4 update the products deatils and price history here
        ps.updatePriceAtActive(activeCampaigns);
        ps.updatePriceAtExpired(expiredCampaigns);
        System.out.println("Updating product price... finished ..");
    }
}
