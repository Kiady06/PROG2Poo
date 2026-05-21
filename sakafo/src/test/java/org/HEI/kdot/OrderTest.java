package org.HEI.kdot;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.HEI.kdot.Type.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests Order class")
class OrderTest {
    private Client client1;
    private Client client2;
    private Plate plate1;
    private Plate plate2;
    private Plate plate3;
    private Order emptyOrder;
    private Order orderWithPlates;
    private OrderPlate orderPlate1;
    private OrderPlate orderPlate2;

    @BeforeEach
    void setUp() {
        client1 = new Client(1, "Dupont", "Jean", "0600000001");
        client2 = new Client(2, "Martin", "Alice", "0600000002");

        plate1 = new Plate(1, "Pizza",    8.50f,  MAIN);
        plate2 = new Plate(2, "Coca",     2.00f,  DRINK);
        plate3 = new Plate(3, "Tiramisu", 5.00f,  DESSERT);

        emptyOrder = new Order(1, LocalDate.now(), client1);

        orderWithPlates = new Order(2, LocalDate.now(), client1);
        orderPlate1 = new OrderPlate(1, plate1, 2, orderWithPlates);
        orderPlate2 = new OrderPlate(2, plate2, 1, orderWithPlates);
        orderWithPlates.getOrderPlates().add(orderPlate1);
        orderWithPlates.getOrderPlates().add(orderPlate2);
    }

    @Nested
    @DisplayName("emptyOrder()")
    class EmptyOrderTests {

        @Test
        @DisplayName("Retourne true quand la liste de plats est vide")
        void shouldReturnTrue_whenNoPlatesAdded() {
            assertTrue(emptyOrder.emptyOrder(),
                    "Une commande sans plats devrait être considérée vide");
        }

        @Test
        @DisplayName("Retourne false quand la commande contient au moins un plat")
        void shouldReturnFalse_whenAtLeastOnePlateExists() {
            assertFalse(orderWithPlates.emptyOrder(),
                    "Une commande avec des plats ne devrait pas être vide");
        }

        @Test
        @DisplayName("Retourne true après création avec constructeur sans liste")
        void shouldReturnTrue_withNoArgConstructorAndEmptyList() {
            Order freshOrder = new Order(99, LocalDate.now(), client1);
            assertTrue(freshOrder.emptyOrder());
        }

        @Test
        @DisplayName("Retourne false après ajout d'un seul plat")
        void shouldReturnFalse_afterAddingOnePlate() {
            Order order = new Order(10, LocalDate.now(), client1);
            assertTrue(order.emptyOrder(), "Devrait être vide avant l'ajout");

            OrderPlate op = new OrderPlate(10, plate3, 1, order);
            order.getOrderPlates().add(op);

            assertFalse(order.emptyOrder(), "Ne devrait plus être vide après l'ajout");
        }
    }

    @Nested
    @DisplayName("getPlates()")
    class GetPlatesTests {

        @Test
        @DisplayName("Retourne une liste vide pour une commande vide")
        void shouldReturnEmptyList_whenOrderIsEmpty() {
            List<Plate> plates = emptyOrder.getPlates();

            assertNotNull(plates, "La liste ne doit jamais être null");
            assertTrue(plates.isEmpty(), "La liste doit être vide");
        }

        @Test
        @DisplayName("Retourne exactement les plats contenus dans la commande")
        void shouldReturnCorrectPlates_whenOrderHasPlates() {
            List<Plate> plates = orderWithPlates.getPlates();

            assertEquals(2, plates.size(), "Doit contenir 2 plats");
            assertTrue(plates.contains(plate1), "Doit contenir plate1 (Pizza)");
            assertTrue(plates.contains(plate2), "Doit contenir plate2 (Coca)");
        }

        @Test
        @DisplayName("Retourne les plats dans le même ordre que les OrderPlates")
        void shouldPreserveOrder_ofPlates() {
            List<Plate> plates = orderWithPlates.getPlates();

            assertEquals(plate1, plates.get(0), "Premier plat doit être plate1");
            assertEquals(plate2, plates.get(1), "Deuxième plat doit être plate2");
        }

