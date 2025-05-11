package MainSystemV1.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String url = "jdbc:mysql://localhost:3306/ssgdb?serverTimezone=Asia/Seoul";
    private static final String user = "ssg";
    private static final String password = "ssg1234";

    private static Connection getConnection = null;

    public static Connection getConnection() {
        try {
            if (getConnection == null || getConnection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver"); // JDBC 드라이버 로드
                getConnection = DriverManager.getConnection(url, user, password);
                //System.out.println("데이터베이스 연결 성공!");
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC 드라이버를 찾을 수 없습니다.", e);
        } catch (SQLException e) {
            throw new RuntimeException("데이터베이스 연결 실패: " + e.getMessage(), e);
        }

        return getConnection;
    }

    public static void closeConnection() {
        if (getConnection != null) {
            try {
                getConnection.close();
                getConnection = null; // 연결 해제 후 null로 설정하여 재연결 가능하게 만듦
            } catch (SQLException e) {
                throw new RuntimeException("데이터베이스 연결 종료 실패: " + e.getMessage(), e);
            }
        }
    }
}
