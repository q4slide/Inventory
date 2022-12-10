package com.inventory.inventory.businesslayer.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;
/**
 * @author TomaVasilev
 * @version 1.0
 * */
@Entity
public class ClientCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Client client;
    @OneToOne
    private Product product;
    private LocalDate addedOn;

    public ClientCart(Client client,Product product, LocalDate addedOn) {
        this.client = client;
        this.product = product;
        this.addedOn = addedOn;
    }

    public ClientCart() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDate getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(LocalDate addedOn) {
        this.addedOn = addedOn;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ",\tProduct name = " + product.getName() +
                 "\tProduct price = "+product.getPrice()+
                ", \taddedOn=" + addedOn ;
    }

}
