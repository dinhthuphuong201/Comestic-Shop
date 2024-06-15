package com.example.CosmeticShop.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {
    private static final String url = "jdbc:mysql://localhost:3306/cosmetic_shop";
    private static final String user = "root";
    private static final String password = "12345";
    
    public Connection getConnection() {
    	Connection conn = null;
        try {
            // Load driver MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Connect to MySQL database
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to MySQL database!");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: unable to load driver class!");
        } catch (SQLException e) {
            System.out.println("Error: unable to connect to MySQL database!");
        }
        
        return conn;
    }
   public SQLConnection() {
	   
   }
 
}
