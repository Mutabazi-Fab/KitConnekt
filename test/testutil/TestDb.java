package testutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Tiny helper for integration tests.
 *
 * Note: This matches the hard-coded DAO connection settings.
 */
public final class TestDb {
  private static final String JDBC_URL =
      "jdbc:postgresql://localhost:5432/equipment_borrowing_system_db";
  private static final String DB_USERNAME = "postgres";
  private static final String DB_PASSWORD = "123";

  private TestDb() {}

  public static boolean canConnect() {
    try (Connection conn = getConnection()) {
      return conn != null && !conn.isClosed();
    } catch (SQLException e) {
      return false;
    }
  }

  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
  }

  public static Integer findLatestEquipmentIdByName(String equipmentName) throws SQLException {
    String sql = "SELECT equipment_id FROM equipment WHERE equipment_name = ? "
        + "ORDER BY equipment_id DESC LIMIT 1";
    try (Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setString(1, equipmentName);
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          return rs.getInt(1);
        }
      }
      return null;
    }
  }

  public static Integer findUserIdByEmail(String email) throws SQLException {
    String sql = "SELECT user_id FROM users WHERE email = ? LIMIT 1";
    try (Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setString(1, email);
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          return rs.getInt(1);
        }
      }
      return null;
    }
  }

  public static int deleteUserByEmail(String email) throws SQLException {
    String sql = "DELETE FROM users WHERE email = ?";
    try (Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setString(1, email);
      return ps.executeUpdate();
    }
  }
}


