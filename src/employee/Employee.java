package employee;

import lombok.Data;

@Data

public class Employee {
    private String eno;
    private String name;
    private double salary;
    private String role;
    private int enterYear;
    private int enterMonth;
    private int enterDay;
    private int lastRaiseYear;

    public Employee(String eno, String name, double salary, String role, int enterYear, int enterMonth, int enterDay, int lastRaiseYear) {
        this.eno = eno;
        this.name = name;
        this.salary = salary;
        this.role = role;
        this.enterYear = enterYear;
        this.enterMonth = enterMonth;
        this.enterDay = enterDay;
        this.lastRaiseYear = lastRaiseYear;
    }
}
