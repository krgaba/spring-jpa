package com.kg.spring.jpa.service;


import com.kg.spring.jpa.model.StockQuote;
import com.kg.spring.jpa.model.SummarizedData;
import com.kg.spring.jpa.repository.StockQuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class SummarizedDataService {

    @Autowired
    public StockQuoteRepository stockQuoteRepository;


    public SummarizedData getMonthlySummarizedData(String symbol, String dateString) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        Date beginDate = formatter.parse(dateString);
        Calendar cal = Calendar.getInstance();
        cal.setTime(beginDate);
        cal.add(Calendar.MONTH, 1);
        Date endDate = cal.getTime();

        return getSummarizedData(this.stockQuoteRepository.findAllBySymbolAndDate(symbol, beginDate, endDate));

    }

    public SummarizedData getDailySummarizedData(String symbol, String dateString) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = formatter.parse(dateString);
        Instant nextDay = beginDate.toInstant().plus(1, ChronoUnit.DAYS);
        Date endDate = Date.from(nextDay);

        return getSummarizedData(this.stockQuoteRepository.findAllBySymbolAndDate(symbol, beginDate, endDate));
    }

    public SummarizedData getSummarizedData(List<StockQuote> stockQuotes){
        double maxPrice = 0;
        double minPrice = 0;
        double lastPriceOfTheDay = 0;
        long totalVolume = 0;

        if (stockQuotes.size() > 0) {
            Comparator<StockQuote> priceComparator = Comparator.comparing(StockQuote::getPrice);
            maxPrice = stockQuotes.stream().max(priceComparator).get().getPrice();
            minPrice = stockQuotes.stream().min(priceComparator).get().getPrice();

            Comparator<StockQuote> dateComparator = Comparator.comparing(StockQuote::getDate);
            lastPriceOfTheDay = stockQuotes.stream().max(dateComparator).get().getPrice();

            totalVolume = stockQuotes.stream().mapToLong(StockQuote::getVolume).sum();
        }

        return new SummarizedData(maxPrice, minPrice, totalVolume, lastPriceOfTheDay);
    }

}
