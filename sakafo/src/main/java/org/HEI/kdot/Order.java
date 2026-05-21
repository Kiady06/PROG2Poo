package org.HEI.kdot;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Order {
    private int id;
    private LocalDate orderDate;
    private Client client;
    private List<OrderPlate> orderPlates;

    public Order() {
    }

    public Order(int id, LocalDate orderDate, Client client, List<OrderPlate> orderPlates) {
        this.id = id;
        this.orderDate = orderDate;
        this.client = client;
        this.orderPlates = orderPlates;
    }

    public Order(int id, LocalDate orderDate, Client client) {
        this.id = id;
        this.orderDate = orderDate;
        this.client = client;
        this.orderPlates = new ArrayList<>();
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<OrderPlate> getOrderPlates() {
        return orderPlates;
    }

    public void setOrderPlates(List<OrderPlate> orderPlates) {
        this.orderPlates = orderPlates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;

        return id == order.id &&
                Objects.equals(orderDate, order.orderDate) &&
                Objects.equals(client, order.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderDate, client);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", client=" + client +
                '}';
    }

    public boolean emptyOrder() {
        return orderPlates.isEmpty();
    }

    public List<Plate> getPlates() {
        return orderPlates.stream()
                .map(OrderPlate::getPlate)
                .toList();
    }

    public Order combineOrders(Order other) {
        this.orderPlates.addAll(other.orderPlates);

        for (OrderPlate orderPlate : other.getOrderPlates()) {
            if (orderPlate.getOrder() != null) {
                orderPlate.getOrder().setId(this.id);
            }
        }

        return this;
    }

    public float totalTypeCoast(Type type) {
        return (float)this.orderPlates.stream()
                .filter(orderPlate -> orderPlate.getPlate().getType() == type)
                .mapToDouble(orderPlate -> orderPlate.getNumberPlate() * orderPlate.getPlate().getUnitPrice())
                .sum();
    }

    public Plate mostExpensivePlate() {
        return this.orderPlates.stream()
                    .map(OrderPlate::getPlate)
                    .max(Comparator.comparingDouble(Plate::getUnitPrice))
                    .orElse(null);
    }
}