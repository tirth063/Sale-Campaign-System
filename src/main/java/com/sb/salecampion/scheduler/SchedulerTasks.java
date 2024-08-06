package com.sb.salecampion.scheduler;

import com.sb.salecampion.model.Campaign;
import com.sb.salecampion.repo.CampaingRepo;
import com.sb.salecampion.service.ProductsServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.List;

@Component
@Slf4j
public class SchedulerTasks {

    @Autowired
    private CampaingRepo campaingRepo;

    @Autowired
    private ProductsServices ps;

    @Scheduled(cron = "0 0 0 * * *")
    public void updateProductPrice() {
        log.info("Updating product price...");

        // Get the current date at midnight
        LocalDate today = LocalDate.now();

        // Get active campaigns starting from today
        List<Campaign> activeCampaigns = campaingRepo.findByStartDateGreaterThanEqualAndEndDateGreaterThanEqual(today, today);

        log.info("Active campaigns found: {}", activeCampaigns.size());

        // Get expired campaigns ending before today
        List<Campaign> expiredCampaigns = campaingRepo.findByEndDateLessThan(today);

        log.info("Expired campaigns found: {}", expiredCampaigns.size());

        // Update the products details and price history
        ps.updatePriceAtActive(activeCampaigns);
        ps.updatePriceAtExpired(expiredCampaigns);

        log.info("Updated product price...");
    }
}
