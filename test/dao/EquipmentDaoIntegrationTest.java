package dao;

import java.util.List;
import model.Equipment;
import model.EquipmentCategory;
import org.junit.Assume;
import org.junit.Test;

import static org.junit.Assert.*;
import testutil.TestDb;

public class EquipmentDaoIntegrationTest {

  @Test
  public void create_find_update_delete_equipment_roundTrip() throws Exception {
    Assume.assumeTrue("PostgreSQL not reachable on localhost:5432", TestDb.canConnect());

    EquipmentCategoryDao categoryDao = new EquipmentCategoryDao();
    List<EquipmentCategory> categories = categoryDao.getAllCategories();
    Assume.assumeTrue("No categories exist in DB to create equipment", categories != null && !categories.isEmpty());

    int categoryId = categories.get(0).getCategoryId();

    EquipmentDao equipmentDao = new EquipmentDao();

    String uniqueName = "JUnit_Equipment_" + System.currentTimeMillis();
    Equipment equipment = new Equipment(uniqueName, categoryId, "Created by JUnit", "Good", "Available");

    int created = equipmentDao.createEquipment(equipment);
    assertTrue("Expected createEquipment to affect 1+ rows", created > 0);

    Integer equipmentId = TestDb.findLatestEquipmentIdByName(uniqueName);
    assertNotNull("Could not find inserted equipment id", equipmentId);

    Equipment fromDb = equipmentDao.findEquipmentById(equipmentId);
    assertNotNull(fromDb);
    assertEquals(uniqueName, fromDb.getEquipmentName());
    assertEquals(categoryId, fromDb.getCategoryId());

    // Update
    fromDb.setStatus("Under Maintenance");
    int updated = equipmentDao.updateEquipment(fromDb);
    assertTrue("Expected updateEquipment to affect 1+ rows", updated > 0);

    Equipment updatedFromDb = equipmentDao.findEquipmentById(equipmentId);
    assertNotNull(updatedFromDb);
    assertEquals("Under Maintenance", updatedFromDb.getStatus());

    // Delete
    int deleted = equipmentDao.deleteEquipment(equipmentId);
    assertTrue("Expected deleteEquipment to affect 1+ rows", deleted > 0);

    Equipment afterDelete = equipmentDao.findEquipmentById(equipmentId);
    assertNull("Expected findEquipmentById to return null after delete", afterDelete);
  }
}


