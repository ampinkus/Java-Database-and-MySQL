package jdbc;
// inserting a new row (employee) in the database
import java.sql.*;
public class JdbcInsertDemo {
    public static void main(String[] args) throws SQLException {

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        String dbUrl = "jdbc:mysql://localhost:3306/demo";
        String user = "student";
        String pass = "student";

        try {
            // 1. Get a connection to database
            myConn = DriverManager.getConnection(dbUrl, user, pass);

            // 2. Create a statement
            myStmt = myConn.createStatement();

            // 3. Insert a new employee
            System.out.println("Inserting a new employee to database\n");

            // Executes the SQL statement in this Statement or PreparedStatement object, which must be an SQL Data Manipulation Language (DML) statement,
            // such as INSERT, UPDATE or DELETE; or an SQL statement that returns nothing, such as a DDL statement.
            int rowsAffected = myStmt.executeUpdate(
                    "insert into employees " +
                            "(last_name, first_name, email, department, salary) " +
                            "values " +
                            "('Wright', 'Eric', 'eric.wright@foo.com', 'HR', 33000.00)");

            // 4. Verify this by getting a list of employees
            // note that we use here executeQuery instead of execute update!
            myRs = myStmt.executeQuery("select * from employees order by last_name");

            // 5. Process the result set
            while (myRs.next()) {
                System.out.println(myRs.getString("last_name") + ", " + myRs.getString("first_name"));
            }
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        finally {
            if (myRs != null) {
                myRs.close();
            }

            if (myStmt != null) {
                myStmt.close();
            }

            if (myConn != null) {
                myConn.close();
            }
        }
    }

}
