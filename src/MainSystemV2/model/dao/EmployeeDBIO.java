package MainSystemV2.model.dao;

import MainSystemV2.Util.DBUtil;
import MainSystemV2.io.EmployeeIO;
import MainSystemV2.model.Employee;
import MainSystemV2.model.ObjectIO;

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

}
