package test;

import java.math.BigDecimal;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetConcurrency extends ConnectMySQL{
    public static void main(String[] args) {

        try {
            DatabaseMetaData dbmd = connection.getMetaData();
            boolean isSupported = dbmd.supportsResultSetConcurrency(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            System.out.println("isSupported : " + isSupported);

            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery("select * from Employees");

            while(resultSet.next()){
                System.out.println("ID:" + resultSet.getInt("EmployeeID")
                                    + "  Name:" + resultSet.getString("EmployeeName")
                                    + "  Salary:" + resultSet.getBigDecimal("Salary"));

            }

            //update row
            resultSet.absolute(1);
            resultSet.updateString("EmployeeName","Alice");
            resultSet.updateRow();

            //insert row
            resultSet.moveToInsertRow();
            resultSet.updateInt("EmployeeID",15);
            resultSet.updateString("EmployeeName","Bone");
            resultSet.updateBigDecimal("Salary", BigDecimal.valueOf(12.5));
            resultSet.insertRow();


            System.out.println();
            resultSet.beforeFirst();
            while(resultSet.next()){
                System.out.println("ID:" + resultSet.getInt("EmployeeID")
                        + "  Name:" + resultSet.getString("EmployeeName")
                        + "  Salary:" + resultSet.getBigDecimal("Salary"));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
