package com.inventory.inventory.businesslayer.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @author TomaVasilev
 * @version 1.0
 */
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PrimaryKeyJoinColumn
    private Long id;
    private String name;
    private String description;
    private Boolean available;
    private BigDecimal price;
    private LocalDate addedOn;


    public Product() {
    }

    public Product(String name, String description, Boolean available, BigDecimal price, LocalDate addedOn) {
        this.name = name;
        this.description = description;
        this.available = available;
        this.price = price;
        this.addedOn = addedOn;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public BigDecimal getPrice() {
        return price;
    }


    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public LocalDate getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(LocalDate addedOn) {
        this.addedOn = addedOn;
    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", available=" + available +
                ", price=" + price +
                ", addedOn=" + addedOn +
                "}";
    }
}
