package MainSystemV1.model;


import MainSystemV1.model.dao.EmployeeDBIO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PayRaiseRate {
    private EmployeeDBIO employeeDBIO = new EmployeeDBIO();

    public void applyRaise(Employee employee) throws SQLException {
        double oldSalary = employee.getSalary();
        double newSalary = oldSalary;

        LocalDate currentDate = LocalDate.now();
        LocalDate hireDate = LocalDate.of(employee.getEnterYear(), employee.getEnterMonth(), employee.getEnterDay());

        long daysWorked = ChronoUnit.DAYS.between(hireDate, currentDate);
        int yearsWorked = (int) (daysWorked / 365); // 근무한 총 연차
        int lastRaiseYear = employee.getLastRaiseYear(); // 마지막 연봉 인상 연차

        // 연차가 증가했을 때만 연봉 인상
        if (yearsWorked > lastRaiseYear && yearsWorked % 3 == 0 || lastRaiseYear == 0 && yearsWorked >= 3) {
            double raiseRate = getRaiseRate(employee.getRole(), yearsWorked);

            newSalary = oldSalary + (oldSalary * raiseRate);
            employee.setSalary((int) newSalary);
            employee.setLastRaiseYear(yearsWorked);

            employeeDBIO.updateEmployee(employee);

        }
    }

    private double getRaiseRate(String role, int yearsWorked) {
        switch (role) {
            case "Manager":
                if (yearsWorked >= 9) return 0.20;          // 9년차 이상은 20프로 인상
                else if (yearsWorked >= 6) return 0.15;     // 6년차 이상은 15프로
                else if (yearsWorked >= 3) return 0.10;     // 3년차 이상은 10프로
                break;
            case "Staff":
                if (yearsWorked >= 9) return 0.18;
                else if (yearsWorked >= 6) return 0.12;
                else if (yearsWorked >= 3) return 0.08;
                break;
            case "Secretary":
                if (yearsWorked >= 9) return 0.18;
                else if (yearsWorked >= 6) return 0.13;
                else if (yearsWorked >= 3) return 0.09;
                break;
        }
        return 0;
    }

}
