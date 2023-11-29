package preparestatement;

import statement.ConnectMySQL;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main extends ConnectMySQL {
    public static void main(String[] args) {
        try {
            Connection connection = ConnectMySQL.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into Employees values(?,?,?)");
            preparedStatement.setInt(1,20);
            preparedStatement.setString(2,"Linh");
            preparedStatement.setBigDecimal(3, BigDecimal.valueOf(15.5));
            preparedStatement.execute();

            String sql = "select * from Employees inner join Projects on Employees.EmployeeID = Projects.EmployeeID where Employees.EmployeeID = ?";
            PreparedStatement preparedStatement1 = connection.prepareStatement(sql);
            preparedStatement1.setInt(1,1);
            ResultSet resultSet = preparedStatement1.executeQuery();
            while (resultSet.next()){
                System.out.println(resultSet.getString("EmployeeName") + " "
                         + resultSet.getString("ProjectName"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
