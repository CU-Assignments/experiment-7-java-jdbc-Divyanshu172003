import java.sql.*;

public class EmployeeData {
    static final String URL = "jdbc:mysql://localhost:3306/company";
    static final String USER = "root";
    static final String PASS = "password"; // Change to your MySQL password

    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = con.createStatement()) {

            String query = "SELECT * FROM Employee";
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("Employee Details:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("EmpID") +
                                   ", Name: " + rs.getString("Name") +
                                   ", Salary: " + rs.getDouble("Salary"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
