package org.HEI.kdot;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Nested
    class KeywordTests {

        @Test
        void shouldMatchFirstname() {
            Client c = new Client(1, "John", "Doe", "0321111111");

            assertTrue(c.hasKeyWord("Jo"));
        }

        @Test
        void shouldMatchLastname() {
            Client c = new Client(1, "John", "Doe", "0321111111");

            assertTrue(c.hasKeyWord("Do"));
        }

        @Test
        void shouldBeCaseInsensitive() {
            Client c = new Client(1, "John", "Doe", "0321111111");

            assertTrue(c.hasKeyWord("jOhN"));
        }

        @Test
        void shouldReturnTrueWhenKeywordNull() {
            Client c = new Client(1, "John", "Doe", "0321111111");

            assertTrue(c.hasKeyWord(null));
        }
    }

    @Nested
    class OperatorTests {

        @Test
        void shouldDetectOrange() {
            Client c = new Client(1, "John", "Doe", "0321111111");

            assertEquals(Operator.ORANGE, c.getOperator());
        }

        @Test
        void shouldDetectTelma() {
            Client c = new Client(1, "John", "Doe", "0341111111");

            assertEquals(Operator.TELMA, c.getOperator());
        }

        @Test
        void shouldReturnNullForInvalidNumber() {
            Client c = new Client(1, "John", "Doe", "999999999");

            assertNull(c.getOperator());
        }
    }
}