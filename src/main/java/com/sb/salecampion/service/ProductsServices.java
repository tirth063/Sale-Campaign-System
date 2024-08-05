package com.sb.salecampion.service;

import com.sb.salecampion.model.Campaign;
import com.sb.salecampion.model.CampingDiscount;
import com.sb.salecampion.model.PriceHistory;
import com.sb.salecampion.model.Products;
import com.sb.salecampion.repo.PriceHistoryRepo;
import com.sb.salecampion.repo.ProductsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductsServices {

    private final ProductsRepo productsRepo;
    private final PriceHistoryRepo priceHistoryRepo;

    @Autowired
    public ProductsServices(ProductsRepo productsRepo, PriceHistoryRepo priceHistoryRepo) {
        this.productsRepo = productsRepo;
        this.priceHistoryRepo = priceHistoryRepo;
    }

    public void saveAll(List<Products> products) {
        productsRepo.saveAll(products);
    }

    public Page<Products> getAll(int pgNo, int pgSize) {
        Pageable pages = PageRequest.of(pgNo, pgSize);
        return productsRepo.findAll(pages);
    }

    public Products findById(int id) {
        return productsRepo.findById(id).orElse(null);
    }

    public List<PriceHistory> getPriceHistory(int productId) {
        return priceHistoryRepo.findByProductId(productId);
    }


    public void updatePriceAtActive(List<Campaign> activeCampaigns) {
        for (Campaign campaign : activeCampaigns) {
            for (CampingDiscount discount : campaign.getCampingDiscounts()) {
                Products product = productsRepo.findById(discount.getProductId()).orElse(null);
                if (product != null) {
                    // Save price history
                    PriceHistory priceHistory = new PriceHistory();
                    priceHistory.setProductId(product.getId());
                    priceHistory.setPrice(product.getCurrent_price());
                    priceHistory.setDate(new Date());
                    priceHistoryRepo.save(priceHistory);

                    // Update product price and discount
                    int discountAmount = product.getMrp() * discount.getDiscount() / 100;
                    int newPrice = product.getMrp() - discountAmount;
                    product.setCurrent_price(newPrice);
                    product.setDiscount(discount.getDiscount());
                    productsRepo.save(product);
                }
            }
        }
    }

    public void updatePriceAtExpired(List<Campaign> expiredCampaigns) {
        for (Campaign campaign : expiredCampaigns) {
            for (CampingDiscount discount : campaign.getCampingDiscounts()) {
                Products product = productsRepo.findById(discount.getProductId()).orElse(null);
                if (product != null) {
                    // Save price history
                    PriceHistory priceHistory = new PriceHistory();
                    priceHistory.setProductId(product.getId());
                    priceHistory.setPrice(product.getCurrent_price());
                    priceHistory.setDate(new Date());
                    priceHistoryRepo.save(priceHistory);

                    // Reset product price and discount
                    product.setCurrent_price(product.getMrp());
                    product.setDiscount(0);
                    productsRepo.save(product);
                }
            }
        }
    }
}
