package dao;

import java.sql.Connection;
import java.sql.Date;
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
import model.Borrow;
import model.Equipment;
import model.User;

/**
 * Data Access Object for borrowing operations
 */
public class BorrowDao {
  // Database connection details
  private static final String JDBC_URL =
      "jdbc:postgresql://localhost:5432/equipment_borrowing_system_db";
  private static final String DB_USERNAME = "postgres";
  private static final String DB_PASSWORD = "123";
    
  /**
   * Create a new borrow record
   * @param borrow The borrow details
   * @return Number of rows affected (1 if successful, 0 if failed)
   */
  public int createBorrow(Borrow borrow) {
    String sql = "INSERT INTO borrowing_records (user_id, equipment_id, borrow_date, "
        + "return_date, status) VALUES (?, ?, ?, ?, ?)";
    
    try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
        PreparedStatement pst = conn.prepareStatement(sql)) {
      
      pst.setInt(1, borrow.getUserId());
      pst.setInt(2, borrow.getEquipmentId());
      pst.setDate(3, borrow.getBorrowDate());
      pst.setDate(4, borrow.getReturnDate());
      pst.setString(5, borrow.getStatus());
      
      int rowsAffected = pst.executeUpdate();
      
      // If borrow successful, update equipment status to "Rented"
      if (rowsAffected > 0) {
        updateEquipmentStatus(borrow.getEquipmentId(), "Rented");
      }
      
      return rowsAffected;
      
    } catch (SQLException e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "Error creating borrow record: " + e.getMessage(),
          "Database Error", JOptionPane.ERROR_MESSAGE);
      return 0;
    }
  }
    
  /**
   * Check if equipment is available for borrowing
   * @param equipmentId The equipment ID to check
   * @return true if equipment is available, false otherwise
   */
  public boolean isEquipmentAvailable(int equipmentId) {
    String sql = "SELECT status FROM equipment WHERE equipment_id = ?";
    
    try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
        PreparedStatement pst = conn.prepareStatement(sql)) {
      
      pst.setInt(1, equipmentId);
      
      try (ResultSet rs = pst.executeQuery()) {
        if (rs.next()) {
          String status = rs.getString("status");
          return "Available".equals(status);
        }
      }
      
    } catch (SQLException e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "Error checking equipment status: " + e.getMessage(),
          "Database Error", JOptionPane.ERROR_MESSAGE);
    }
    
    return false;
  }
    
  /**
   * Update equipment status
   * @param equipmentId The equipment ID to update
   * @param status The new status
   * @return Number of rows affected (1 if successful, 0 if failed)
   */
  public int updateEquipmentStatus(int equipmentId, String status) {
    String sql = "UPDATE equipment SET status = ? WHERE equipment_id = ?";
    
    try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
        PreparedStatement pst = conn.prepareStatement(sql)) {
      
      pst.setString(1, status);
      pst.setInt(2, equipmentId);
      
      return pst.executeUpdate();
      
    } catch (SQLException e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "Error updating equipment status: " + e.getMessage(),
          "Database Error", JOptionPane.ERROR_MESSAGE);
      return 0;
    }
  }
    
  /**
   * Get user details by user ID
   * @param userId The user ID
   * @return User object if found, null otherwise
   */
  public User getUserById(int userId) {
    String sql = "SELECT * FROM users WHERE user_id = ?";
    
    try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
        PreparedStatement pst = conn.prepareStatement(sql)) {
      
      pst.setInt(1, userId);
      
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
      JOptionPane.showMessageDialog(null, "Error retrieving user: " + e.getMessage(),
          "Database Error", JOptionPane.ERROR_MESSAGE);
    }
    
    return null;
  }
    
  /**
   * Get user details by email
   * @param email The user's email
   * @return User object if found, null otherwise
   */
  public User getUserByEmail(String email) {
    String sql = "SELECT * FROM users WHERE email = ?";
    
    try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
        PreparedStatement pst = conn.prepareStatement(sql)) {
      
      pst.setString(1, email);
      
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
      JOptionPane.showMessageDialog(null, "Error retrieving user: " + e.getMessage(),
          "Database Error", JOptionPane.ERROR_MESSAGE);
    }
    
    return null;
  }
    
  /**
   * Get equipment details by ID
   * @param equipmentId The equipment ID
   * @return Equipment object if found, null otherwise
   */
  public Equipment getEquipmentById(int equipmentId) {
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
          equipment.setCategoryName(rs.getString("category_name"));
          return equipment;
        }
      }
      
    } catch (SQLException e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "Error retrieving equipment: " + e.getMessage(),
          "Database Error", JOptionPane.ERROR_MESSAGE);
    }
    
    return null;
  }
    
  /**
   * Get all available equipment (status = "Available")
   * @return List of available equipment
   */
  public List<Equipment> getAvailableEquipment() {
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
        equipment.setCategoryName(rs.getString("category_name"));
        equipmentList.add(equipment);
      }
      
    } catch (SQLException e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null,
          "Error retrieving available equipment: " + e.getMessage(),
          "Database Error", JOptionPane.ERROR_MESSAGE);
    }
    
    return equipmentList;
  }
    
  /**
   * Get all equipment (regardless of status)
   * @return List of all equipment
   */
  public List<Equipment> getAllEquipment() {
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
   * Get all active borrow records (status = 'Active' or 'Overdue')
   * @return List of active borrow records
   */
  public List<Borrow> getActiveBorrows() {
    List<Borrow> borrowList = new ArrayList<>();
    String sql = "SELECT b.*, u.full_name, e.equipment_name "
        + "FROM borrowing_records b "
        + "JOIN users u ON b.user_id = u.user_id "
        + "JOIN equipment e ON b.equipment_id = e.equipment_id "
        + "WHERE e.status = 'Rented' "
        + "ORDER BY b.borrow_id";
    
    try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {
      
      while (rs.next()) {
        Borrow borrow = new Borrow();
        borrow.setBorrowId(rs.getInt("borrow_id"));
        borrow.setUserId(rs.getInt("user_id"));
        borrow.setEquipmentId(rs.getInt("equipment_id"));
        borrow.setBorrowDate(rs.getDate("borrow_date"));
        // Try to get the return date with the correct column name (could be return_date)
        try {
          borrow.setReturnDate(rs.getDate("return_date"));
        } catch (SQLException ex) {
          // If that fails, try expected_return_date
          try {
            borrow.setReturnDate(rs.getDate("expected_return_date"));
          } catch (SQLException ex2) {
            // If both fail, set it to null
            borrow.setReturnDate(null);
          }
        }
        borrow.setStatus(rs.getString("status"));
        borrow.setUserName(rs.getString("full_name"));
        borrow.setEquipmentName(rs.getString("equipment_name"));
        borrowList.add(borrow);
      }
      
    } catch (SQLException e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "Error retrieving active borrows: " + e.getMessage(),
          "Database Error", JOptionPane.ERROR_MESSAGE);
    }
    
    return borrowList;
  }
    
  /**
   * Get a map of borrow IDs to display strings (for combo box)
   * @return Map with borrow ID as key and display string as value
   */
  /**
   * Insert test data for borrowing records (for development and testing)
   */
  public void insertTestBorrowData() {
    // Check if we have any rented equipment first
    List<String> rentedItems = checkRentedEquipment();
    if (rentedItems.isEmpty()) {
      System.out.println("No rented equipment found to create test borrow records");
      return;
    }
    
    // Check if we already have borrow records
    int recordCount = checkBorrowRecords();
    if (recordCount > 0) {
      System.out.println("Already have " + recordCount + " borrow records, not creating test data");
      return;
    }
    
    // Get equipment IDs for rented items
    String sql = "SELECT equipment_id FROM equipment WHERE status = 'Rented' LIMIT 2";
    try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {
      
      List<Integer> equipmentIds = new ArrayList<>();
      while (rs.next()) {
        equipmentIds.add(rs.getInt("equipment_id"));
      }
      
      if (equipmentIds.isEmpty()) {
        return;
      }
      
      // Get a user ID
      int userId;
      try (Statement userStmt = conn.createStatement();
          ResultSet userRs = userStmt.executeQuery("SELECT user_id FROM users LIMIT 1")) {
        if (userRs.next()) {
          userId = userRs.getInt("user_id");
        } else {
          return; // No users found
        }
      }
      
      // Insert borrow records for each rented equipment
      String insertSql = "INSERT INTO borrowing_records (user_id, equipment_id, borrow_date, "
          + "return_date, status) VALUES (?, ?, ?, ?, 'Active')";
      try (PreparedStatement pst = conn.prepareStatement(insertSql)) {
        
        java.util.Date today = new java.util.Date();
        java.sql.Date sqlToday = new java.sql.Date(today.getTime());
        
        // Add 7 days to today for the expected return date
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(java.util.Calendar.DAY_OF_MONTH, 7);
        java.sql.Date returnDate = new java.sql.Date(calendar.getTimeInMillis());
        
        for (Integer equipmentId : equipmentIds) {
          pst.setInt(1, userId);
          pst.setInt(2, equipmentId);
          pst.setDate(3, sqlToday);
          pst.setDate(4, returnDate);
          pst.executeUpdate();
          System.out.println("Created test borrow record for equipment " + equipmentId);
        }
      }
      
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
    
  public Map<Integer, String> getBorrowIdDisplayMap() {
    Map<Integer, String> borrowMap = new HashMap<>();
    // Get records where equipment is rented
    String sql = "SELECT b.borrow_id, b.borrow_date, u.full_name, e.equipment_name "
        + "FROM borrowing_records b "
        + "JOIN users u ON b.user_id = u.user_id "
        + "JOIN equipment e ON b.equipment_id = e.equipment_id "
        + "WHERE e.status = 'Rented' "
        + "ORDER BY b.borrow_id";
    
    try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {
      
      while (rs.next()) {
        int borrowId = rs.getInt("borrow_id");
        String displayText = borrowId + " - " + rs.getString("equipment_name")
            + " (" + rs.getString("full_name") + ")";
        borrowMap.put(borrowId, displayText);
      }
      
    } catch (SQLException e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "Error retrieving borrow IDs: " + e.getMessage(),
          "Database Error", JOptionPane.ERROR_MESSAGE);
    }
    
    return borrowMap;
  }
    
  /**
   * Get borrow details by ID
   * @param borrowId The borrow ID
   * @return Borrow object if found, null otherwise
   */
  public Borrow getBorrowById(int borrowId) {
    String sql = "SELECT b.*, u.full_name, e.equipment_name "
        + "FROM borrowing_records b "
        + "JOIN users u ON b.user_id = u.user_id "
        + "JOIN equipment e ON b.equipment_id = e.equipment_id "
        + "WHERE b.borrow_id = ?";
    
    try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
        PreparedStatement pst = conn.prepareStatement(sql)) {
      
      pst.setInt(1, borrowId);
      
      try (ResultSet rs = pst.executeQuery()) {
        if (rs.next()) {
          Borrow borrow = new Borrow();
          borrow.setBorrowId(rs.getInt("borrow_id"));
          borrow.setUserId(rs.getInt("user_id"));
          borrow.setEquipmentId(rs.getInt("equipment_id"));
          borrow.setBorrowDate(rs.getDate("borrow_date"));
          // Try to get the return date with the correct column name
          try {
            borrow.setReturnDate(rs.getDate("return_date"));
          } catch (SQLException ex) {
            // If that fails, try expected_return_date
            try {
              borrow.setReturnDate(rs.getDate("expected_return_date"));
            } catch (SQLException ex2) {
              // If both fail, set it to null
              borrow.setReturnDate(null);
            }
          }
          // Omit the actual_return_date field as it doesn't exist
          borrow.setStatus(rs.getString("status"));
          borrow.setUserName(rs.getString("full_name"));
          borrow.setEquipmentName(rs.getString("equipment_name"));
          return borrow;
        }
      }
      
    } catch (SQLException e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "Error retrieving borrow details: " + e.getMessage(),
          "Database Error", JOptionPane.ERROR_MESSAGE);
    }
    
    return null;
  }
    
  /**
   * Check if there are any rented equipment in the database (diagnostic method)
   * @return List of rented equipment IDs and names
   */
  public List<String> checkRentedEquipment() {
    List<String> rentedItems = new ArrayList<>();
    String sql = "SELECT equipment_id, equipment_name, status FROM equipment "
        + "WHERE status = 'Rented'";
    
    try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {
      
      while (rs.next()) {
        int equipmentId = rs.getInt("equipment_id");
        String name = rs.getString("equipment_name");
        String status = rs.getString("status");
        rentedItems.add(equipmentId + " - " + name + " (" + status + ")");
      }
      
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    return rentedItems;
  }
    
  /**
   * Check if there are any borrowing records in the database (diagnostic method)
   * @return Number of borrow records found
   */
  public int checkBorrowRecords() {
    String sql = "SELECT COUNT(*) FROM borrowing_records";
    
    try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {
      
      if (rs.next()) {
        return rs.getInt(1);
      }
      
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    return 0;
  }
    
  /**
   * Debug method to check column names in the borrowing_records table
   */
  public void checkBorrowingTableStructure() {
    try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM borrowing_records LIMIT 1")) {
      
      // Get metadata
      java.sql.ResultSetMetaData metaData = rs.getMetaData();
      int columnCount = metaData.getColumnCount();
      
      System.out.println("\n=== BORROWING TABLE STRUCTURE ===");
      System.out.println("Column count: " + columnCount);
      
      // Print column names
      for (int i = 1; i <= columnCount; i++) {
        String columnName = metaData.getColumnName(i);
        String columnType = metaData.getColumnTypeName(i);
        System.out.println(i + ". " + columnName + " (" + columnType + ")");
      }
      
      System.out.println("==============================\n");
      
    } catch (SQLException e) {
      System.out.println("Error checking table structure: " + e.getMessage());
      e.printStackTrace();
    }
  }
    
  /**
   * Check valid statuses allowed in borrowing_records table
   * @return List of valid status values from the database constraint
   */
  public List<String> getValidBorrowStatuses() {
    List<String> validStatuses = new ArrayList<>();
    
    // First try to query distinct status values that are actually used in the table
    try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT DISTINCT status FROM borrowing_records")) {
      
      boolean foundAny = false;
      while (rs.next()) {
        String status = rs.getString("status");
        if (status != null && !status.isEmpty()) {
          validStatuses.add(status);
          System.out.println("Found existing status in database: '" + status + "'");
          foundAny = true;
        }
      }
      
      // If we found values, return them
      if (foundAny) {
        return validStatuses;
      }
    } catch (SQLException e) {
      System.out.println("Error checking existing statuses: " + e.getMessage());
    }
    
    // If no values were found, try to check the constraint definition
    try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(
            "SELECT pg_get_constraintdef(c.oid) AS constraint_def "
            + "FROM pg_constraint c "
            + "JOIN pg_class t ON t.oid = c.conrelid "
            + "JOIN pg_namespace n ON n.oid = t.relnamespace "
            + "WHERE t.relname = 'borrowing_records' "
            + "AND pg_get_constraintdef(c.oid) LIKE '%status%'")) {
      
      while (rs.next()) {
        String constraintDef = rs.getString("constraint_def");
        System.out.println("Found constraint: " + constraintDef);
        
        // Parse the constraint definition to extract allowed values
        if (constraintDef.contains("CHECK") && constraintDef.contains("status")) {
          String values = constraintDef.replaceAll(".*status.*\\((.*?)\\).*", "$1");
          System.out.println("Extracted values: " + values);
          
          // Split the values and add them to our list
          String[] statusArray = values.split(",");
          for (String status : statusArray) {
            status = status.trim().replace("'", "");
            validStatuses.add(status);
            System.out.println("Added valid status: '" + status + "'");
          }
        }
      }
    } catch (SQLException e) {
      System.out.println("Error getting valid statuses: " + e.getMessage());
      e.printStackTrace();
      // Default values as fallback
      validStatuses.add("Active");
      validStatuses.add("Returned");
      validStatuses.add("Overdue");
    }
    
    return validStatuses;
  }
    
  public boolean returnEquipment(int borrowId, Date actualReturnDate) {
    // First, check the valid status values in the database
    List<String> validStatuses = getValidBorrowStatuses();
    String statusToUse = "Returned";
    
    // If 'Returned' is not a valid status, try alternatives
    if (!validStatuses.contains("Returned")) {
      if (validStatuses.contains("returned")) {
        statusToUse = "returned"; // Try lowercase version
      } else if (validStatuses.contains("RETURNED")) {
        statusToUse = "RETURNED"; // Try uppercase version
      } else if (!validStatuses.isEmpty()) {
        statusToUse = validStatuses.get(0); // Use first available status as fallback
      }
    }
    
    System.out.println("Using status: '" + statusToUse + "' for return operation");
    
    // Update the borrowing record status with the correct case/value
    String updateBorrowSql = "UPDATE borrowing_records SET status = ? WHERE borrow_id = ?";
    
    try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
        PreparedStatement pst = conn.prepareStatement(updateBorrowSql)) {
      
      // Start transaction
      conn.setAutoCommit(false);
      
      // Update borrow record with the correct status and borrowId
      pst.setString(1, statusToUse);
      pst.setInt(2, borrowId);
      
      int rowsAffected = pst.executeUpdate();
      
      if (rowsAffected > 0) {
        // Get equipment ID from borrow record
        try (PreparedStatement getEquipmentIdPst = conn.prepareStatement(
            "SELECT equipment_id FROM borrowing_records WHERE borrow_id = ?")) {
          
          getEquipmentIdPst.setInt(1, borrowId);
          
          try (ResultSet rs = getEquipmentIdPst.executeQuery()) {
            if (rs.next()) {
              int equipmentId = rs.getInt("equipment_id");
              
              // Update equipment status to Available
              try (PreparedStatement updateEquipmentPst = conn.prepareStatement(
                  "UPDATE equipment SET status = 'Available' WHERE equipment_id = ?")) {
                
                updateEquipmentPst.setInt(1, equipmentId);
                updateEquipmentPst.executeUpdate();
                
                // Commit transaction
                conn.commit();
                return true;
              }
            }
          }
        }
      }
      
      // Rollback if something went wrong
      conn.rollback();
      
    } catch (SQLException e) {
      e.printStackTrace();
      
      // If it's a constraint violation specifically for status, try another approach
      if (e.getMessage().contains("borrowing_records_status_check")) {
        // Try a different approach - just update the equipment status and leave borrow record
        System.out.println("Got constraint violation, trying alternate approach");
        try (Connection conn2 = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement getEquipIdPst = conn2.prepareStatement(
                "SELECT equipment_id FROM borrowing_records WHERE borrow_id = ?")) {
          
          getEquipIdPst.setInt(1, borrowId);
          
          try (ResultSet rs = getEquipIdPst.executeQuery()) {
            if (rs.next()) {
              int equipmentId = rs.getInt("equipment_id");
              
              // Just update the equipment status to Available
              try (PreparedStatement updateEquipPst = conn2.prepareStatement(
                  "UPDATE equipment SET status = 'Available' WHERE equipment_id = ?")) {
                
                updateEquipPst.setInt(1, equipmentId);
                int equipRowsUpdated = updateEquipPst.executeUpdate();
                
                if (equipRowsUpdated > 0) {
                  System.out.println("Equipment status updated successfully as fallback");
                  JOptionPane.showMessageDialog(null,
                      "Equipment marked as Available.\n"
                      + "Note: Borrowing record status couldn't be updated due to database "
                      + "constraints.",
                      "Partial Success", JOptionPane.INFORMATION_MESSAGE);
                  return true;
                }
              }
            }
          }
        } catch (SQLException e2) {
          e2.printStackTrace();
        }
      }
      
      // Show the original error if we couldn't handle it
      JOptionPane.showMessageDialog(null, "Error returning equipment: " + e.getMessage(),
          "Database Error", JOptionPane.ERROR_MESSAGE);
    }
    
    return false;
  }
}
