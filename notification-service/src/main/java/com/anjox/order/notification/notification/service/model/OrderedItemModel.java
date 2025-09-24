package com.anjox.order.notification.notification.service.model;

import java.util.UUID;

public class OrderedItemModel {

    private UUID id =  UUID.randomUUID();

    private ProductModel product;

    private int quantity;

    public OrderedItemModel() {
    }
    public OrderedItemModel(ProductModel product, int quantity) {
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
