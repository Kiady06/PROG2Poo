package org.HEI.kdot;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Order {
    private int id;
    private LocalDateTime dateOrder;
    Client client;
    List<DrinkOrder> drinkOrders;

    public Order(int id, Client client) {
        this(id, LocalDateTime.now(), client, new ArrayList<DrinkOrder>());
    }

    public Order(int id, Client client,  List<DrinkOrder> drinkOrders) {
        this(id, LocalDateTime.now(), client, drinkOrders);
    }

    public double getTotalQuantity() {
        return drinkOrders.stream()
                .map(DrinkOrder::getDrink)
                .map(Drink::getQuantity)
                .mapToDouble(Quantity::toL)
                .sum();
    }
}
