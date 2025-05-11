package MainSystem.model;

// 비서 (Secretary)
public class Secretary extends Employee {
    public Secretary(String eno, String name, int enterYear, int enterMonth, int enterDay, int salary, int lastRaiseYear) {
        super(eno, name, enterYear, enterMonth, enterDay, "Secretary", null, salary, lastRaiseYear);
    }

}
