package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final String URL = "jdbc:sqlite:cab_booking.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void initialize() throws SQLException {
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS Customers (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phone_no TEXT UNIQUE, email TEXT UNIQUE, password TEXT)");
            stmt.execute("CREATE TABLE IF NOT EXISTS Subscriptions (id INTEGER PRIMARY KEY AUTOINCREMENT, customer_id INTEGER, plan_type TEXT, km_limit REAL, status TEXT)");
            stmt.execute("CREATE TABLE IF NOT EXISTS Rides (id INTEGER PRIMARY KEY AUTOINCREMENT, customer_id INTEGER, pickup TEXT, drop TEXT, distance REAL, fare REAL)");
        }
    }
}