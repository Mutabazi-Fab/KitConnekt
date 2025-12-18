package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.User;

public class UserDao {
  // Database connection details
  private static final String JDBC_URL =
      "jdbc:postgresql://localhost:5432/equipment_borrowing_system_db";
  private static final String DB_USERNAME = "postgres";
  private static final String DB_PASSWORD = "123";
    
  // Method to save a new user
  public boolean saveUser(User user) {
    String sql = "INSERT INTO users (full_name, email, password, is_admin) VALUES (?, ?, ?, ?)";
    
    try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
        PreparedStatement pst = conn.prepareStatement(sql)) {
      
      pst.setString(1, user.getFullName());
      pst.setString(2, user.getEmail());
      pst.setString(3, user.getPassword());
      pst.setBoolean(4, user.isAdmin());
      
      int rowsAffected = pst.executeUpdate();
      return rowsAffected > 0;
      
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }
    
  // Method to authenticate user
  public User authenticateUser(String email, String password) {
    String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
    
    try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
        PreparedStatement pst = conn.prepareStatement(sql)) {
      
      pst.setString(1, email);
      pst.setString(2, password);
      
      try (ResultSet rs = pst.executeQuery()) {
        if (rs.next()) {
          User user = new User();
          user.setUserId(rs.getInt("user_id"));
          user.setFullName(rs.getString("full_name"));
          user.setEmail(rs.getString("email"));
          user.setAdmin(rs.getBoolean("is_admin"));
          return user;
        }
      }
      
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    return null;
  }
    
  // Method to check if email already exists
  public boolean emailExists(String email) {
    String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
    
    try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
        PreparedStatement pst = conn.prepareStatement(sql)) {
      
      pst.setString(1, email);
      
      try (ResultSet rs = pst.executeQuery()) {
        if (rs.next()) {
          return rs.getInt(1) > 0;
        }
      }
      
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    return false;
  }
    
  /**
   * Get all users from database
   * @return List of all users
   */
  public List<User> findAllUsers() {
    List<User> userList = new ArrayList<>();
    String sql = "SELECT * FROM users ORDER BY user_id";
    
    try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {
      
      while (rs.next()) {
        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setFullName(rs.getString("full_name"));
        user.setEmail(rs.getString("email"));
        // Skip password for security
        user.setAdmin(rs.getBoolean("is_admin"));
        userList.add(user);
      }
      
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    return userList;
  }
    
  /**
   * Search users by name
   * @param searchName The name to search for (partial match)
   * @return List of users matching the search criteria
   */
  public List<User> searchUsersByName(String searchName) {
    List<User> userList = new ArrayList<>();
    String sql = "SELECT * FROM users WHERE LOWER(full_name) LIKE ? ORDER BY user_id";
    
    try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
        PreparedStatement pst = conn.prepareStatement(sql)) {
      
      pst.setString(1, "%" + searchName.toLowerCase() + "%");
      
      try (ResultSet rs = pst.executeQuery()) {
        while (rs.next()) {
          User user = new User();
          user.setUserId(rs.getInt("user_id"));
          user.setFullName(rs.getString("full_name"));
          user.setEmail(rs.getString("email"));
          // Skip password for security
          user.setAdmin(rs.getBoolean("is_admin"));
          userList.add(user);
        }
      }
      
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    return userList;
  }
}
