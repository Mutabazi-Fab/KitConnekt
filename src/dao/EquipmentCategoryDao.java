package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import model.EquipmentCategory;


/**
 * Data Access Object for Equipment Categories
 */
public class EquipmentCategoryDao {
  // Database connection
  private static final String JDBC_URL =
      "jdbc:postgresql://localhost:5432/equipment_borrowing_system_db";
  private static final String DB_USERNAME = "postgres";
  private static final String DB_PASSWORD = "123";
    
    
  /**
   * Get all equipment categories
   */
  public List<EquipmentCategory> getAllCategories() {
    List<EquipmentCategory> categoryList = new ArrayList<>();
    String sql = "SELECT * FROM equipment_categories ORDER BY category_name";
    
    try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {
      
      while (rs.next()) {
        EquipmentCategory category = new EquipmentCategory();
        category.setCategoryId(rs.getInt("category_id"));
        category.setCategoryName(rs.getString("category_name"));
        category.setCategoryDescription(rs.getString("category_description"));
        category.setCreatedAt(rs.getTimestamp("created_at"));
        categoryList.add(category);
      }
      
    } catch (SQLException e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "Error retrieving categories: " + e.getMessage(),
          "Database Error", JOptionPane.ERROR_MESSAGE);
    }
    
    return categoryList;
  }
    
  /**
   * Get a map of category IDs to category names
   * @return Map with category IDs as keys and category names as values
   */
  public Map<Integer, String> getCategoryIdToNameMap() {
    Map<Integer, String> categoryMap = new HashMap<>();
    String sql = "SELECT category_id, category_name FROM equipment_categories";
    
    try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {
      
      while (rs.next()) {
        categoryMap.put(rs.getInt("category_id"), rs.getString("category_name"));
      }
      
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    return categoryMap;
  }
    
  /**
   * Get category ID by name
   * @param categoryName Name of the category
   * @return Category ID or -1 if not found
   */
  public int getCategoryIdByName(String categoryName) {
    String sql = "SELECT category_id FROM equipment_categories WHERE category_name = ?";
    
    try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
        PreparedStatement pst = conn.prepareStatement(sql)) {
      
      pst.setString(1, categoryName);
      
      try (ResultSet rs = pst.executeQuery()) {
        if (rs.next()) {
          return rs.getInt("category_id");
        }
      }
      
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    return -1;
  }
}
