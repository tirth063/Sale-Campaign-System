package com.sb.salecampion.repo;

import com.sb.salecampion.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaingRepo extends JpaRepository<Campaign , Integer>
{

}
