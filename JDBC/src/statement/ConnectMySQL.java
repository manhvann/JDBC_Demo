package statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectMySQL {

    public static Connection getConnection(){
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/sampledb", "root", "123456");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
