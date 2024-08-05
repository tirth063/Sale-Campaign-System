package com.sb.salecampion.controller;

import com.sb.salecampion.model.PriceHistory;
import com.sb.salecampion.model.Products;
import com.sb.salecampion.scheduler.SchedulerTasks;
import com.sb.salecampion.service.ProductsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    public SchedulerTasks schedulerTasks;

    public final ProductsServices ps;

    ProductController( ProductsServices ps ) {
        this.ps = ps;
    }

    @PostMapping
    public void   saveAll(@RequestBody List<Products> products){
         ps.saveAll(products);
    }

    @GetMapping
    public Page<Products> getProducts(@RequestParam int page, @RequestParam int pageSize) {
        return ps.getAll(page-1, pageSize);
    }

    @GetMapping("/{id}")
    public Products getProductById(@PathVariable int id) {
        return ps.findById(id);
    }

    @GetMapping("/history/{id}")
    public List<PriceHistory> getPriceHistory(@PathVariable int id) {
        return ps.getPriceHistory(id);
    }

    @PostMapping("/start")
    public void callSc(){
        schedulerTasks.updateProductPrice();
    }

}
