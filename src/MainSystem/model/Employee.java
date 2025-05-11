package MainSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employee {
    private String eno;
    private String name;
    private int enterYear;
    private int enterMonth;
    private int enterDay;
    private String role;
    private String secno;
    private int salary;
    private int lastRaiseYear;


}
