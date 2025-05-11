package employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Db {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        ResultSet rs = null;
        // 1. JDBC 드라이버 등록 : MYSQL DB 접근 하기 위한 드라이버 등록
        Class.forName("com.mysql.cj.jdbc.Driver");


        // 2. Mysql DB에 연결객체 얻어와서 연결하기
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ssgdb?serverTimezone=Asia/Seoul", "ssg", "ssg1234");
        //System.out.println("Connection Ok!" + connection);
        return connection;
    }
    public static void main(String[] args) throws  ClassNotFoundException {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        PayRaiseRate payRaiseRate = new PayRaiseRate();

        List<Employee> employees = employeeDAO.getAllEmployees();
        System.out.println(employeeDAO.getAllEmployees());

        for (Employee employee : employees) {
            payRaiseRate.applyRaise(employee);

        }

    }
}
