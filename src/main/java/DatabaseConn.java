import objects.AllData;
import objects.Department;
import objects.Reviewer;
import objects.Student;
import queries.Task;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import io.github.cdimascio.dotenv.Dotenv;

import static utils.ReaderOds.read;

public class DatabaseConn {
    private static final Dotenv dotenv = Dotenv.load();
    private static final String URL = String.format("jdbc:mariadb://%s", dotenv.get("MARIADB_HOST"));
    private static final String user = dotenv.get("MARIADB_USER");
    private static final String password = dotenv.get("MARIADB_PASSWORD");

    public static void main(String[] args){
        AllData data = read("data.ods");
        if (data == null)
            return;

        ArrayList<Student> students = data.getStudents();
        ArrayList<Reviewer> reviewers = data.getReviewers();
        ArrayList<Department> departments = data.getDepartments();

        try (Connection conn = createConnection()) {
            try (Statement smt = conn.createStatement()) {
                Task.dropAllTables(conn);
                Task.createTables(conn);

                Task.insertStudents(conn, students);
                Task.insertDepartments(conn, departments);
                Task.insertReviewers(conn, reviewers);
                Task.insertStudentsPriorities(conn, students);
                Task.insertReviewersPriorities(conn, reviewers);
            }
            catch (SQLException ex) {
                System.out.printf("Can't create statement: %s\n", ex);
            }
        }
        catch (SQLException ex) {
            System.out.printf("Can't create connection: %s\n", ex);
        }
    }

    private static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(URL, user, password);
    }
}