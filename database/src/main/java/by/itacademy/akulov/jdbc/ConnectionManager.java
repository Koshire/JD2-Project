package by.itacademy.akulov.jdbc;

import lombok.experimental.UtilityClass;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.SQLException;

@UtilityClass
public class ConnectionManager {

    private static DataSource dataSource;

    static {
        initConnectionPool();
    }

    private static void initConnectionPool() {
        PoolProperties poolProperties = new PoolProperties();
        poolProperties.setDriverClassName(PropertiesManager.get("db.driver"));
        poolProperties.setUrl(PropertiesManager.get("db.url"));
        poolProperties.setUsername(PropertiesManager.get("db.username"));
        poolProperties.setPassword(PropertiesManager.get("db.password"));
        poolProperties.setMaxActive(Integer.parseInt(PropertiesManager.get("db.maxActive.size")));
        poolProperties.setMaxIdle(Integer.parseInt(PropertiesManager.get("db.maxIdle.size")));
        dataSource = new DataSource(poolProperties);
    }

    public static Connection get() throws SQLException {
        return dataSource.getConnection();
    }
}
