package com.anjox.order.api.model;

import com.anjox.order.api.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderModel {

    private UUID id =  UUID.randomUUID();

    private String client;

    private List<OrderedItemModel> items = new ArrayList<>();


    private BigDecimal totalPrice;

    private String email;

    private OrderStatus status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderDate;

    public OrderModel() {
    }

    public OrderModel(UUID id, String client, List<OrderedItemModel> items, BigDecimal totalPrice, String email, OrderStatus status, LocalDateTime orderDate) {
        this.id = id;
        this.client = client;
        this.items = items;
        this.totalPrice = totalPrice;
        this.email = email;
        this.status = status;
        this.orderDate = orderDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public List<OrderedItemModel> getItems() {
        return items;
    }

    public void setItems(List<OrderedItemModel> items) {
        this.items = items;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
}
