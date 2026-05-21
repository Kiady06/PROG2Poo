package org.HEI.kdot;

import lombok.AllArgsConstructor;
import lombok.Data;

// ngl should've been beverage
@Data
@AllArgsConstructor
public class Drink {
    private int id;
    private String name;
    private Quantity quantity;
    private double unitPrice;

    public boolean containKeyWord(String keyword) {
        if (keyword == null) {
            return true;
        }
        return this.getName().toLowerCase().contains(keyword.toLowerCase());
    }
}
