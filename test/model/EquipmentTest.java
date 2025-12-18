package model;

import java.sql.Timestamp;
import org.junit.Test;

import static org.junit.Assert.*;

public class EquipmentTest {

  @Test
  public void constructor_withoutId_setsCoreFields() {
    Equipment equipment = new Equipment("Camera", 3, "DSLR", "Good", "Available");

    assertEquals("Camera", equipment.getEquipmentName());
    assertEquals(3, equipment.getCategoryId());
    assertEquals("DSLR", equipment.getDescription());
    assertEquals("Good", equipment.getCondition());
    assertEquals("Available", equipment.getStatus());
  }

  @Test
  public void fullConstructor_setsAllFields() {
    Timestamp createdAt = new Timestamp(System.currentTimeMillis());

    Equipment equipment = new Equipment(10, "Laptop", 2, "MacBook", "New", "Rented", createdAt);

    assertEquals(10, equipment.getEquipmentId());
    assertEquals("Laptop", equipment.getEquipmentName());
    assertEquals(2, equipment.getCategoryId());
    assertEquals("MacBook", equipment.getDescription());
    assertEquals("New", equipment.getCondition());
    assertEquals("Rented", equipment.getStatus());
    assertEquals(createdAt, equipment.getCreatedAt());
  }

  @Test
  public void categoryName_isIndependentDisplayField() {
    Equipment equipment = new Equipment();
    equipment.setCategoryName("Audio");

    assertEquals("Audio", equipment.getCategoryName());
  }
}


