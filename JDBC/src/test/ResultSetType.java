package test;

import java.math.BigDecimal;
import java.sql.*;

public class ResultSetType extends ConnectMySQL{
    public static void main(String[] args) {
        try{

            // Tạo Statement với TYPE_SCROLL_SENSITIVE
            Statement insensitiveStatement = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            // Tạo ResultSet từ Statemen
            ResultSet insensitiveResultSet = insensitiveStatement.executeQuery("SELECT * FROM employees");
            // Hiển thị dữ liệu ban đầu
            while (insensitiveResultSet.next()) {
                System.out.println("Name: " + insensitiveResultSet.getString("EmployeeName") + ", Salary: " + insensitiveResultSet.getInt("Salary"));
            }

            // Thêm một nhân viên mới vào cơ sở dữ liệu
            Statement statement2 = connection.createStatement();
            statement2.executeUpdate("insert into Employees values (12,'David9', 80000)");

            insensitiveResultSet.beforeFirst();
            System.out.println("After Insensitive Update:");
            while (insensitiveResultSet.next()) {
                System.out.println("Name: " + insensitiveResultSet.getString("EmployeeName") + ", Salary: " + insensitiveResultSet.getInt("Salary"));
            }

            DatabaseMetaData dbmd = connection.getMetaData();
            boolean isSupported = dbmd.supportsResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE);
            System.out.println("isSupported : " + isSupported);

        }catch (SQLException e){
            e.printStackTrace();
        }


    }
}
