package com.example.restaurantpda;

import android.database.sqlite.SQLiteDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlFinder {

    // SQL Server Database connection settings
    private static final String DB_URL = "jdbc:sqlserver://<SERVER_ADDRESS>:<PORT>;databaseName=restaurant";
    private static final String USER = "<USERNAME>";
    private static final String PASSWORD = "<PASSWORD>";

    // Σύνδεση με τη βάση δεδομένων
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Ανάκτηση του πίνακα με τα προϊόντα
    public static ResultSet fetchMenuItems() throws SQLException {
        String query = "SELECT name, quantity, category FROM items";
        PreparedStatement stmt = getConnection().prepareStatement(query);
        return stmt.executeQuery();
    }

    // Ενημέρωση ενός προϊόντος στη βάση δεδομένων
    public static int updateItem(String tableNumber, String itemName, int quantity, String comment) throws SQLException {
        String query = "UPDATE items SET quantity = ?, comment = ? WHERE tableNumber = ? AND name = ?";
        PreparedStatement stmt = getConnection().prepareStatement(query);
        stmt.setInt(1, quantity);
        stmt.setString(2, comment);
        stmt.setString(3, tableNumber);
        stmt.setString(4, itemName);
        return stmt.executeUpdate();
    }

    public SQLiteDatabase getWritableDatabase() {
    }
}
