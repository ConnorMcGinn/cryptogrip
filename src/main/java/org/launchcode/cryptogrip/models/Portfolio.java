package org.launchcode.cryptogrip.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Portfolio {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

    @ManyToMany(mappedBy = "portfolios")
    private List<Asset> assets;

    public Portfolio() {
    }

    public Portfolio(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

    public void addAsset(Asset anAsset) {
        this.assets.add(anAsset);
    }
}
