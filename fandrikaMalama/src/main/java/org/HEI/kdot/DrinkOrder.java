package org.HEI.kdot;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DrinkOrder {
    private int id;
    private Drink drink;
    private int number;
}
