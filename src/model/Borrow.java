package model;

import java.sql.Date;

/**
 * Model class for borrowing equipment
 */
public class Borrow {
  private int borrowId;
  private int userId;
  private int equipmentId;
  private Date borrowDate;
  private Date returnDate;
  private Date actualReturnDate;
  private String status;
  
  // Additional fields for display purposes
  private String userName;
  private String equipmentName;
  
  public Borrow() {}
    
  public Borrow(int userId, int equipmentId, Date borrowDate, Date returnDate, String status) {
    this.userId = userId;
    this.equipmentId = equipmentId;
    this.borrowDate = borrowDate;
    this.returnDate = returnDate;
    this.status = status;
  }
    
  public Borrow(int borrowId, int userId, int equipmentId, Date borrowDate, Date returnDate,
      String status) {
    this.borrowId = borrowId;
    this.userId = userId;
    this.equipmentId = equipmentId;
    this.borrowDate = borrowDate;
    this.returnDate = returnDate;
    this.status = status;
  }
    
  public Borrow(int borrowId, int userId, int equipmentId, Date borrowDate, Date returnDate,
      Date actualReturnDate, String status) {
    this.borrowId = borrowId;
    this.userId = userId;
    this.equipmentId = equipmentId;
    this.borrowDate = borrowDate;
    this.returnDate = returnDate;
    this.actualReturnDate = actualReturnDate;
    this.status = status;
  }

  public int getBorrowId() {
    return borrowId;
  }

  public void setBorrowId(int borrowId) {
    this.borrowId = borrowId;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public int getEquipmentId() {
    return equipmentId;
  }

  public void setEquipmentId(int equipmentId) {
    this.equipmentId = equipmentId;
  }

  public Date getBorrowDate() {
    return borrowDate;
  }

  public void setBorrowDate(Date borrowDate) {
    this.borrowDate = borrowDate;
  }

  public Date getReturnDate() {
    return returnDate;
  }

  public void setReturnDate(Date returnDate) {
    this.returnDate = returnDate;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
  
  public Date getActualReturnDate() {
    return actualReturnDate;
  }
  
  public void setActualReturnDate(Date actualReturnDate) {
    this.actualReturnDate = actualReturnDate;
  }
  
  public String getUserName() {
    return userName;
  }
  
  public void setUserName(String userName) {
    this.userName = userName;
  }
  
  public String getEquipmentName() {
    return equipmentName;
  }
  
  public void setEquipmentName(String equipmentName) {
    this.equipmentName = equipmentName;
  }
}
