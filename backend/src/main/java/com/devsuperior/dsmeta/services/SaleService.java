package com.devsuperior.dsmeta.services;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
public class SaleService {

    @Autowired
    private SaleRepository repository;

    public Page<Sale> findSales(String minDate,String maxDate,Pageable pageable){
        LocalDate min=minDate.equals("") ?
                LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()).minusYears(1) :
                LocalDate.parse(minDate);
        LocalDate max =maxDate.equals("") ?
                LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()) :
                LocalDate.parse(maxDate);

        return repository.findSales(min,max,pageable);

    }

}
