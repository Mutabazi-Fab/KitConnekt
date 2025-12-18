package dao;

import org.junit.Test;

import static org.junit.Assert.*;

public class EquipmentDaoTest {

  @Test
  public void getEquipmentStatuses_returnsExpectedDefaults() {
    EquipmentDao dao = new EquipmentDao();

    String[] statuses = dao.getEquipmentStatuses();

    assertArrayEquals(new String[] {"Available", "Rented", "Under Maintenance"}, statuses);
  }
}


