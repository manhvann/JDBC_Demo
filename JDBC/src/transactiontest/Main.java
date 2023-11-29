package transactiontest;

import statement.ConnectMySQL;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main extends ConnectMySQL {
    public static void main(String[] args) {
        try {
            Connection connection = ConnectMySQL.getConnection();
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            String insert1 = "insert into Employees values (30,'Manh',15.6)";
            String insert2 = "insert into Employees values (0,'Manh',15.6)";
            try{
                statement.executeUpdate(insert1);
                statement.executeUpdate(insert2);
                System.out.println("success");
                connection.commit();
            }catch (Exception e){
                e.printStackTrace();
                connection.rollback();
                System.out.println("rollback");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
