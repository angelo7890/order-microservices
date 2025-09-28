package com.anjox.processor_orders.processor_orders.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "item_order")
public class OrderedItemModel {

    @Id
    private UUID id =  UUID.randomUUID();

    @ManyToOne
    private ProductModel product;

    private int quantity;

    @ManyToOne
    private OrderModel order;

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

    public OrderModel getOrder() {
        return order;
    }

    public void setOrder(OrderModel order) {
        this.order = order;
    }
}
