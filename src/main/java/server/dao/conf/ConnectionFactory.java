package server.dao.conf;

import java.sql.*;

public class ConnectionFactory {
    public static java.sql.Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName ("oracle.jdbc.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:system/xe@localhost:1521/XE");
            return connection;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
