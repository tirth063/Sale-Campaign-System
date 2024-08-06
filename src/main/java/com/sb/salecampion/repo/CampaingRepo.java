package com.sb.salecampion.repo;

import com.sb.salecampion.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface CampaingRepo extends JpaRepository<Campaign, Integer> {
    List<Campaign> findByStartDateGreaterThanEqualAndEndDateGreaterThanEqual(LocalDate startDate, LocalDate endDate);

    List<Campaign> findByEndDateLessThan(LocalDate endDate);
}

