package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectMySQL {
    public static final Connection connection;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampledb", "root", "123456");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ConnectMySQL() {
    }
}
