package statement;

import java.sql.*;
import java.util.Scanner;


public class ExStatement extends ConnectMySQL{
    public static void main(String[] args) {
        try{
            Connection connection = ConnectMySQL.getConnection();
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


            // SQL injection
//            Scanner sc = new Scanner(System.in);
//            System.out.println("Vui lòng nhập tên: ");
//            String name = sc.nextLine();
//            ResultSet resultSet = statement.executeQuery("select * from employees where EmployeeName = '" + name + "'");
//            if(resultSet.next()){
//                System.out.println("Đăng nhập thành công");
//            }else{
//                System.out.println("Đăng nhập không thành công!Vui lòng nhập lại!!");
//            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
