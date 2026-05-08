package school.hei;

public class TestDepartement {
    public static void main() {
        // Instancier un objet
        var krysten = new Employee(1, "Krysten", "Comerightback",2022, 2_000_000, 4);
        var hope = new Employee(2, "Hope", "Sandoval",2017, 4_000_000, 5);
        var cobain = new Employee(3, "Kurt", "Cobain",2000, 4_000_000, 1);

        var dept = new Departement(1, "Fire");
//        System.out.println("Le dept instancie : " + dept.name);

        dept.addEmployee(krysten);
        dept.addEmployee(hope);
        dept.addEmployee(cobain);
        dept.showEmps();

//        dept.removeEmployee(cobain);
//        dept.showEmps();
//
//        dept.removeEmployee(krysten);
//        dept.showEmps();

        System.out.println(dept.getTotalSalaryDept());
    }
}
