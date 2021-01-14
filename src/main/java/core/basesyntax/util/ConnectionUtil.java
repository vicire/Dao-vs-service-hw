package core.basesyntax.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {

    public static Connection getConnection() {
        Properties dbProperties = new Properties();
        dbProperties.put("user", "rsaviak");
        dbProperties.put("password", "zxcvb");
        String url = "jdbc:mysql://localhost:3306/taxi_service?serverTimezone=UTC";
        try {
            return DriverManager.getConnection(url, dbProperties);
        } catch (SQLException e) {
            throw new RuntimeException("Can`t connect to your database server" + e);
        }
    }
}
