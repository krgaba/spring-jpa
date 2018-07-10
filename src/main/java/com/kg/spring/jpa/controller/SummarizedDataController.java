package com.kg.spring.jpa.controller;

import com.kg.spring.jpa.model.SummarizedData;
import com.kg.spring.jpa.service.SummarizedDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/summarized-data")
public class SummarizedDataController {

    @Autowired
    SummarizedDataService summarizedDataService;

    @RequestMapping(value = "/byDate/{symbol}/{dateString}", method = RequestMethod.GET)
    public SummarizedData getSummarizedDataByDate(@PathVariable String symbol, @PathVariable String dateString)
            throws ParseException {

        return this.summarizedDataService.getDailySummarizedData(symbol, dateString);
    }

    @RequestMapping(value = "/byMonth/{symbol}/{dateString}", method = RequestMethod.GET)
    public SummarizedData getSummarizedDataByMonth(@PathVariable String symbol, @PathVariable String dateString)
            throws ParseException {

        return this.summarizedDataService.getMonthlySummarizedData(symbol, dateString);

    }
}
