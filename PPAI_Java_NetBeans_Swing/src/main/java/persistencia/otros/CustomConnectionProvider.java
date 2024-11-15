package persistencia.otros;

import java.sql.Connection;
import java.sql.SQLException;
import org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl;

public class CustomConnectionProvider extends DriverManagerConnectionProviderImpl {
    @Override
    public Connection getConnection() throws SQLException {
        Connection connection = super.getConnection();
        connection.createStatement().execute("PRAGMA foreign_keys = ON");
        return connection;
    }
}
