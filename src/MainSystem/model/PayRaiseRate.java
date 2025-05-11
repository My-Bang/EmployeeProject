package MainSystem.model;


import MainSystem.model.dao.EmployeeDBIO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
/**
 * 직원의 연봉 인상을 관리하는 클래스입니다.
 * 직원의 근무 연차에 따라 특정 기준을 충족하면 연봉이 자동으로 인상됩니다.
 */
public class PayRaiseRate {
    private EmployeeDBIO employeeDBIO = new EmployeeDBIO();
    /**
     * 직원의 연봉을 인상하는 메서드입니다.
     *
     * @param employee 연봉 인상을 적용할 직원 객체
     * @throws SQLException 데이터베이스 업데이트 중 오류 발생 시 예외 처리
     */
    public void applyRaise(Employee employee) throws SQLException {
        double oldSalary = employee.getSalary();
        double newSalary = oldSalary;

        LocalDate currentDate = LocalDate.now();
        LocalDate hireDate = LocalDate.of(employee.getEnterYear(), employee.getEnterMonth(), employee.getEnterDay());

        long daysWorked = ChronoUnit.DAYS.between(hireDate, currentDate);
        int yearsWorked = (int) (daysWorked / 365); // 근무한 총 연차
        int lastRaiseYear = employee.getLastRaiseYear(); // 마지막 연봉 인상 연차

        // 연차가 증가했을 때만 연봉 인상
        if (yearsWorked > lastRaiseYear && yearsWorked % 3 == 0) {
            double raiseRate = getRaiseRate(employee.getRole(), yearsWorked);

            newSalary = oldSalary + (oldSalary * raiseRate);
            employee.setSalary((int) newSalary);
            employee.setLastRaiseYear(yearsWorked);

            employeeDBIO.updateEmployee(employee);

        }
    }
    /**
     * 직원의 직급과 근무 연차에 따라 연봉 인상률을 반환하는 메서드입니다.
     *
     * @param role 직원의 직급 (Manager, Staff, Secretary)
     * @param yearsWorked 직원의 근무 연차
     * @return 연봉 인상 비율 (예: 0.10 = 10% 인상)
     */
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
