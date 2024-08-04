package com.sb.salecampion.repo;

import com.sb.salecampion.model.PriceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PriceHistoryRepo extends JpaRepository<PriceHistory , Integer>
{
     List<PriceHistory> findByProductId(int id);
}
