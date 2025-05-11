package MainSystem.io;

import MainSystem.model.Employee;

import java.util.List;

public interface EmployeeSearch {
    List<Employee>  searchEmployeesByName(String name);         // 이름으로 직원 검색
    List<Employee> searchEmployeesByRole(String role); // 부서별 직원 검색
    List<Employee> getUnassignedSecretaries(); // 임원에게 할당되어있지 않은 비서 목록 반환
}
