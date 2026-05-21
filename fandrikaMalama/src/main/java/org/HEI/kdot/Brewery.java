package org.HEI.kdot;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class Brewery {
    private int id;
    private String name;
    private List<Drink> drinks;
    private List<Client> clients;
    private List<Order> orders;

    public  Brewery(int id, String name) {
        this(id, name, new ArrayList<Drink>(), new ArrayList<Client>(), new ArrayList<Order>());
    }

    public List<Drink> getDrinksKeyWord(String keyword) {
        return drinks.stream()
                .filter(drink -> drink.containKeyWord(keyword))
                .toList();
    }

    public List<Client> getClientsKeyWord(String keyword) {
        return clients.stream()
                .filter(client -> client.hasKeyWord(keyword))
                .toList();
    }


    public Map<Operator, Long> numberClientOperator() {
        return clients.stream()
                .map(Client::getOperator)
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(
                        op -> op,
                        Collectors.counting()
                ));
    }
}

// questions : hashCode
