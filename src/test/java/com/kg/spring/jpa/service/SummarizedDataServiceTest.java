package com.kg.spring.jpa.service;

import com.kg.spring.jpa.model.StockQuote;
import com.kg.spring.jpa.model.SummarizedData;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class SummarizedDataServiceTest {

    public StockQuoteService stockQuoteService;

    public List<StockQuote> stockQuotes;

    @Before
    public void before(){
        stockQuoteService = new StockQuoteService();
        stockQuotes = stockQuoteService.loadData("/json/fiveStockQuotes.json");
    }

    @Test
    public void testClosingPrice(){
        SummarizedDataService summarizedDataService = new SummarizedDataService();
        SummarizedData summarizedData = summarizedDataService.getSummarizedData(stockQuotes);
        assertEquals(Double.doubleToLongBits(187.87), Double.doubleToLongBits(summarizedData.getClosingPrice()));
    }

    @Test
    public void testTotalVolume(){
        SummarizedDataService summarizedDataService = new SummarizedDataService();
        SummarizedData summarizedData = summarizedDataService.getSummarizedData(stockQuotes);
        assertEquals(6949, summarizedData.getTotalVolume());
    }

    @Test
    public void testLowest(){
        SummarizedDataService summarizedDataService = new SummarizedDataService();
        SummarizedData summarizedData = summarizedDataService.getSummarizedData(stockQuotes);
        assertEquals(Double.doubleToLongBits(180.02), Double.doubleToLongBits(summarizedData.getLowestPrice()));
    }

    @Test
    public void testHighest(){
        SummarizedDataService summarizedDataService = new SummarizedDataService();
        SummarizedData summarizedData = summarizedDataService.getSummarizedData(stockQuotes);
        assertEquals(Double.doubleToLongBits(189.12), Double.doubleToLongBits(summarizedData.getHighestPrice()));
    }



}
