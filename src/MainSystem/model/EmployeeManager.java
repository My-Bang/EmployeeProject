package MainSystem.model;

import MainSystem.model.dao.EmployeeDBIO;

import java.sql.SQLException;
import java.util.List;

public class EmployeeManager {
    private static EmployeeManager instance;
    private static EmployeeDBIO employeeDBIO;

    public static EmployeeManager getInstance() {
        if (instance == null) {
            instance = new EmployeeManager();
            employeeDBIO = new EmployeeDBIO();
        }

        return instance;
    }

    public boolean addEmployee(Employee employee) {
        if (employee == null || employee.getEno().isEmpty()) {
            System.out.println("올바른 직원 정보가 아닙니다.");
            return false;
        }

        return employeeDBIO.addEmployee(employee);
    }

    public boolean updateEmployee(Employee employee) throws SQLException {
        if (employee == null || employee.getEno().isEmpty()) {
            System.out.println("올바른 직원 정보가 아닙니다.");
            return false;
        }

        return employeeDBIO.updateEmployee(employee);
    }

    public Employee getEmployeeById(String eno) {
        return employeeDBIO.getEmployeeById(eno);
    }

    public List<Employee> getAllEmployees() {
        return employeeDBIO.getAllEmployees();
    }

    public List<Employee> searchEmployeesByName(String name) {
        return employeeDBIO.searchEmployeesByName(name);
    }

    public List<Employee> searchEmployeesByRole(String role) {
        return employeeDBIO.searchEmployeesByRole(role);
    }

    public List<Employee> getUnassignedSecretaries() {
        return employeeDBIO.getUnassignedSecretaries();
    }

    public boolean selectUpdateEmployee(Employee employee){
        return employeeDBIO.selectUpdateEmployee(employee);
    }
}
