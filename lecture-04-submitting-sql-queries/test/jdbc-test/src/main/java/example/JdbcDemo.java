package example;
/*
Una vez cargado el driver es necesario crear un objeto del tipo Connection, para administrar la conexión.
Una aplicación puede utilizar DriverManager para obtener un objeto de tipo conexión, Connection, con una base de datos.
La conexión se especifica siguiendo una sintaxis basada en la especificación más amplia de los URL, de la forma:
basada en la especificación más amplia de los URL, de la forma:  jdbc:subprotocolo//servidor:puerto/base de datos
La siguiente línea de código ilustra ésta idea:  Connection con = DriverManager.getConnection(url, "myLogin", "myPassword");

Si uno de los drivers que hemos cargado reconoce la URL suministada por el método DriverManager.getConnection,
dicho driver establecerá una conexión con el controlador de base de datos especificado en la URL del JDBC.
La clase DriverManager, como su nombre indica, maneja todos los detalles del establecimiento de la conexión
detrás de la escena.
La conexión devuelta por el método DriverManager.getConnection es una conexión abierta que se puede utilizar
para crear sentencias JDBC que pasen nuestras sentencias SQL al controlador de la base de datos.
 */
import java.sql.*;

public class JdbcDemo {

    public static void main(String[] args) throws SQLException {

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        // the ?useSSL=false is to avoid SSL warnings

        String dbUrl = "jdbc:mysql://localhost:3306/demo?useSSL=false"; // jdbc:subprotocolo//servidor:puerto/base de datos
        String user = "student";
        String pass = "student";

        try {
            // 1. Get a connection to database
            myConn = DriverManager.getConnection(dbUrl, user, pass);

            // 2. Create a statement
            myStmt = myConn.createStatement();

            // 3. Execute SQL query
            myRs = myStmt.executeQuery("select * from employees");

            // 4. Process the result set
            while (myRs.next()) { // returns true if there are more rows to read
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
