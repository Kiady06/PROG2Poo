package utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class UtilsTest {

    @Test
    void testCountAdults_MixedAges() {
        int result = AgeUtils.countAdults(12, 18, 30, 15, 22);
        assertEquals(3, result, "Should count 3 adults (18, 30, 22)");
    }

    @Test
    void testCountAdults_OnlyMinors() {
        int result = AgeUtils.countAdults(5, 10, 17);
        assertEquals(0, result, "Should count 0 adults");
    }

    @Test
    void testCountAdults_OnlyAdults() {
        int result = AgeUtils.countAdults(25, 40, 18);
        assertEquals(3, result, "Should count all 3 as adults");
    }

    @Test
    void testCountAdults_EmptyArray() {
        int result = AgeUtils.countAdults();
        assertEquals(0, result, "Should return 0 for an empty input");
    }

    @Test
    void testCountAdults_NullArray() {
        int result = AgeUtils.countAdults((int[]) null);
        assertEquals(0, result, "Should return 0 for a null input");
    }

    @Test
    void testCountAdults_NegativeAge_ThrowsException() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class, 
            () -> AgeUtils.countAdults(20, -5, 35)
        );
        assertEquals("Input should only contain positive integers", exception.getMessage());
    }

    @Test
    void testGetRetakeExams_WithFailedGrades() {
        // Préparation des données
        Map<String, Double> grades = new HashMap<>();
        grades.put("Math", 8.5);
        grades.put("Physics", 14.0);
        grades.put("History", 9.2);

        ExamUtils.Student student = new ExamUtils.Student("John", "Doe", grades);

        // Exécution de la méthode
        List<String> retakes = ExamUtils.getRetakeExams(student);

        // Vérifications
        assertEquals(List.of("Math", "History"), retakes);
    }

    @Test
    void testGetRetakeExams_NoRetakes() {
        // Préparation des données : toutes les notes sont supérieures ou égales à 10
        Map<String, Double> grades = new HashMap<>();
        grades.put("Math", 12.0);
        grades.put("Physics", 15.5);

        ExamUtils.Student student = new ExamUtils.Student("Jane", "Smith", grades);

        List<String> retakes = ExamUtils.getRetakeExams(student);

        assertTrue(retakes.isEmpty(), "Aucun examen à repasser, la liste devrait être vide.");
    }

    @Test
    void testGetRetakeExams_EmptyGrades() {
        // Préparation des données : Map vide
        Map<String, Double> grades = new HashMap<>();
        ExamUtils.Student student = new ExamUtils.Student("Alex", "Jones", grades);

        List<String> retakes = ExamUtils.getRetakeExams(student);

        assertTrue(retakes.isEmpty(), "La liste devrait être vide si la map de notes est vide.");
    }

    @Test
    void testGetRetakeExams_NullGrades() {
        ExamUtils.Student student = new ExamUtils.Student("Alice", "Brown", null);

        List<String> retakes = ExamUtils.getRetakeExams(student);

        assertTrue(retakes.isEmpty(), "La méthode doit gérer le cas où la map est nulle et retourner une liste vide.");
    }
    
    @Test
    void testGetRetakeExams_WithInvalidGrades() {
        Map<String, Double> grades = new HashMap<>();
        grades.put("Math", null);
        grades.put("Physics", Double.NaN);
        grades.put("History", 9.5);

        ExamUtils.Student student = new ExamUtils.Student("Bob", "White", grades);

        List<String> retakes = ExamUtils.getRetakeExams(student);

        assertEquals(1, retakes.size(), "Seul l'examen valide et inférieur à 10 doit être retourné.");
        assertTrue(retakes.contains("History"));
    }

    @Test
    void testGetIvandryCount_ValidInputs() {
        int count = IvandryUtils.getIvandryCount("Hello Ivandry", "Ivandry", "No match here");
        assertEquals(2, count, "Should find the word 'Ivandry' twice.");
    }

    @Test
    void testGetIvandryCount_EmptyOrNullInputs() {
        assertEquals(0, IvandryUtils.getIvandryCount());
        assertEquals(0, IvandryUtils.getIvandryCount((String[]) null));
    }
}