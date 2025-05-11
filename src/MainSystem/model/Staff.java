package MainSystem.model;

public class Staff extends Employee {
    public Staff(String eno, String name, int enterYear, int enterMonth, int enterDay, int salary, int lastRaiseYear) {
        super(eno, name, enterYear, enterMonth, enterDay, "Staff", null, salary, lastRaiseYear);
    }


}
