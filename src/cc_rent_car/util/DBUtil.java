package cc_rent_car.util;

import java.sql.Connection;

public class DBUtil {
    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/dzyxxx";
    private static final String dbUser = "root";
    private static final String dbPwd = "123";

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws java.sql.SQLException {
        return java.sql.DriverManager.getConnection(jdbcUrl, dbUser, dbPwd);
    }
}
