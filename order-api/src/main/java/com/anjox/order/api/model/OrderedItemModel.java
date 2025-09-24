package com.anjox.order.api.model;

import java.util.UUID;

public class OrderedItemModel {

    private UUID id =  UUID.randomUUID();

    private ProductModel product;

    private int quantity;

    public OrderedItemModel() {
    }
    public OrderedItemModel(UUID id, ProductModel product, int quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
