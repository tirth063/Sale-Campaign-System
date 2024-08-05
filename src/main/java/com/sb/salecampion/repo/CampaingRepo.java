package com.sb.salecampion.repo;

import com.sb.salecampion.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CampaingRepo extends JpaRepository<Campaign , Integer>
{

}
