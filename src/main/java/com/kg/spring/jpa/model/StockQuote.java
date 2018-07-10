package com.kg.spring.jpa.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kg.spring.jpa.adapter.JsonSymbolDeserializer;

import javax.persistence.*;
import java.util.Date;

@Entity
public class StockQuote {

    @Id
    @GeneratedValue
    @JsonIgnore
    private long id;

    @OneToOne(cascade = {CascadeType.ALL})
    private Symbol symbol;

    private double price;
    private long volume;
    private Date date;

    public Symbol getSymbol() {
        return symbol;
    }

    @JsonDeserialize(using=JsonSymbolDeserializer.class)
    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
