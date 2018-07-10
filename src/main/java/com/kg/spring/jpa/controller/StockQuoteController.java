package com.kg.spring.jpa.controller;

import com.kg.spring.jpa.model.StockQuote;
import com.kg.spring.jpa.repository.StockQuoteRepository;
import com.kg.spring.jpa.service.StockQuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/stock-quote")
public class StockQuoteController {

    @Autowired
    StockQuoteRepository stockQuoteRepository;

    @Autowired
    StockQuoteService stockQuoteService;

    @Value("${json.file.path}")
    private String jsonFilePath;

    @Value("${dynamic.load}")
    private boolean dynamicLoad;


    @PostMapping("/load")
    public ResponseEntity<?> saveAll() {
        List<StockQuote> stockQuotes = new ArrayList<>();
        if(!dynamicLoad)
            stockQuotes = stockQuoteService.loadData(jsonFilePath);
        else
            stockQuotes = stockQuoteService.loadDataDynamically();

        if(stockQuotes.size() > 0){
            stockQuoteRepository.saveAll(stockQuotes);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping
    Collection<StockQuote> getAll() {
        return this.stockQuoteRepository.findAll();
    }





}
