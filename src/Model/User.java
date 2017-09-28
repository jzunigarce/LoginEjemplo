/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import db.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author jzuniga
 */
public class User {
    
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    public static final String TABLE = "user";

    public User() {
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public static User findByEmail(String email) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        String query = "SELECT * FROM " + User.TABLE + " WHERE email=?";
        PreparedStatement preparedStm = conn.prepareStatement(query);
        preparedStm.setString(1, email);
        
        ResultSet rs = preparedStm.executeQuery();
        User user = null;
        if(rs.next()) {
            user = new User();
            user.setId(rs.getInt("id"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
        }
        conn.close();
        return user;
    }
    
    public static ArrayList<User> getAll() throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        String query = "SELECT * FROM " + User.TABLE;
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        ArrayList<User> users = new ArrayList<User>();
        while(rs.next()) {
            User user = new  User();
            user.setId(rs.getInt("id"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            
            users.add(user);
        }
        conn.close();
        return users;
    }
    
    public boolean create() throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        String query = "INSERT INTO " + User.TABLE + 
                "(first_name, last_name, email, password) VALUES(?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, this.getFirstName());
        ps.setString(2, this.getLastName());
        ps.setString(3, this.getEmail());
        ps.setString(4, this.getPassword());
        
        int result = ps.executeUpdate();
        conn.close();
        return result > 0;
    }
}
