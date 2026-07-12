
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcExamples {

    public static void main(String args[]) throws Exception {
        String url = "jdbc:derby://localhost:1527/testdb;create=true";

        try (Connection c = DriverManager.getConnection(url); Statement stmt = c.createStatement()) {
            String query = "SELECT * FROM EMPLOYEES";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String dept = rs.getString("department");

                // %-15s means "left-align this string up to 15 characters wide"
                System.out.printf("ID: %-3d | Name: %-15s | Dept: %s\n", id, name, dept);
            }
            rs.close();

        } catch (SQLException e) {
            String state = e.getSQLState();
            int errorCode = e.getErrorCode();
            System.err.println("SQLException Occured, state:%s error code: %d".formatted(state, errorCode));
            System.out.println("Exception:" + e);
        }
    }

}
