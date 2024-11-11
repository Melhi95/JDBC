package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://mysite.com:3306/usersdb";
        String user = "login";
        String password = "pass";
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
}
