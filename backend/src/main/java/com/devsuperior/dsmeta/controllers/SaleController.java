package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.services.SaleService;
import com.devsuperior.dsmeta.services.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value ="/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @Autowired
    private SmsService smsService;

    @GetMapping
    public ResponseEntity<Page<Sale>> findSales(
            @RequestParam(value="minDate",defaultValue="") String minDate,
            @RequestParam(value="maxDate",defaultValue="") String maxDate,
            Pageable pageable){
        return ResponseEntity.ok(saleService.findSales(minDate,maxDate,pageable));
    }

    @GetMapping(path = "/{id}/notification")
    public void notifySms(@PathVariable Long id){
        smsService.sendSms(id);
    }

}
