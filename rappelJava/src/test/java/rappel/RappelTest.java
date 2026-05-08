package rappel;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class RappelTest {

    // ─── countAdults ────────────────────────────────────────────────────────

    @Test
    @DisplayName("should return 0 when all values are under 18")
    void countAdults_allUnder18() {
        assertEquals(0, AgeUtils.countAdults(1, 5, 10, 17));
    }

    @Test
    @DisplayName("should correctly count when all are adults")
    void countAdults_allAdults() {
        assertEquals(3, AgeUtils.countAdults(18, 25, 40));
    }

    @Test
    @DisplayName("should return 3 for this list: [18, 10, 12, 25, 33]")
    void countAdults_listOf5() {
        assertEquals(3, AgeUtils.countAdults(Arrays.asList(18, 10, 12, 25, 33)));
    }

    @Test
    @DisplayName("should return 3 for these numbers: 18, 10, 12, 25, 33")
    void countAdults_varargs5() {
        assertEquals(3, AgeUtils.countAdults(18, 10, 12, 25, 33));
    }

    @Test
    @DisplayName("should return 0 for an empty list")
    void countAdults_emptyList() {
        assertEquals(0, AgeUtils.countAdults(Collections.emptyList()));
    }

    @Test
    @DisplayName("should return 0 for no argument")
    void countAdults_noArgs() {
        assertEquals(0, AgeUtils.countAdults());
    }

    @Test
    @DisplayName("should return 1 for the number 18")
    void countAdults_single18() {
        assertEquals(1, AgeUtils.countAdults(18));
    }

    @Test
    @DisplayName("should return 1 for a one-element list: [18]")
    void countAdults_singleElementList() {
        assertEquals(1, AgeUtils.countAdults(List.of(18)));
    }

    @Test
    @DisplayName("should throw if a value is negative")
    void countAdults_negativeThrows() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> AgeUtils.countAdults(1, -67, 7));
        assertEquals("Input should only contain positive integers", ex.getMessage());
    }

    // ─── getIvandryCount ────────────────────────────────────────────────────

    @Test
    @DisplayName("should return 2 for ['Tennis Ivandry', 'La City Ivandry', 'Mausolée']")
    void ivandry_listOf3() {
        List<String> input = Arrays.asList("Tennis Ivandry", "La City Ivandry", "Mausolée");
        assertEquals(2, IvandryUtils.getIvandryCount(input));
    }

    @Test
    @DisplayName("should be case-insensitive")
    void ivandry_caseInsensitive() {
        assertEquals(3, IvandryUtils.getIvandryCount("IVANDRY", "ivandry", "Ivandry"));
    }

    @Test
    @DisplayName("should return 0 if 'Ivandry' is not present")
    void ivandry_notPresent() {
        assertEquals(0, IvandryUtils.getIvandryCount("Analamahitsy", "Mausolée", "Ambohitrarahaba"));
    }

    @Test
    @DisplayName("should return 0 for empty list or no arguments")
    void ivandry_emptyOrNoArgs() {
        assertEquals(0, IvandryUtils.getIvandryCount(Collections.emptyList()));
        assertEquals(0, IvandryUtils.getIvandryCount(new String[0]));
    }

    @Test
    @DisplayName("should correctly count varargs")
    void ivandry_varargs() {
        assertEquals(2, IvandryUtils.getIvandryCount("Hôtel Ivandry", "Chez Ivandry", "Antanimena"));
    }

    @Test
    @DisplayName("should return 1 for a single string 'Ivandry'")
    void ivandry_single() {
        assertEquals(1, IvandryUtils.getIvandryCount("Ivandry"));
    }

    @Test
    @DisplayName("should not count 'Ivandryland' (word boundary)")
    void ivandry_wordBoundary() {
        assertEquals(0, IvandryUtils.getIvandryCount("Ivandryland"));
    }

    @Test
    @DisplayName("should throw if an element is not a string (null)")
    void ivandry_nullElementThrows() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> IvandryUtils.getIvandryCount(Arrays.asList("Ivandry", null)));
        assertEquals("Inputs should be strings", ex.getMessage());
    }

    // ─── getRetakeExams ─────────────────────────────────────────────────────

    private ExamUtils.Student makeStudent(Map<String, Double> grades) {
        return new ExamUtils.Student("Krysten", "Comerightback", grades);
    }

    @Test
    @DisplayName("should return empty list when all grades are passing (>=10)")
    void retake_allPassing() {
        ExamUtils.Student s = makeStudent(Map.of("math", 12.0, "english", 15.0, "science", 10.0));
        assertEquals(Collections.emptyList(), ExamUtils.getRetakeExams(s));
    }

    @Test
    @DisplayName("should return all subjects when all grades are failing (<10)")
    void retake_allFailing() {
        ExamUtils.Student s = makeStudent(Map.of("math", 5.0, "english", 8.0, "science", 3.0));
        List<String> result = ExamUtils.getRetakeExams(s);
        assertTrue(result.containsAll(Arrays.asList("math", "english", "science")));
        assertEquals(3, result.size());
    }

    @Test
    @DisplayName("should return only subjects with grades under 10")
    void retake_someFailing() {
        ExamUtils.Student s = makeStudent(Map.of("math", 12.0, "english", 7.0, "science", 9.0, "history", 14.0));
        List<String> result = ExamUtils.getRetakeExams(s);
        assertTrue(result.containsAll(Arrays.asList("english", "science")));
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("should return empty list when grades object is empty")
    void retake_emptyGrades() {
        ExamUtils.Student s = makeStudent(Collections.emptyMap());
        assertEquals(Collections.emptyList(), ExamUtils.getRetakeExams(s));
    }

    @Test
    @DisplayName("should handle borderline value 10 as passing grade")
    void retake_borderline() {
        ExamUtils.Student s = makeStudent(Map.of("math", 10.0, "english", 9.0, "science", 11.0));
        List<String> result = ExamUtils.getRetakeExams(s);
        assertEquals(List.of("english"), result);
    }

    @Test
    @DisplayName("should work with a single failing subject")
    void retake_singleFailing() {
        ExamUtils.Student s = makeStudent(Map.of("math", 6.5));
        assertEquals(List.of("math"), ExamUtils.getRetakeExams(s));
    }
}
