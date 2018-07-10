package com.kg.spring.jpa.model;

public class SummarizedData {

    private final double closingPrice;
    private double highestPrice;
    private double lowestPrice;
    private long totalVolume;

    public SummarizedData(double highestPrice, double lowestPrice, long totalVolume, double closingPrice) {
        this.highestPrice = highestPrice;
        this.lowestPrice = lowestPrice;
        this.totalVolume = totalVolume;
        this.closingPrice = closingPrice;
    }

    public double getClosingPrice() {
        return closingPrice;
    }

    public double getHighestPrice() {
        return highestPrice;
    }

    public void setHighestPrice(double highestPrice) {
        this.highestPrice = highestPrice;
    }

    public double getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(double lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public long getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(long totalVolume) {
        this.totalVolume = totalVolume;
    }
}
