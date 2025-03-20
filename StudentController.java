import java.util.Scanner;

import java.sql.*;

public class StudentController {
    static final String URL = "jdbc:mysql://localhost:3306/university";
    static final String USER = "root";
    static final String PASS = "password";

    public void addStudent(Student student) throws SQLException {
        Connection con = DriverManager.getConnection(URL, USER, PASS);
        String query = "INSERT INTO Student VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, student.getStudentID());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getDepartment());
            pstmt.setInt(4, student.getMarks());
            pstmt.executeUpdate();
            System.out.println("Student Added Successfully!");
        }
        con.close();
    }
}


public class Student {
    private int studentID;
    private String name;
    private String department;
    private int marks;

    public Student(int studentID, String name, String department, int marks) {
        this.studentID = studentID;
        this.name = name;
        this.department = department;
        this.marks = marks;
    }

    public int getStudentID() { return studentID; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public int getMarks() { return marks; }
}

public class StudentView {
    public static void main(String[] args) {
        StudentController controller = new StudentController();
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Department: ");
        String department = sc.nextLine();
        System.out.print("Enter Marks: ");
        int marks = sc.nextInt();

        Student student = new Student(id, name, department, marks);

        try {
            controller.addStudent(student);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
