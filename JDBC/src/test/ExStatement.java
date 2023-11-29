package test;

import java.sql.*;


public class ExStatement extends ConnectMySQL{
    public static void main(String[] args) {
        try{

            Statement statement = connection.createStatement();
            Boolean check = statement.execute("update Employees\n" +
                    "set EmployeeName = 'Manh'\n" +
                    "where EmployeeID = 1");
            System.out.println("check: " + check);

            int numberRow = statement.executeUpdate("update Employees\n" +
                    "set EmployeeName = 'Ali'\n" +
                    "where EmployeeID = 1");
            System.out.println("numberRow : " + numberRow);

            ResultSet resultSet = statement.executeQuery("select * from employees");
            while (resultSet.next()){
                System.out.print(resultSet.getString(1) + " ");
                System.out.println(resultSet.getString("EmployeeName"));
            }



        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