        @Test
        @DisplayName("Ne retourne pas de doublons si deux OrderPlates ont le même plat")
        void shouldReturnBothEntries_evenIfSamePlate() {
            // Même plat commandé deux fois en lignes séparées → 2 entrées dans la liste
            Order order = new Order(20, LocalDate.now(), client1);
            OrderPlate op1 = new OrderPlate(20, plate1, 1, order);
            OrderPlate op2 = new OrderPlate(21, plate1, 3, order);
            order.getOrderPlates().add(op1);
            order.getOrderPlates().add(op2);

            List<Plate> plates = order.getPlates();

            assertEquals(2, plates.size(),
                    "getPlates() doit retourner une entrée par OrderPlate, pas dédupliquer");
        }

        @Test
        @DisplayName("Retourne bien des objets Plate (pas null)")
        void shouldNeverContainNullPlates() {
            List<Plate> plates = orderWithPlates.getPlates();
            plates.forEach(p -> assertNotNull(p, "Aucun plat ne devrait être null"));
        }
    }

    @Nested
    @DisplayName("combineOrders()")
    class CombineOrdersTests {

        @Test
        @DisplayName("La commande résultante contient les plats des deux commandes")
        void shouldContainPlatesFromBothOrders() {
            Order other = new Order(3, LocalDate.now(), client2);
            OrderPlate op3 = new OrderPlate(3, plate3, 2, other);
            other.getOrderPlates().add(op3);

            orderWithPlates.combineOrders(other);

            assertEquals(3, orderWithPlates.getOrderPlates().size(),
                    "Doit contenir 2 + 1 = 3 OrderPlates après combinaison");
        }

        @Test
        @DisplayName("Combiner avec une commande vide ne modifie pas la commande courante")
        void shouldNotChange_whenCombiningWithEmptyOrder() {
            int sizeBefore = orderWithPlates.getOrderPlates().size();

            orderWithPlates.combineOrders(emptyOrder);

            assertEquals(sizeBefore, orderWithPlates.getOrderPlates().size(),
                    "La taille ne doit pas changer si l'autre commande est vide");
        }

        @Test
        @DisplayName("Combiner deux commandes vides donne une commande vide")
        void shouldStayEmpty_whenBothOrdersAreEmpty() {
            Order other = new Order(5, LocalDate.now(), client2);

            emptyOrder.combineOrders(other);

            assertTrue(emptyOrder.emptyOrder(),
                    "Combiner deux commandes vides doit rester vide");
        }

        @Test
        @DisplayName("Retourne la commande courante (this) — permet le chaînage")
        void shouldReturnThis_forChaining() {
            Order other = new Order(6, LocalDate.now(), client2);
            Order result = orderWithPlates.combineOrders(other);

            assertSame(orderWithPlates, result,
                    "combineOrders doit retourner l'instance courante (this)");
        }

        @Test
        @DisplayName("Les plats de l'autre commande sont bien accessibles via getPlates()")
        void shouldMakePlatesAccessible_afterCombine() {
            Order other = new Order(7, LocalDate.now(), client2);
            OrderPlate op3 = new OrderPlate(3, plate3, 1, other);
            other.getOrderPlates().add(op3);

            orderWithPlates.combineOrders(other);

            List<Plate> plates = orderWithPlates.getPlates();
            assertTrue(plates.contains(plate3),
                    "plate3 doit être accessible dans la commande combinée");
        }

        @Test
        @DisplayName("Combiner n'affecte pas la commande passée en paramètre")
        void shouldNotMutate_theOtherOrder() {
            Order other = new Order(8, LocalDate.now(), client2);
            OrderPlate op3 = new OrderPlate(3, plate3, 1, other);
            other.getOrderPlates().add(op3);

            int otherSizeBefore = other.getOrderPlates().size();
            orderWithPlates.combineOrders(other);

            assertEquals(otherSizeBefore, other.getOrderPlates().size(),
                    "combineOrders ne doit pas modifier la commande passée en paramètre");
        }
    }

    @Nested
    @DisplayName("totalTypeCoast()")
    class TotalTypeCoastTests {

        @Test
        @DisplayName("Retourne le prix total correct pour un type présent — quantité × prix")
        void shouldReturnCorrectTotal_forExistingType() {
            // plate1 = Pizza 8.50f × 2 = 17.00f  (type SNACK)
            // plate2 = Coca  2.00f × 1 =  2.00f  (type BOISSON)
            float result = orderWithPlates.totalTypeCoast(Type.MAIN);

            assertEquals(17.00f, result, 0.001f,
                    "Pizza 8.50 × 2 = 17.00 pour le type SNACK");
        }

