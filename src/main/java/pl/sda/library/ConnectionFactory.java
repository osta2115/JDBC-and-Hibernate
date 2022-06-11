package pl.sda.library;

import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection createMySqlConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "admin", "Password1");
    }

    public static Connection createH2Connection() throws SQLException {
        var jdbcDataSource = new JdbcDataSource();
        jdbcDataSource.setURL("jdbc:h2:mem:test_db");
        jdbcDataSource.setUser("user");
        jdbcDataSource.setPassword("");
        return jdbcDataSource.getConnection();
    }

}
