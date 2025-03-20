import java.sql.*;
import java.util.Scanner;

public class ProductCRUD {
    static final String URL = "jdbc:mysql://localhost:3306/shop";
    static final String USER = "root";
    static final String PASS = "password";

    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             Scanner sc = new Scanner(System.in)) {

            System.out.println("1. Insert  2. Read  3. Update  4. Delete");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> insertProduct(con);
                case 2 -> readProducts(con);
                case 3 -> updateProduct(con);
                case 4 -> deleteProduct(con);
                default -> System.out.println("Invalid choice.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertProduct(Connection con) throws SQLException {
        String query = "INSERT INTO Product (ProductID, ProductName, Price, Quantity) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter Product ID: ");
            pstmt.setInt(1, sc.nextInt());
            sc.nextLine(); 
            System.out.print("Enter Product Name: ");
            pstmt.setString(2, sc.nextLine());
            System.out.print("Enter Price: ");
            pstmt.setDouble(3, sc.nextDouble());
            System.out.print("Enter Quantity: ");
            pstmt.setInt(4, sc.nextInt());

            pstmt.executeUpdate();
            System.out.println("Product Inserted Successfully!");
        }
    }

    public static void readProducts(Connection con) throws SQLException {
        String query = "SELECT * FROM Product";
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("Product Details:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("ProductID") +
                                   ", Name: " + rs.getString("ProductName") +
                                   ", Price: " + rs.getDouble("Price") +
                                   ", Quantity: " + rs.getInt("Quantity"));
            }
        }
    }
}
