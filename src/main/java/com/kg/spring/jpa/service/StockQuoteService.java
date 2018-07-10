package com.kg.spring.jpa.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kg.spring.jpa.model.StockQuote;
import com.kg.spring.jpa.repository.StockQuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class StockQuoteService {

    @Autowired
    StockQuoteRepository stockQuoteRepository;

    @Value("${dynamic.load.url}")
    private String dynamicLoadUrl;


    public List<StockQuote> loadData(String file){
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<StockQuote>> typeReference = new TypeReference<List<StockQuote>>() {};
        InputStream inputStream = TypeReference.class.getResourceAsStream(file);
        List<StockQuote> stockQuotes = new ArrayList<>();
        try {
            stockQuotes = mapper.readValue(inputStream, typeReference);
            System.out.println("Stock quotes loaded!");
            return stockQuotes;
        } catch (IOException e) {
            System.out.println("Unable to load stock quotes: " + e.getMessage());
            return stockQuotes;
        }
    }

    public List<StockQuote> loadDataDynamically() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<StockQuote>> responseEntity = restTemplate.exchange(dynamicLoadUrl,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<StockQuote>>() {});
        List<StockQuote> stockQuotes = responseEntity.getBody();
        return stockQuotes;
    }
}
