package MainSystemV2.controller;

import MainSystemV2.model.Employee;
import MainSystemV2.model.EmployeeManager;
import MainSystemV2.model.PayRaiseRate;
import MainSystemV2.model.dao.EmployeeDBIO;
import MainSystemV2.view.EmployeeView;

import java.sql.SQLException;
import java.util.List;

public class EmployeeController {
    private EmployeeManager employeeManager;
    private EmployeeView employeeView;

    // 생성자
    public EmployeeController(EmployeeView employeeView) {
        this.employeeManager = EmployeeManager.getInstance(); // 싱글톤 사용
        this.employeeView = employeeView;
    }

    // 직원 추가 요청 처리
    public void addEmployee(Employee employee) {
        boolean result = employeeManager.addEmployee(employee);
        if (result) {
            employeeView.displayMessage("직원 추가 성공: " + employee.getName());
        } else {
            employeeView.displayMessage("직원 추가 실패.");
        }
    }

    // 직원 정보 업데이트 요청 처리
    public void updateEmployee(Employee employee) throws SQLException {
        boolean result = employeeManager.updateEmployee(employee);
        if (result) {
            employeeView.displayMessage("직원 정보 업데이트 성공: " + employee.getName());
        } else {
            employeeView.displayMessage("직원 정보 업데이트 실패.");
        }
    }

    // 특정 직원 조회 요청 처리
    public void getEmployeeById(String eno) {
        Employee employee = employeeManager.getEmployeeById(eno);
        if (employee != null) {
            employeeView.displayEmployee(employee);
        } else {
            employeeView.displayMessage("직원 정보를 찾을 수 없습니다.");
        }
    }

    // 모든 직원 목록 조회 요청 처리
    public void listAllEmployees() {
        List<Employee> employees = employeeManager.getAllEmployees();
        employeeView.displayEmployees(employees);
    }

    // 직원 이름으로 검색 요청 처리
    public void searchEmployeeByName(String name) {
        List<Employee> employees = employeeManager.searchEmployeesByName(name);
        employeeView.displayEmployees(employees);
    }

    // 직원 역할별 검색 요청 처리
    public void searchEmployeesByRole(String role) {
        List<Employee> employees = employeeManager.searchEmployeesByRole(role);
        employeeView.displayEmployees(employees);
    }


    public void updateSalary() throws SQLException {
        EmployeeDBIO employeeDBIO = new EmployeeDBIO();
        PayRaiseRate payRaiseRate = new PayRaiseRate();
        List<Employee> employees = employeeDBIO.getAllEmployees();
        for (Employee employee : employees) {
            payRaiseRate.applyRaise(employee);
        }
    }
}
