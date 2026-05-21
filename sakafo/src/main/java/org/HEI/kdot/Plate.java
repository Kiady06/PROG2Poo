package org.HEI.kdot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Plate {
    private int id;
    private String name;
    private float unitPrice; //next time use double duh
    private Type type;
    private List<Ingredients> ingredientsList;

    public Plate(int id, String name, float unitPrice, Type type, List<Ingredients> ingredientsList) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.type = type;
        this.ingredientsList = ingredientsList != null ? ingredientsList : new ArrayList<>();
    }

    public Plate(int id, String name, float unitPrice, Type type) {
        this(id, name, unitPrice, type, new ArrayList<>());
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Ingredients> getIngredientsList() {
        return ingredientsList;
    }

    public void setIngredientsList(List<Ingredients> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    public Ingredients addIngredient(Ingredients ingredient) {
        if (this.ingredientsList == null) {
            this.ingredientsList = new ArrayList<>();
        }
        this.ingredientsList.add(ingredient);
        return ingredient;
    }

    public boolean containsIngredient(Ingredients ingredient) {
        if (this.ingredientsList == null) return false;
        return ingredientsList.contains(ingredient);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plate plate)) return false;

        return id == plate.id &&
                Float.compare(plate.unitPrice, unitPrice) == 0 &&
                Objects.equals(name, plate.name) &&
                type == plate.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, unitPrice, type);
    }

    @Override
    public String toString() {
        return "Plate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", unitPrice=" + unitPrice +
                ", type=" + type +
                '}';
    }

    public Ingredients addIngredients(Ingredients ingredients) {

        this.ingredientsList.add(ingredients);

        return ingredients;

    }
}