/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jzuniga
 */
public class ConnectionManager {
    private static final String URL = "jdbc:mysql://localhost:3306/Login?autoReconnect=true&useSSL=false";
    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static Connection connection;
    
    public static Connection getConnection() throws SQLException{
        try {
            Class.forName(DRIVER_NAME);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch(ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return connection;
    }
}
