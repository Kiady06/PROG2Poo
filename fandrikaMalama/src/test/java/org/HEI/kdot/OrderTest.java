package org.HEI.kdot;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void shouldComputeTotalQuantityInLiters() {

        Drink coke = new Drink(1, "Coke", new Quantity(500, Unit.ML), 1.0);
        Drink water = new Drink(2, "Water", new Quantity(1, Unit.L), 0.5);

        DrinkOrder o1 = new DrinkOrder(1, coke, 1);
        DrinkOrder o2 = new DrinkOrder(2, water, 1);

        Order order = new Order(1, null, List.of(o1, o2));

        double total = order.getTotalQuantity();

        assertEquals(1.5, total, 0.0001);
    }
}