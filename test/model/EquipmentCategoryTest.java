package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class EquipmentCategoryTest {

  @Test
  public void toString_returnsCategoryName() {
    EquipmentCategory category = new EquipmentCategory();
    category.setCategoryName("Computers");

    assertEquals("Computers", category.toString());
  }
}


