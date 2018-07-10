package com.kg.spring.jpa.repository;

import com.kg.spring.jpa.model.StockQuote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;


public interface StockQuoteRepository extends JpaRepository<StockQuote, Long> {

    @Query("SELECT q from StockQuote q INNER JOIN q.symbol s where s.value = ?1 and q.date >= ?2 and q.date < ?3")
    List<StockQuote> findAllBySymbolAndDate(String symbol, Date begin, Date end);
}
