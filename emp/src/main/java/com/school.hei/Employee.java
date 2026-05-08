package school.hei;

import java.time.LocalDate;

public class Employee {
    int id;
    String firstName;
    String  lastName;
    int hiringYear;
    int salary;
    int rating;

    // Constructeur
    Employee(int id, String firstName, String lastName, int hiringYear, int salary, int rating) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hiringYear = hiringYear;
        this.salary = salary;
        this.rating = rating;
    }

    Employee(int id, String firstName, String lastName, int hiringYear) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hiringYear = hiringYear;
        this.rating = 3;
    }

    Employee(int id, String firstName, String lastName, int hiringYear, int rating) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hiringYear = hiringYear;
        this.rating = rating;
    }

    // Methods
    int countYearsOfService() {
        return LocalDate.now().getYear() - hiringYear;
    }

    boolean isWorkingWell() {
        return rating >= 3;
    }
}