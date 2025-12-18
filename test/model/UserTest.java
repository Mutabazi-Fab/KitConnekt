package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

  @Test
  public void constructor_setsFieldsCorrectly() {
    User user = new User("Alice Smith", "alice@user.com", "secret", false);

    assertEquals("Alice Smith", user.getFullName());
    assertEquals("alice@user.com", user.getEmail());
    assertEquals("secret", user.getPassword());
    assertFalse(user.isAdmin());
  }

  @Test
  public void setters_updateFieldsCorrectly() {
    User user = new User();

    user.setUserId(42);
    user.setFullName("Bob");
    user.setEmail("bob@admin.com");
    user.setPassword("pw");
    user.setAdmin(true);

    assertEquals(42, user.getUserId());
    assertEquals("Bob", user.getFullName());
    assertEquals("bob@admin.com", user.getEmail());
    assertEquals("pw", user.getPassword());
    assertTrue(user.isAdmin());
  }
}


