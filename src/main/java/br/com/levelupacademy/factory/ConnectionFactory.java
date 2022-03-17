package br.com.levelupacademy.factory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public final class ConnectionFactory {

    private static DataSource dataSource;

    private ConnectionFactory() {

    }

    public static Connection recoverConnection() throws SQLException {
        if(dataSource == null) {
            buildConnectionPool();
        }
        return dataSource.getConnection();
    }

    private static void buildConnectionPool() {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource
                .setJdbcUrl("jdbc:mysql://localhost/LevelUpAcademy?useTimezone=true&server=UTC");
        comboPooledDataSource.setUser("root");
        comboPooledDataSource.setPassword("");

        dataSource = comboPooledDataSource;
    }
}
