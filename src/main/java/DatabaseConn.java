import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static utils.ReaderOds.read;

public class DatabaseConn {
    private static final String URL = String.format("jdbc:mariadb://%s", System.getenv("MARIADB_HOST"));
    private static final String user = System.getenv("MARIADB_USER");
    private static final String password = System.getenv("MARIADB_PASSWORD");

    public static <DataResult> void main(String[] args){
        DataResult data = null;
        read("data.ods");

        try (Connection conn = createConnection()) {
            try (Statement smt = conn.createStatement()) {

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