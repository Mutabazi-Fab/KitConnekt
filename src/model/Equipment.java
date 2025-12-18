package model;

import java.sql.Timestamp;

/**
 * Model class for Equipment
 */
public class Equipment {
  private int equipmentId;
  private String equipmentName;
  private int categoryId;
  private String description;
  private String condition;
  private String status;
  private Timestamp createdAt;
  
  // Category name for display purposes (not stored in equipment table)
  private String categoryName;
  
  public Equipment() {}
    
  public Equipment(String equipmentName, int categoryId, String description, String condition,
      String status) {
    this.equipmentName = equipmentName;
    this.categoryId = categoryId;
    this.description = description;
    this.condition = condition;
    this.status = status;
  }
    
  public Equipment(int equipmentId, String equipmentName, int categoryId, String description,
      String condition, String status, Timestamp createdAt) {
    this.equipmentId = equipmentId;
    this.equipmentName = equipmentName;
    this.categoryId = categoryId;
    this.description = description;
    this.condition = condition;
    this.status = status;
    this.createdAt = createdAt;
  }

  public int getEquipmentId() {
    return equipmentId;
  }

  public void setEquipmentId(int equipmentId) {
    this.equipmentId = equipmentId;
  }

  public String getEquipmentName() {
    return equipmentName;
  }

  public void setEquipmentName(String equipmentName) {
    this.equipmentName = equipmentName;
  }

  public int getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(int categoryId) {
    this.categoryId = categoryId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getCondition() {
    return condition;
  }

  public void setCondition(String condition) {
    this.condition = condition;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }
  
  public String getCategoryName() {
    return categoryName;
  }
  
  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }
}
