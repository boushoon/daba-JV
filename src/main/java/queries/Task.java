package queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Task {
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
        DROP TABLE IF EXISTS studentPriorities;
        """;
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.printf("Error dropping studentsPriorities table: %s\n", ex);
        }
    }

    private static void dropReviewersPrioritiesTable(Connection conn) {
        String query = """
        DROP TABLE IF EXISTS reviewerPriorities;
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
                        pr5 INT
                        FOREIGN KEY(reviewerID) REFERENCES reviewers(id),
                        FOREIGN KEY(pr1) REFERENCES students(id),
                        FOREIGN KEY(pr2) REFERENCES students(id),
                        FOREIGN KEY(pr3) REFERENCES students(id),
                        FOREIGN KEY(pr4) REFERENCES students(id),
                        FOREIGN KEY(pr5) REFERENCES students(id);
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
}
