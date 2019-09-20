package org.launchcode.cryptogrip.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Crypto {
    @Id
    private int id;

    @NotNull
    private String name;

    @NotNull
    private String symbol;

    @OneToMany
    @JoinColumn(name = "crypto_id")
    private List<Asset> assets = new ArrayList<>();

    @NotNull
    private double price;

    @NotNull
    private double marketCap;

    @NotNull
    private int cmcRank;

    public Crypto() {
    }

    public Crypto(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public Crypto(int id, String name, String symbol, double price,
                  double marketCap, int cmcRank) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
//        this.asset = asset;
        this.price = price;
        this.marketCap = marketCap;
        this.cmcRank = cmcRank;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(double marketCap) {
        this.marketCap = marketCap;
    }

    public int getCmcRank() {
        return cmcRank;
    }

    public void setCmcRank(int cmcRank) {
        this.cmcRank = cmcRank;
    }

}
