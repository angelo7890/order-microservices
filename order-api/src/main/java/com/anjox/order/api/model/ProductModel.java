package com.anjox.order.api.model;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductModel {
   private UUID id = UUID.randomUUID();

   private String name;

   private BigDecimal price;

    public ProductModel() {
    }

    public ProductModel(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
