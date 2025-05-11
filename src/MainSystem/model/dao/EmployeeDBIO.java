package MainSystem.model.dao;

import MainSystem.Util.DBUtil;
import MainSystem.io.EmployeeIO;
import MainSystem.model.Employee;
import MainSystem.model.ObjectIO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//실제로 CRUD 구현 부

public class EmployeeDBIO extends ObjectIO implements EmployeeIO {

    @Override
    public boolean addEmployee(Employee employee) {

        String sql = "" +
                "INSERT INTO EMPLOYEE (eno, name, enteryear, entermonth, enterday, role, secno, salary, lastRaiseYear) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, employee.getEno());
            pstmt.setString(2, employee.getName());
            pstmt.setInt(3, employee.getEnterYear());
            pstmt.setInt(4, employee.getEnterMonth());
            pstmt.setInt(5, employee.getEnterDay());
            pstmt.setString(6, employee.getRole());
            pstmt.setString(7, employee.getSecno());
            pstmt.setInt(8, employee.getSalary());
            pstmt.setInt(9, employee.getLastRaiseYear());

            System.out.println(pstmt.toString());
            int cnt = pstmt.executeUpdate();
            if (cnt > 0) {
                System.out.println("회원 정보 추가 성공");
                return true;
            } else {
                System.out.println("회원 정보 추가 실패");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        DBUtil.getConnection();
        return false;
    }

    @Override
    public boolean updateEmployee(Employee employee) throws SQLException {

        String sql = "UPDATE EMPLOYEE SET name=?, enteryear=?, entermonth=?, enterday=?, role=?, secno=?, salary=?, lastRaiseYear =? WHERE eno=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, employee.getName());
            pstmt.setInt(2, employee.getEnterYear());
            pstmt.setInt(3, employee.getEnterMonth());
            pstmt.setInt(4, employee.getEnterDay());
            pstmt.setString(5, employee.getRole());
            pstmt.setString(6, employee.getSecno());
            pstmt.setInt(7, employee.getSalary());
            pstmt.setInt(8, employee.getLastRaiseYear());
            pstmt.setString(9, employee.getEno());
            pstmt.executeUpdate();
        }

        return false;
    }


    @Override
    public Employee getEmployeeById(String eno) {
        String sql = "SELECT * FROM EMPLOYEE WHERE eno = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, eno);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // 🔹 단순히 테이블의 데이터를 Employee 객체로 변환하여 반환
                return new Employee(
                        rs.getString("eno"),
                        rs.getString("name"),
                        rs.getInt("enteryear"),
                        rs.getInt("entermonth"),
                        rs.getInt("enterday"),
                        rs.getString("role"),  // 그대로 저장
                        rs.getString("secno"),
                        rs.getInt("salary"),
                        rs.getInt("lastRaiseYear")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        DBUtil.getConnection();
        return null; // 직원이 존재하지 않는 경우
    }


    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM EMPLOYEE";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                employees.add(new Employee(
                        rs.getString("eno"),
                        rs.getString("name"),
                        rs.getInt("enteryear"),
                        rs.getInt("entermonth"),
                        rs.getInt("enterday"),
                        rs.getString("role"),
                        rs.getString("secno"),
                        rs.getInt("salary"),
                        rs.getInt("lastRaiseYear")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving employees", e);
        }

        return employees;
    }


    @Override
    public List<Employee> searchEmployeesByName(String name) {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM EMPLOYEE WHERE name LIKE ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + name + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                employees.add(new Employee(
                        rs.getString("eno"),
                        rs.getString("name"),
                        rs.getInt("enteryear"),
                        rs.getInt("entermonth"),
                        rs.getInt("enterday"),
                        rs.getString("role"),
                        rs.getString("secno"),
                        rs.getInt("salary"),
                        rs.getInt("lastRaiseYear")

                ));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        DBUtil.getConnection();
        return employees;
    }

    @Override
    public List<Employee> searchEmployeesByRole(String role) {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM EMPLOYEE WHERE role LIKE ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, role);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                employees.add(new Employee(
                        rs.getString("eno"),
                        rs.getString("name"),
                        rs.getInt("enteryear"),
                        rs.getInt("entermonth"),
                        rs.getInt("enterday"),
                        rs.getString("role"),
                        rs.getString("secno"),
                        rs.getInt("salary"),
                        rs.getInt("lastRaiseYear")

                ));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        DBUtil.getConnection();
        return employees;
    }

    @Override
    public List<Employee> getUnassignedSecretaries()  {
        List<Employee> secretaries = new ArrayList<>();
        String sql = "SELECT * FROM Employee " +
                "WHERE role = 'Secretary' " +
                "AND eno NOT IN (" +
                "   SELECT secno FROM Employee WHERE role = 'Manager' AND secno IS NOT NULL" +
                ")";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Employee emp = new Employee(
                        rs.getString("eno"),
                        rs.getString("name"),
                        rs.getInt("enteryear"),
                        rs.getInt("entermonth"),
                        rs.getInt("enterday"),
                        rs.getString("role"),
                        rs.getString("secno"),
                        rs.getInt("salary"),
                        rs.getInt("lastRaiseYear")
                );
                secretaries.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return secretaries;
    }

    @Override
    public boolean selectUpdateEmployee(Employee employee) {
        String eno = employee.getEno();

        Employee existing = getEmployeeById(eno);
        if (existing == null) { // 독립적 검증
            System.out.println("업데이트 실패: 해당 사번의 직원이 존재하지 않습니다.");
            return false;
        }

        String column = null;
        String newValue = null;

        if (employee.getName() != null && !employee.getName().isEmpty()) { // 문자열이 비어있지 않은지 확인하는 표현식
            column = "name";
            newValue = employee.getName();
        } else if (employee.getEnterYear() != 0) { // 0이 아닌 원래 값이 들어있으면 업데이트 하자
            column = "enteryear";
            newValue = Integer.toString(employee.getEnterYear());
        } else if (employee.getEnterMonth() != 0) {
            column = "entermonth";
            newValue = Integer.toString(employee.getEnterMonth());
        } else if (employee.getEnterDay() != 0) {
            column = "enterday";
            newValue = Integer.toString(employee.getEnterDay());
        } else if (employee.getRole() != null && !employee.getRole().isEmpty()) {
            column = "role";
            newValue = employee.getRole();
        } else if (employee.getSecno() != null && !employee.getSecno().isEmpty()) {
            column = "secno";
            newValue = employee.getSecno();
        } else if (employee.getSalary() != 0) {
            column = "salary";
            newValue = Integer.toString(employee.getSalary());
        } else {
            System.out.println("업데이트할 항목이 지정되지 않았습니다.");
            return false;
        }

        String sql = "UPDATE EMPLOYEE SET " + column + " = ? WHERE eno = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            if (column.equals("enteryear") || column.equals("entermonth") || // 정수형이 들어왔을때 newValue에 정수형으로 받아 오기 위해.
                    column.equals("enterday") || column.equals("salary")) {
                pstmt.setInt(1, Integer.parseInt(newValue));
            } else {
                pstmt.setString(1, newValue);
            }
            pstmt.setString(2, eno);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("업데이트 성공");
                return true;
            } else {
                System.out.println("업데이트 실패");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
