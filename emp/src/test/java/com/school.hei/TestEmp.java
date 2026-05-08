package school.hei;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class TestEmp {

    @Test
    public void testIsWorkingWell() {
        // GIVEN: Un employé avec une note de 4
        Employee emp = new Employee(1, "John", "Doe", 2020, 3000, 4);

        // WHEN: On vérifie s'il travaille bien
        boolean result = emp.isWorkingWell();

        // THEN: Le résultat doit être vrai
        assertTrue(result);
    }

    @Test
    public void testCountYearsOfService() {
        // GIVEN: Un employé recruté il y a 5 ans
        int hiringYear = LocalDate.now().getYear() - 5;
        Employee emp = new Employee(1, "John", "Doe", hiringYear);

        // WHEN: On calcule ses années de service
        int years = emp.countYearsOfService();

        // THEN: On doit obtenir 5
        assertEquals(5, years);
    }
}