        @Test
        @DisplayName("Retourne 0 si aucun plat ne correspond au type demandé")
        void shouldReturnZero_whenNoPlateMatchesType() {
            float result = orderWithPlates.totalTypeCoast(Type.DESSERT);

            assertEquals(0.0f, result, 0.001f,
                    "Aucun dessert dans la commande → doit retourner 0");
        }

        @Test
        @DisplayName("Retourne 0 pour une commande vide")
        void shouldReturnZero_whenOrderIsEmpty() {
            float result = emptyOrder.totalTypeCoast(Type.SNACK);

            assertEquals(0.0f, result, 0.001f,
                    "Commande vide → total toujours 0 quel que soit le type");
        }

        @Test
        @DisplayName("Prend bien en compte la quantité (numberPlate)")
        void shouldMultiplyByQuantity() {
            // plate2 = Coca 2.00f × 1 = 2.00f
            float result = orderWithPlates.totalTypeCoast(DRINK);

            assertEquals(2.00f, result, 0.001f,
                    "Coca 2.00 × 1 = 2.00 pour le type BOISSON");
        }

        @Test
        @DisplayName("Somme correcte quand plusieurs plats du même type sont commandés")
        void shouldSumAll_whenMultiplePlatesSameType() {
            // Ajout d'un second snack : Burger 5.00f × 3 = 15.00f
            // Total SNACK = Pizza(8.50×2) + Burger(5.00×3) = 17.00 + 15.00 = 32.00
            Plate burger = new Plate(4, "Burger", 5.00f, MAIN);
            OrderPlate opBurger = new OrderPlate(4, burger, 3, orderWithPlates);
            orderWithPlates.getOrderPlates().add(opBurger);

            float result = orderWithPlates.totalTypeCoast(MAIN);

            assertEquals(32.00f, result, 0.001f,
                    "Pizza(8.50×2) + Burger(5.00×3) = 32.00 pour le type SNACK");
        }

        @Test
        @DisplayName("N'inclut pas les plats d'un autre type dans le calcul")
        void shouldIgnore_otherTypes() {
            float snackTotal = orderWithPlates.totalTypeCoast(Type.SNACK);
            float boissonTotal = orderWithPlates.totalTypeCoast(DRINK);

            assertNotEquals(snackTotal + boissonTotal, snackTotal,
                    "Le total SNACK ne doit pas inclure les BOISSONS");
        }
    }

    @Nested
    @DisplayName("mostExpensivePlate()")
    class MostExpensivePlateTests {

        @Test
        @DisplayName("Retourne le plat avec le prix unitaire le plus élevé")
        void shouldReturnMostExpensivePlate() {
            // plate1 = Pizza 8.50f  ← le plus cher
            // plate2 = Coca  2.00f
            Plate result = orderWithPlates.mostExpensivePlate();

            assertEquals(plate1, result,
                    "La Pizza à 8.50 est le plat le plus cher de la commande");
        }

        @Test
        @DisplayName("Retourne null si la commande est vide")
        void shouldReturnNull_whenOrderIsEmpty() {
            Plate result = emptyOrder.mostExpensivePlate();

            assertNull(result,
                    "Commande vide → orElse(null) doit retourner null");
        }

        @Test
        @DisplayName("Retourne le seul plat s'il n'y en a qu'un")
        void shouldReturnOnlyPlate_whenOneOrderPlate() {
            Order singleOrder = new Order(30, LocalDate.now(), client1);
            OrderPlate op = new OrderPlate(30, plate3, 1, singleOrder);
            singleOrder.getOrderPlates().add(op);

            Plate result = singleOrder.mostExpensivePlate();

            assertEquals(plate3, result,
                    "Avec un seul plat, il est forcément le plus cher");
        }

        @Test
        @DisplayName("Retourne bien le plus cher parmi plusieurs plats")
        void shouldReturnCorrectPlate_withManyPlates() {
            // plate1=8.50, plate2=2.00, plate3=5.00
            Order richOrder = new Order(40, LocalDate.now(), client1);
            richOrder.getOrderPlates().add(new OrderPlate(10, plate1, 1, richOrder));
            richOrder.getOrderPlates().add(new OrderPlate(11, plate2, 1, richOrder));
            richOrder.getOrderPlates().add(new OrderPlate(12, plate3, 1, richOrder));

            Plate result = richOrder.mostExpensivePlate();

            assertEquals(plate1, result, "Pizza à 8.50 doit être retournée");
        }
    }

}