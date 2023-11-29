package callablestatement;

import statement.ConnectMySQL;

import java.math.BigDecimal;
import java.sql.*;

public class Main  extends ConnectMySQL {
    public static void main(String[] args) {
        try {
            Connection connection = ConnectMySQL.getConnection();
            // Accepts 3 In param and 1 Out param
            CallableStatement callableStatement = connection.prepareCall("{call insertEmployee(?,?,?,?)}");
            callableStatement.setInt(1,26);
            callableStatement.setString(2,"Kanh");
            callableStatement.setBigDecimal(3, BigDecimal.valueOf(12.5));
            callableStatement.registerOutParameter(4, Types.VARCHAR);
            callableStatement.execute();
            String x = callableStatement.getString(4);
            System.out.println("x = " + x);



            //There are no arguments and no return value
            CallableStatement callableStatement1 = connection.prepareCall("{call showEmployee()}");
            ResultSet resultSet = callableStatement1.executeQuery();
            System.out.println("Employee's name list ");
            while (resultSet.next()){
                System.out.println(resultSet.getString("EmployeeName"));
            }


            //Accepts 1 argument and has a return value
            CallableStatement callableStatement2 = connection.prepareCall("{? = call GetEmployeeName(?)}");
            callableStatement2.registerOutParameter(1, Types.VARCHAR); // Register the return type
            callableStatement2.setInt(2, 0); // Set the value for the input parameter
            callableStatement2.execute();
            String employeeName = callableStatement2.getString(1); // Get the return value
            System.out.println("Employee Name: " + employeeName);



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
