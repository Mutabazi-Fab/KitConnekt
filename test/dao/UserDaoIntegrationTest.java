package dao;

import model.User;
import org.junit.Assume;
import org.junit.Test;

import static org.junit.Assert.*;
import testutil.TestDb;

/**
 * Integration tests that hit the real PostgreSQL database.
 *
 * This project hard-codes the DB connection details inside the DAO classes, so these tests will
 * only work if you have a local PostgreSQL running with:
 * - database: equipment_borrowing_system_db
 * - user: postgres
 * - password: 123
 * - table: users(user_id, full_name, email, password, is_admin, ...)
 *
 */
public class UserDaoIntegrationTest {

  @Test
  public void saveUser_thenAuthenticateUser_returnsUser() {
    Assume.assumeTrue("PostgreSQL not reachable on localhost:5432", TestDb.canConnect());

    UserDao userDao = new UserDao();

    String uniqueEmail = "junit_" + System.currentTimeMillis() + "@user.com";
    String password = "pw123";

    User toSave = new User("JUnit User", uniqueEmail, password, false);
    assertTrue("Expected saveUser to return true", userDao.saveUser(toSave));

    User authenticated = userDao.authenticateUser(uniqueEmail, password);
    assertNotNull("Expected authenticateUser to return a user", authenticated);
    assertEquals(uniqueEmail, authenticated.getEmail());
    assertEquals("JUnit User", authenticated.getFullName());
    assertFalse(authenticated.isAdmin());

    // Cleanup (best-effort)
    try {
      TestDb.deleteUserByEmail(uniqueEmail);
    } catch (Exception ignored) {
      // ignore cleanup failures
    }
  }
}


