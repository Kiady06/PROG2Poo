package school.hei;

import java.util.ArrayList;
import java.util.List;

public class Departement {
    int id;
    String name;
    List<Employee> employees;

    Departement (int id, String name, List<Employee> emps) {
        this.id = id;
        this.name = name;
        this.employees = emps;
    }

    Departement (int id, String name) {
        this.id = id;
        this.name = name;
        this.employees = new ArrayList<>();
    }

    // Methodes
    void showEmps() {
        for (Employee emp : this.employees) {
            System.out.println(emp.firstName);
        }
    }

    Employee addEmployee(Employee emp) {
        this.employees.add(emp);
        return  emp;
    }

    Employee removeEmployee(Employee emp) {
        employees.remove(emp);
        return emp;
    }

    int getTotalSalaryDept() {
        int rsl = 0;
        for (Employee emp : this.employees) {
            rsl += emp.salary;
        }

        return  rsl;
    }
}

/* Methodes :
  - Constructeur (id, nomDept, List<Employee> est une liste vide)
  - addEmployee
  - removeEmployee
  - total salaire des emps du departement
*/
