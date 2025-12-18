package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Equipment;


public class EquipmentDao {
  // Database connection details
  private static final String JDBC_URL =
      "jdbc:postgresql://localhost:5432/equipment_borrowing_system_db";
  private static final String DB_USERNAME = "postgres";
  private static final String DB_PASSWORD = "123";
    
  
  public int createEquipment(Equipment equipment) {
    String sql = "INSERT INTO equipment (equipment_name, category_id, description, condition, "
        + "status) VALUES (?, ?, ?, ?, ?)";
    
    try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
        PreparedStatement pst = conn.prepareStatement(sql)) {
      
      pst.setString(1, equipment.getEquipmentName());
      pst.setInt(2, equipment.getCategoryId());
      pst.setString(3, equipment.getDescription());
      pst.setString(4, equipment.getCondition());
      pst.setString(5, equipment.getStatus());
      
      int rowsAffected = pst.executeUpdate();
      return rowsAffected;
      
    } catch (SQLException e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "Error saving equipment: " + e.getMessage(),
          "Database Error", JOptionPane.ERROR_MESSAGE);
      return 0;
    }
  }
    
  
  public int updateEquipment(Equipment equipment) {
    String sql = "UPDATE equipment SET equipment_name=?, category_id=?, description=?, "
        + "condition=?, status=? WHERE equipment_id=?";
    
    try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
        PreparedStatement pst = conn.prepareStatement(sql)) {
      
      pst.setString(1, equipment.getEquipmentName());
      pst.setInt(2, equipment.getCategoryId());
      pst.setString(3, equipment.getDescription());
      pst.setString(4, equipment.getCondition());
      pst.setString(5, equipment.getStatus());
      pst.setInt(6, equipment.getEquipmentId());
      
      int rowsAffected = pst.executeUpdate();
      return rowsAffected;
      
    } catch (SQLException e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "Error updating equipment: " + e.getMessage(),
          "Database Error", JOptionPane.ERROR_MESSAGE);
      return 0;
    }
  }
    
  
  public int deleteEquipment(int equipmentId) {
    String sql = "DELETE FROM equipment WHERE equipment_id=?";
    
    try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
        PreparedStatement pst = conn.prepareStatement(sql)) {
      
      pst.setInt(1, equipmentId);
      
      int rowsAffected = pst.executeUpdate();
      return rowsAffected;
      
    } catch (SQLException e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "Error deleting equipment: " + e.getMessage(),
          "Database Error", JOptionPane.ERROR_MESSAGE);
      return 0;
    }
  }
    
  
  public Equipment findEquipmentById(int equipmentId) {
    String sql = "SELECT e.*, c.category_name FROM equipment e "
        + "JOIN equipment_categories c ON e.category_id = c.category_id "
        + "WHERE e.equipment_id = ?";
    
    try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
        PreparedStatement pst = conn.prepareStatement(sql)) {
      
      pst.setInt(1, equipmentId);
      
      try (ResultSet rs = pst.executeQuery()) {
        if (rs.next()) {
          Equipment equipment = new Equipment();
          equipment.setEquipmentId(rs.getInt("equipment_id"));
          equipment.setEquipmentName(rs.getString("equipment_name"));
          equipment.setCategoryId(rs.getInt("category_id"));
          equipment.setDescription(rs.getString("description"));
          equipment.setCondition(rs.getString("condition"));
          equipment.setStatus(rs.getString("status"));
          equipment.setCreatedAt(rs.getTimestamp("created_at"));
          equipment.setCategoryName(rs.getString("category_name"));
          return equipment;
        }
      }
      
    } catch (SQLException e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "Error finding equipment: " + e.getMessage(),
          "Database Error", JOptionPane.ERROR_MESSAGE);
    }
    
    return null;
  }
    
  
  public List<Equipment> findAllEquipment() {
    List<Equipment> equipmentList = new ArrayList<>();
    String sql = "SELECT e.*, c.category_name FROM equipment e "
        + "JOIN equipment_categories c ON e.category_id = c.category_id "
        + "ORDER BY e.equipment_id";
    
    try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {
      
      while (rs.next()) {
        Equipment equipment = new Equipment();
        equipment.setEquipmentId(rs.getInt("equipment_id"));
        equipment.setEquipmentName(rs.getString("equipment_name"));
        equipment.setCategoryId(rs.getInt("category_id"));
        equipment.setDescription(rs.getString("description"));
        equipment.setCondition(rs.getString("condition"));
        equipment.setStatus(rs.getString("status"));
        equipment.setCreatedAt(rs.getTimestamp("created_at"));
        equipment.setCategoryName(rs.getString("category_name"));
        equipmentList.add(equipment);
      }
      
    } catch (SQLException e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "Error retrieving equipment list: " + e.getMessage(),
          "Database Error", JOptionPane.ERROR_MESSAGE);
    }
    
    return equipmentList;
  }
    
  
  /**
   * Get list of all available statuses
   */
  public String[] getEquipmentStatuses() {
    return new String[] {"Available", "Rented", "Under Maintenance"};
  }
    
  
  public List<Equipment> searchEquipmentByName(String searchName) {
    List<Equipment> equipmentList = new ArrayList<>();
    String sql = "SELECT e.*, c.category_name FROM equipment e "
        + "JOIN equipment_categories c ON e.category_id = c.category_id "
        + "WHERE LOWER(e.equipment_name) LIKE ? "
        + "ORDER BY e.equipment_id";
    
    try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
        PreparedStatement pst = conn.prepareStatement(sql)) {
      
      pst.setString(1, "%" + searchName.toLowerCase() + "%");
      
      try (ResultSet rs = pst.executeQuery()) {
        while (rs.next()) {
          Equipment equipment = new Equipment();
          equipment.setEquipmentId(rs.getInt("equipment_id"));
          equipment.setEquipmentName(rs.getString("equipment_name"));
          equipment.setCategoryId(rs.getInt("category_id"));
          equipment.setDescription(rs.getString("description"));
          equipment.setCondition(rs.getString("condition"));
          equipment.setStatus(rs.getString("status"));
          equipment.setCreatedAt(rs.getTimestamp("created_at"));
          equipment.setCategoryName(rs.getString("category_name"));
          equipmentList.add(equipment);
        }
      }
      
    } catch (SQLException e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "Error searching equipment: " + e.getMessage(),
          "Database Error", JOptionPane.ERROR_MESSAGE);
    }
    
    return equipmentList;
  }
    
  
  public List<Equipment> findAllAvailableEquipment() {
    List<Equipment> equipmentList = new ArrayList<>();
    String sql = "SELECT e.*, c.category_name FROM equipment e "
        + "JOIN equipment_categories c ON e.category_id = c.category_id "
        + "WHERE e.status = 'Available' "
        + "ORDER BY e.equipment_id";
    
    try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {
      
      while (rs.next()) {
        Equipment equipment = new Equipment();
        equipment.setEquipmentId(rs.getInt("equipment_id"));
        equipment.setEquipmentName(rs.getString("equipment_name"));
        equipment.setCategoryId(rs.getInt("category_id"));
        equipment.setDescription(rs.getString("description"));
        equipment.setCondition(rs.getString("condition"));
        equipment.setStatus(rs.getString("status"));
        equipment.setCreatedAt(rs.getTimestamp("created_at"));
        equipment.setCategoryName(rs.getString("category_name"));
        equipmentList.add(equipment);
      }
      
    } catch (SQLException e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null,
          "Error retrieving available equipment list: " + e.getMessage(),
          "Database Error", JOptionPane.ERROR_MESSAGE);
    }
    
    return equipmentList;
  }
}
