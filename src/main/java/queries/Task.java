package queries;

import objects.Department;
import objects.Reviewer;
import objects.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Task {
    private static void createStudentsTable(Connection conn){
        String query =
        """
        CREATE TABLE IF NOT EXISTS students(
        id INT PRIMARY KEY NOT NULL,
        name CHAR(127) NOT NULL,
        surname CHAR(127) NOT NULL
        );
        """;

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.executeQuery();
        }
        catch (SQLException ex) {
            System.out.printf("Statement execution error: %s\n", ex);
        }
    }

    private static void createDepartmentsTable(Connection conn){
        String query = """
        CREATE TABLE IF NOT EXISTS departments(
        id INT PRIMARY KEY NOT NULL,
        name CHAR(127) NOT NULL
        );
        """;

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.executeQuery();
        }
        catch (SQLException ex) {
            System.out.printf("Statement execution error: %s\n", ex);
        }
    }

    private static void createReviewersTable(Connection conn){
        String query = """
        CREATE TABLE IF NOT EXISTS reviewers(
        id INT PRIMARY KEY NOT NULL,
        name CHAR(127) NOT NULL,
        surname CHAR(127) NOT NULL,
        departmentID INT,
        FOREIGN KEY(departmentID) REFERENCES departments(id)
        );
        """;

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.executeQuery();
        }
        catch (SQLException ex) {
            System.out.printf("Statement execution error: %s\n", ex);
        }
    }

    private static void createStudentsPrioritiesTable(Connection conn){
        String query = """
        CREATE TABLE IF NOT EXISTS studentsPriorities(
        studentID INT PRIMARY KEY,
        pr1 INT,
        pr2 INT,
        pr3 INT,
        FOREIGN KEY(studentID) REFERENCES students(id),
        FOREIGN KEY(pr1) REFERENCES reviewers(id),
        FOREIGN KEY(pr2) REFERENCES reviewers(id),
        FOREIGN KEY(pr3) REFERENCES reviewers(id)
        );
        """;

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.executeQuery();
        }
        catch (SQLException ex) {
            System.out.printf("Statement execution error: %s\n", ex);
        }
    }

    private static void createReviewersPrioritiesTable(Connection conn){
        String query =
                """
                CREATE TABLE IF NOT EXISTS reviewersPriorities(
                reviewerID INT PRIMARY KEY,
                pr1 INT,
                pr2 INT,
                pr3 INT,
                pr4 INT,
                pr5 INT,
                FOREIGN KEY(reviewerID) REFERENCES reviewers(id),
                FOREIGN KEY(pr1) REFERENCES students(id),
                FOREIGN KEY(pr2) REFERENCES students(id),
                FOREIGN KEY(pr3) REFERENCES students(id),
                FOREIGN KEY(pr4) REFERENCES students(id),
                FOREIGN KEY(pr5) REFERENCES students(id));
                """;
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.executeQuery();
        }
        catch (SQLException ex) {
            System.out.printf("Statement execution error: %s\n", ex);
        }
    }

    public static void createTables(Connection conn){
        createStudentsTable(conn);
        createDepartmentsTable(conn);
        createReviewersTable(conn);
        createStudentsPrioritiesTable(conn);
        createReviewersPrioritiesTable(conn);
    }

    public static void insertStudents(Connection conn, ArrayList<Student> students) {
        String query = """
        INSERT INTO students(id, name, surname) VALUES (?, ?, ?);
        """;
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            for (Student student : students) {
                statement.setInt(1, student.getId());
                statement.setString(2, student.getName());
                statement.setString(3, student.getSurname());
                statement.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.printf("Error inserting into students: %s\n", ex);
        }
    }

    public static void insertDepartments(Connection conn, ArrayList<Department> departments) {
        String query = """
        INSERT INTO departments(id, name) VALUES (?, ?);
        """;
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            for (Department department : departments) {
                statement.setInt(1, department.getId());
                statement.setString(2, department.getName());
                statement.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.printf("Error inserting into departments: %s\n", ex);
        }
    }

    public static void insertReviewers(Connection conn, ArrayList<Reviewer> reviewers) {
        String query = """
        INSERT INTO reviewers(id, name, surname, departmentID) VALUES (?, ?, ?, ?);
        """;
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            for (Reviewer reviewer : reviewers) {
                statement.setInt(1, reviewer.getId());
                statement.setString(2, reviewer.getName());
                statement.setString(3, reviewer.getSurname());
                statement.setInt(4, reviewer.getDepartmentID());
                statement.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.printf("Error inserting into reviewers: %s\n", ex);
        }
    }

    public static void insertStudentsPriorities(Connection conn, ArrayList<Student> students) {
        String query = """
        INSERT INTO studentsPriorities(studentID, pr1, pr2, pr3) VALUES (?, ?, ?, ?);
        """;
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            for (Student student : students) {
                statement.setInt(1, student.getId());
                statement.setInt(2, student.getPr1());
                statement.setInt(3, student.getPr2());
                statement.setInt(4, student.getPr3());
                statement.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.printf("Error inserting into studentsPriorities: %s\n", ex);
        }
    }

    public static void insertReviewersPriorities(Connection conn, ArrayList<Reviewer> reviewers) {
        String query = """
        INSERT INTO reviewersPriorities(reviewerID, pr1, pr2, pr3, pr4, pr5) VALUES (?, ?, ?, ?, ?, ?);
        """;
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            for (Reviewer reviewer : reviewers) {
                statement.setInt(1, reviewer.getId());
                statement.setInt(2, reviewer.getPr1());
                statement.setInt(3, reviewer.getPr2());
                statement.setInt(4, reviewer.getPr3());
                statement.setInt(5, reviewer.getPr4());
                statement.setInt(6, reviewer.getPr5());
                statement.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.printf("Error inserting into reviewersPriorities: %s\n", ex);
        }
    }

    private static void dropStudentsTable(Connection conn) {
        String query = """
        DROP TABLE IF EXISTS students;
        """;
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.printf("Error dropping students table: %s\n", ex);
        }
    }

    private static void dropDepartmentsTable(Connection conn) {
        String query = """
        DROP TABLE IF EXISTS departments;
        """;
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.printf("Error dropping departments table: %s\n", ex);
        }
    }

    private static void dropReviewersTable(Connection conn) {
        String query = """
        DROP TABLE IF EXISTS reviewers;
        """;
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.printf("Error dropping reviewers table: %s\n", ex);
        }
    }

    private static void dropStudentsPrioritiesTable(Connection conn) {
        String query = """
        DROP TABLE IF EXISTS studentsPriorities;
        """;
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.printf("Error dropping studentsPriorities table: %s\n", ex);
        }
    }

    private static void dropReviewersPrioritiesTable(Connection conn) {
        String query = """
        DROP TABLE IF EXISTS reviewersPriorities;
        """;
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.printf("Error dropping reviewersPriorities table: %s\n", ex);
        }
    }

    public static void dropAllTables(Connection conn) {
        dropStudentsPrioritiesTable(conn);
        dropReviewersPrioritiesTable(conn);
        dropReviewersTable(conn);
        dropDepartmentsTable(conn);
        dropStudentsTable(conn);
    }
}
