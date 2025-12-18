package model;

import java.sql.Timestamp;

/**
 * Model class for Equipment Category
 */
public class EquipmentCategory {
  private int categoryId;
  private String categoryName;
  private String categoryDescription;
  private Timestamp createdAt;
  
  public EquipmentCategory() {}
    
  public EquipmentCategory(String categoryName, String categoryDescription) {
    this.categoryName = categoryName;
    this.categoryDescription = categoryDescription;
  }
    
  public EquipmentCategory(int categoryId, String categoryName, String categoryDescription,
      Timestamp createdAt) {
    this.categoryId = categoryId;
    this.categoryName = categoryName;
    this.categoryDescription = categoryDescription;
    this.createdAt = createdAt;
  }

  public int getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(int categoryId) {
    this.categoryId = categoryId;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  public String getCategoryDescription() {
    return categoryDescription;
  }

  public void setCategoryDescription(String categoryDescription) {
    this.categoryDescription = categoryDescription;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }
  
  @Override
  public String toString() {
    return categoryName;
  }
}
