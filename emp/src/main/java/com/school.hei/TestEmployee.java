package school.hei;

public class TestEmployee {
    public static void main() {
        // Instancier un objet
        var e = new Employee(1, "Krysten", "Comerightback",2022);
        e.salary = 1_000_000;
        e.rating = 4;

        System.out.println(e.countYearsOfService());
        System.out.println(e.isWorkingWell());
    }
}
