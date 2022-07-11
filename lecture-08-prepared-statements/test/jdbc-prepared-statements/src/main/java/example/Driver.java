package example;
/*
A PreparedStatement is a pre-compiled SQL statement. It is a subinterface of Statement.
Prepared Statement objects have some useful additional features than Statement objects.
Instead of hard coding queries, PreparedStatement object provides a feature to execute a parameterized query.

Advantages of PreparedStatement:
When PreparedStatement is created, the SQL query is passed as a parameter.
This Prepared Statement contains a pre-compiled SQL query, so when the PreparedStatement is executed,
DBMS can just run the query instead of first compiling it.
We can use the same PreparedStatement and supply with different parameters at the time of execution.
An important advantage of PreparedStatements is that they prevent SQL injection attacks.
 */
import java.sql.*;

public class Driver {

    public static void main(String[] args) throws SQLException {

        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            // 1. Get a connection to database
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "student" , "student");

            // 2. Prepare statement: myStmt is of class PreparedStatement and we apply it to the connection
            // the "?" are the placeholders
            myStmt = myConn.prepareStatement("select * from employees where salary > ? and department=?");

            // 3. Set the parameters
            // the parameter values are set depending on the data type and position starting from left and moving to right
            myStmt.setDouble(1, 80000);
            myStmt.setString(2, "Legal");

            // 4. Execute SQL query
            myRs = myStmt.executeQuery();

            // 5. Display the result set
            display(myRs);

            //
            // Reuse the prepared statement:  salary > 25000,  department = HR
            //

            System.out.println("\n\nReuse the prepared statement:  salary > 25000,  department = HR");

            // 6. Set the parameters
            myStmt.setDouble(1, 25000);
            myStmt.setString(2, "HR");

            // 7. Execute SQL query
            myRs = myStmt.executeQuery();

            // 8. Display the result set
            display(myRs);


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

    private static void display(ResultSet myRs) throws SQLException {
        while (myRs.next()) {
            String lastName = myRs.getString("last_name");
            String firstName = myRs.getString("first_name");
            double salary = myRs.getDouble("salary");
            String department = myRs.getString("department");

            System.out.printf("%s, %s, %.2f, %s\n", lastName, firstName, salary, department);
        }
    }
}
