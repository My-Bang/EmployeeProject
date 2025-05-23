package MainSystemV1.io;

import MainSystemV1.model.Employee;

import java.util.List;

public interface EmployeeOutput {
    Employee getEmployeeById(String eno);   // 특정 ID로 직원 조회
    List<Employee> getAllEmployees();   // 모든 직원 조회
}
