package model;

import java.sql.Date;
import org.junit.Test;

import static org.junit.Assert.*;

public class BorrowTest {

  @Test
  public void constructor_setsFieldsCorrectly() {
    Date borrowDate = Date.valueOf("2025-12-01");
    Date returnDate = Date.valueOf("2025-12-08");

    Borrow borrow = new Borrow(5, 9, borrowDate, returnDate, "Active");

    assertEquals(5, borrow.getUserId());
    assertEquals(9, borrow.getEquipmentId());
    assertEquals(borrowDate, borrow.getBorrowDate());
    assertEquals(returnDate, borrow.getReturnDate());
    assertEquals("Active", borrow.getStatus());
  }

  @Test
  public void setters_updateDisplayFields() {
    Borrow borrow = new Borrow();
    borrow.setUserName("Alice");
    borrow.setEquipmentName("Camera");

    assertEquals("Alice", borrow.getUserName());
    assertEquals("Camera", borrow.getEquipmentName());
  }

  @Test
  public void actualReturnDate_canBeSet() {
    Borrow borrow = new Borrow();
    Date actual = Date.valueOf("2025-12-09");
    borrow.setActualReturnDate(actual);

    assertEquals(actual, borrow.getActualReturnDate());
  }
}


