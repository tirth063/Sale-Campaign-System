package com.sb.salecampion.repo;

import com.sb.salecampion.model.Products;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepo extends JpaRepository<Products , Integer>
{

}
