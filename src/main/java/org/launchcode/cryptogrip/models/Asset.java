package org.launchcode.cryptogrip.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Asset {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @ManyToOne
    private Crypto crypto;

    @ManyToMany
    private List<Portfolio> portfolios;

    @NotNull
    private double quantity;

    public Asset() {
    }

    public Asset(Crypto crypto, double quantity) {
        this.crypto = crypto;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public Crypto getCrypto() {
        return crypto;
    }

    public void setCrypto(Crypto crypto) {
        this.crypto = crypto;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
