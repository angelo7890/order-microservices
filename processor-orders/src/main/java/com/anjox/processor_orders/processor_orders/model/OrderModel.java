package com.anjox.processor_orders.processor_orders.model;
import com.anjox.processor_orders.processor_orders.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class OrderModel {

    @Id
    private UUID id =  UUID.randomUUID();

    private String client;

    @OneToMany(mappedBy = "order")
    private List<OrderedItemModel> items = new ArrayList<>();

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    private String email;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "order_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderDate =  LocalDateTime.now();

    public OrderModel() {
    }

    public OrderModel(UUID id, String client, List<OrderedItemModel> items, BigDecimal totalPrice, String email, OrderStatus status) {
        this.id = id;
        this.client = client;
        this.items = items;
        this.totalPrice = totalPrice;
        this.email = email;
        this.status = status;
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

    @Override
    public String toString() {
        return "OrderModel{" +
                "id=" + id +
                ", client='" + client + '\'' +
                ", items=" + items +
                ", totalPrice=" + totalPrice +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", orderDate=" + orderDate +
                '}';
    }
}
