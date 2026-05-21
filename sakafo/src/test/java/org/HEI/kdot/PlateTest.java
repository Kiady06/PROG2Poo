package org.HEI.kdot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.HEI.kdot.Type.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests Plate class")
class PlateTest {

    private Plate plate;
    private Ingredients ingredient2;
    private Ingredients ingredient3;

    @BeforeEach
    void setUp() {
        var ingredient1 = new Ingredients(1, "potato");
        this.ingredient2 = new Ingredients(2, "carot");
        this.ingredient3 = new Ingredients(3, "tomato");

        var ingredientsPLat1 = new ArrayList<Ingredients>();
        ingredientsPLat1.add(ingredient1);
        ingredientsPLat1.add(ingredient2);

        this.plate = new Plate(1, "Meh", 12, APPETIZER, ingredientsPLat1);
    }

    @Nested
    class PlateIngredientsTest {

        @Test
        void addIngredientPlate() {
            // Given
            int initialIngredientsLength = plate.getIngredientsList().size();

            // When
            plate.addIngredients(ingredient3);

            // Then
            assertEquals(initialIngredientsLength + 1, plate.getIngredientsList().size());
        }

        @Test
        void doNotContainIngredientsPlate() {
            // Given / When

            // Then
            assertFalse(plate.containsIngredient(ingredient3));
        }

        @Test
        void containIngredientsPlate() {
            // Given / When

            // Then
            assertTrue(plate.containsIngredient(ingredient2));
        }
    }
}
