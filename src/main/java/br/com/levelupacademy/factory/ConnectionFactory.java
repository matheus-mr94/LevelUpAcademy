package br.com.levelupacademy.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public Connection recoverConnection() throws SQLException {
     return DriverManager
                .getConnection("jdbc:mysql://localhost/LevelUpAcademy?useTimezone=true&server=UTC",
                "root", "Root@2102");
    }
}
