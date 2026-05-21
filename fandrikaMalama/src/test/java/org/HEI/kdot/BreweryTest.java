package org.HEI.kdot;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BreweryTest {

    @Nested
    class DrinkFilterTests {

        @Test
        void shouldFilterDrinksByKeyword() {

            Drink d1 = new Drink(1, "Coca", new Quantity(500, Unit.ML), 1.0);
            Drink d2 = new Drink(2, "Pepsi", new Quantity(1, Unit.L), 1.5);

            Brewery brewery = new Brewery(1, "Test", List.of(d1, d2), List.of(), List.of());

            List<Drink> result = brewery.getDrinksKeyWord("coc");

            assertEquals(1, result.size());
            assertEquals("Coca", result.get(0).getName());
        }

        @Test
        void shouldReturnAllWhenKeywordNull() {

            Drink d1 = new Drink(1, "Coca", new Quantity(500, Unit.ML), 1.0);

            Brewery brewery = new Brewery(1, "Test", List.of(d1), List.of(), List.of());

            List<Drink> result = brewery.getDrinksKeyWord(null);

            assertEquals(1, result.size());
        }
    }

    @Nested
    class ClientFilterTests {

        @Test
        void shouldFilterClientsByKeyword() {

            Client c1 = new Client(1, "John", "Doe", "0321111111");
            Client c2 = new Client(2, "Jane", "Smith", "0342222222");

            Brewery brewery = new Brewery(1, "Test", List.of(), List.of(c1, c2), List.of());

            List<Client> result = brewery.getClientsKeyWord("jo");

            assertEquals(1, result.size());
            assertEquals("John", result.get(0).getFirstName());
        }
    }

    @Nested
    class OperatorStatsTests {

        @Test
        void shouldCountClientsByOperator() {

            Client c1 = new Client(1, "A", "A", "0321111111");
            Client c2 = new Client(2, "B", "B", "0322222222");
            Client c3 = new Client(3, "C", "C", "0343333333");

            Brewery brewery = new Brewery(
                    1,
                    "Test",
                    List.of(),
                    List.of(c1, c2, c3),
                    List.of()
            );

            Map<Operator, Long> result = brewery.numberClientOperator();

            assertAll(
                    () -> assertEquals(2L, result.get(Operator.ORANGE)),
                    () -> assertEquals(1L, result.get(Operator.TELMA))
            );
        }

        @Test
        void shouldReturnEmptyMapWhenNoClients() {

            Brewery brewery = new Brewery(1, "Test");

            Map<Operator, Long> result = brewery.numberClientOperator();

            assertTrue(result.isEmpty());
        }
    }
}