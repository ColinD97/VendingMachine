package com.techelevator;

import java.math.BigDecimal;

public class CoinPurse {
    private int coinQuarter;
    private int coinDime;
    private int coinNickel;
    private final BigDecimal valueQuarter;
    private final BigDecimal valueDime;
    private final BigDecimal valueNickel;


    public CoinPurse(){
        this.coinQuarter = 0;
        this.coinDime = 0;
        this.coinNickel = 0;
        this.valueQuarter = new BigDecimal("0.25");
        this.valueDime = new BigDecimal("0.10");
        this.valueNickel = new BigDecimal("0.05");
    }


    public BigDecimal getValueQuarter() {
        return valueQuarter.multiply(BigDecimal.valueOf(coinQuarter));
    }

    public BigDecimal getValueDime() {
        return valueDime.multiply(BigDecimal.valueOf(coinDime));
    }

    public BigDecimal getValueNickel() {
        return valueNickel.multiply(BigDecimal.valueOf(coinNickel));
    }

    public int getCoinQuarter() {
        return coinQuarter;
    }

    public void setCoinQuarter(int coinQuarter) {
        this.coinQuarter = coinQuarter;
    }

    public int getCoinDime() {
        return coinDime;
    }

    public void setCoinDime(int coinDime) {
        this.coinDime = coinDime;
    }

    public int getCoinNickel() {
        return coinNickel;
    }

    public void setCoinNickel(int coinNickel) {
        this.coinNickel = coinNickel;
    }
}
