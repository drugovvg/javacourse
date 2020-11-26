package part1.lessons14_.connection.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManagerJdbc implements ConnectionManager {
    public static final ConnectionManager INSTANCE = new ConnectionManagerJdbc();

    private ConnectionManagerJdbc() {
    }

    public static ConnectionManager getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:3306/jdbcDB",
                    "inno",
                    "inno");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
