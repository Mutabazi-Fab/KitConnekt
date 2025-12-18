package model;

public class User {
  private int userId;
  private String fullName;
  private String email;
  private String password;
  private boolean isAdmin;

  public User() {}

  public User(String fullName, String email, String password, boolean isAdmin) {
    this.fullName = fullName;
    this.email = email;
    this.password = password;
    this.isAdmin = isAdmin;
  }

  // Getters and Setters
  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
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

  public boolean isAdmin() {
    return isAdmin;
  }

  public void setAdmin(boolean admin) {
    isAdmin = admin;
  }
}
