package dao;

import java.util.List;
import model.EquipmentCategory;
import org.junit.Assume;
import org.junit.Test;

import static org.junit.Assert.*;
import testutil.TestDb;

public class EquipmentCategoryDaoIntegrationTest {

  @Test
  public void getAllCategories_returnsAtLeastOneCategory_ifDbHasData() {
    Assume.assumeTrue("PostgreSQL not reachable on localhost:5432", TestDb.canConnect());

    EquipmentCategoryDao dao = new EquipmentCategoryDao();
    List<EquipmentCategory> categories = dao.getAllCategories();

    // If your DB is empty, this might be 0; adjust expectation if needed.
    assertNotNull(categories);
    assertTrue("Expected at least one category in equipment_categories", categories.size() > 0);
  }

  @Test
  public void getCategoryIdByName_returnsValidId_forExistingCategory() {
    Assume.assumeTrue("PostgreSQL not reachable on localhost:5432", TestDb.canConnect());

    EquipmentCategoryDao dao = new EquipmentCategoryDao();
    List<EquipmentCategory> categories = dao.getAllCategories();
    Assume.assumeTrue("No categories exist in DB to test against", categories != null && !categories.isEmpty());

    String name = categories.get(0).getCategoryName();
    int id = dao.getCategoryIdByName(name);

    assertTrue("Expected category id > 0 for existing category", id > 0);
  }
}


