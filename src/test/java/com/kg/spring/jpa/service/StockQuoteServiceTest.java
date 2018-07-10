package com.kg.spring.jpa.service;

import com.kg.spring.jpa.model.StockQuote;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class StockQuoteServiceTest {

    StockQuoteService stockQuoteService;

    @Test
    public void testDataLoadFromFile(){
        stockQuoteService = new StockQuoteService();
        List<StockQuote> stockQuotes = stockQuoteService.loadData("/json/fiveStockQuotes.json");
        assertEquals(5,stockQuotes.size());
    }
    @Test
    public void testEmptyFile(){
        stockQuoteService = new StockQuoteService();
        List<StockQuote> stockQuotes = stockQuoteService.loadData("/json/emptyStockQuotes.json");
        assertEquals(0,stockQuotes.size());
    }

    @Test
    public void testStockSymbol(){
        stockQuoteService = new StockQuoteService();
        List<StockQuote> stockQuotes = stockQuoteService.loadData("/json/fiveStockQuotes.json");
        assertEquals("AAPl",stockQuotes.get(0).getSymbol().getValue());
    }

}
