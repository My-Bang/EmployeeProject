package MainSystemV1.view;

import MainSystemV1.model.Employee;
import java.util.List;

public class EmployeeView {
    // 단일 직원 정보 출력
    public void displayEmployee(Employee employee) {
        if (employee == null) {
            System.out.println("해당 직원 정보를 찾을 수 없습니다.");
            return;
        }
        System.out.println("\n=== 직원 정보 =============================");
        System.out.println("ID        : " + employee.getEno());
        System.out.println("이름      : " + employee.getName());
        System.out.println("입사 연도 : " + employee.getEnterYear());
        System.out.println("입사 월   : " + employee.getEnterMonth());
        System.out.println("입사 일   : " + employee.getEnterDay());
        System.out.println("직급      : " + employee.getRole());
        System.out.println("비서 번호 : " + (employee.getSecno() != null ? employee.getSecno() : "없음"));
        System.out.println("급여      : " + employee.getSalary() + " 원");
        System.out.println("==========================================\n");
    }

    // 직원 목록 출력
    public void displayEmployees(List<Employee> employees) {
        if (employees.isEmpty()) {
            System.out.println("직원 목록이 없습니다.");
            return;
        }

        // 컬럼 헤더 출력
        System.out.println("\n=== 직원 목록 ===");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("%-10s | %-10s | %-10s | %-10s | %-10s | %-10s\n", "사번", "이름", "입사일", "직급", "비서번호", "월급");
        System.out.println("--------------------------------------------------------------------------------");

        // 직원 정보 출력
        for (Employee e : employees) {
            System.out.printf("%-10s | %-10s | %4d-%02d-%02d | %-10s | %-10s | %,10d 원\n",
                    e.getEno(), e.getName(), e.getEnterYear(), e.getEnterMonth(), e.getEnterDay(),
                    e.getRole(), (e.getSecno() != null ? e.getSecno() : "없음"), e.getSalary());
        }
        System.out.println("================================================================================\n");
    }

    // 메시지 출력
    public void displayMessage(String message) {
        System.out.println("\n[알림] " + message + "\n");
    }
}