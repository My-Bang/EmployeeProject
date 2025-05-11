package MainSystem.io;
import MainSystem.model.Employee;

import java.sql.SQLException;

public interface EmployeeInput {
    boolean addEmployee(Employee employee);   // 직원 추가
    boolean updateEmployee(Employee employee) throws SQLException; // 직원 정보 수정
    boolean selectUpdateEmployee(Employee employee);
}
