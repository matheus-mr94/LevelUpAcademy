package br.com.levelupacademy.factory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public final class ConnectionFactory {

    private static DataSource dataSource;

    public ConnectionFactory() {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource
                .setJdbcUrl("jdbc:mysql://localhost/LevelUpAcademy?useTimezone=true&server=UTC");
        comboPooledDataSource.setUser("root");
        comboPooledDataSource.setPassword("");

        dataSource = comboPooledDataSource;
    }

    public  Connection recoverConnection() throws SQLException {
        return this.dataSource.getConnection();
    }
}
