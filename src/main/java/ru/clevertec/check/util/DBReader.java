package ru.clevertec.check.util;

import ru.clevertec.check.model.DiscountCard;
import ru.clevertec.check.model.Product;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DBReader implements AutoCloseable {
    private Connection connection;

    public DBReader(String url, String user, String password) throws SQLException {
        this.connection = DriverManager.getConnection(url, user, password);
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}