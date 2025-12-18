# KitConnekt (Equipment Borrowing System)

KitConnekt is a Java Swing desktop application for managing equipment borrowing, returns, and admin
inventory handling. It uses PostgreSQL for persistence and follows a layered structure:

- `src/model`: domain models (User, Equipment, Borrow, EquipmentCategory)
- `src/dao`: database access (DAO pattern)
- `src/view`: Swing UI forms
- `test`: JUnit tests (unit tests + DB integration tests)

## Requirements

- Java (project targets Java 8)
- PostgreSQL running locally

### Database (current DAO settings)

The DAO classes currently connect using:

- URL: `jdbc:postgresql://localhost:5432/equipment_borrowing_system_db`
- Username: `postgres`
- Password: `123`

## Running (NetBeans)

Open the project and Run using the main class:

- `view.LoginAndSignUp`

## Running tests

JUnit jars and PostgreSQL driver are included under `lib/`.

### In NetBeans

- Right-click project → **Test**
- Or right-click a test file in `test/` → **Test File**

### Integration tests

Integration tests require PostgreSQL to be running and accessible using the connection settings
above. They will auto-skip if the database is not reachable.


