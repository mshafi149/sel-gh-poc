package stepdefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import utils.DatabaseHelper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DatabaseSteps {

    private DatabaseHelper dbHelper;
    private Map<String, Object> retrievedUser;
    private List<Map<String, Object>> retrievedUsers;
    private Exception caughtException;

    @Before("@database")
    public void setupDatabase() throws SQLException {
        dbHelper = new DatabaseHelper();
        dbHelper.connect();
        System.out.println("✅ Database test setup complete");
    }

    @After("@database")
    public void teardownDatabase() throws SQLException {
        if (dbHelper != null) {
            dbHelper.dropUsersTable();
            dbHelper.disconnect();
            System.out.println("✅ Database test cleanup complete");
        }
    }

    @Given("the database connection is established")
    public void the_database_connection_is_established() throws SQLException {
        Assert.assertNotNull(dbHelper, "Database helper should be initialized");
        System.out.println("✅ Database connection verified");
    }

    @When("I create a users table")
    public void i_create_a_users_table() throws SQLException {
        dbHelper.createUsersTable();
    }

    @Then("the users table should exist")
    public void the_users_table_should_exist() throws SQLException {
        boolean exists = dbHelper.tableExists("users");
        Assert.assertTrue(exists, "Users table should exist");
    }

    @When("I insert a user with id {int}, name {string}, and email {string}")
    public void i_insert_a_user(Integer id, String name, String email) throws SQLException {
        dbHelper.insertUser(id, name, email);
    }

    @When("I insert the following users:")
    public void i_insert_the_following_users(DataTable dataTable) throws SQLException {
        List<Map<String, String>> users = dataTable.asMaps();
        for (Map<String, String> user : users) {
            int id = Integer.parseInt(user.get("id"));
            String name = user.get("name");
            String email = user.get("email");
            dbHelper.insertUser(id, name, email);
        }
    }

    @Then("I should be able to retrieve user with id {int}")
    public void i_should_be_able_to_retrieve_user(Integer id) throws SQLException {
        retrievedUser = dbHelper.getUserById(id);
        Assert.assertNotNull(retrievedUser, "User with id " + id + " should exist");
    }

    @Then("the user name should be {string}")
    public void the_user_name_should_be(String expectedName) {
        String actualName = (String) retrievedUser.get("name");
        Assert.assertEquals(actualName, expectedName, "User name should match");
    }

    @Then("the user email should be {string}")
    public void the_user_email_should_be(String expectedEmail) {
        String actualEmail = (String) retrievedUser.get("email");
        Assert.assertEquals(actualEmail, expectedEmail, "User email should match");
    }

    @Then("the users table should have {int} records")
    public void the_users_table_should_have_records(Integer expectedCount) throws SQLException {
        int actualCount = dbHelper.getUserCount();
        Assert.assertEquals(actualCount, expectedCount.intValue(),
                "Expected " + expectedCount + " users but found " + actualCount);
    }

    @When("I update user with id {int} to have email {string}")
    public void i_update_user_email(Integer id, String newEmail) throws SQLException {
        dbHelper.updateUserEmail(id, newEmail);
    }

    @Then("the user with id {int} should have email {string}")
    public void the_user_should_have_email(Integer id, String expectedEmail) throws SQLException {
        Map<String, Object> user = dbHelper.getUserById(id);
        String actualEmail = (String) user.get("email");
        Assert.assertEquals(actualEmail, expectedEmail, "User email should be updated");
    }

    @When("I delete user with id {int}")
    public void i_delete_user(Integer id) throws SQLException {
        dbHelper.deleteUser(id);
    }

    @Then("the user with id {int} should not exist")
    public void the_user_should_not_exist(Integer id) throws SQLException {
        Map<String, Object> user = dbHelper.getUserById(id);
        Assert.assertNull(user, "User with id " + id + " should not exist");
    }

    @When("I query users with email domain {string}")
    public void i_query_users_by_email_domain(String domain) throws SQLException {
        retrievedUsers = dbHelper.getUsersByEmailDomain(domain);
    }

    @Then("I should get {int} users")
    public void i_should_get_users(Integer expectedCount) {
        Assert.assertEquals(retrievedUsers.size(), expectedCount.intValue(),
                "Expected " + expectedCount + " users but found " + retrievedUsers.size());
    }

    @When("I attempt to insert a user with id {int}, name {string}, and email {string}")
    public void i_attempt_to_insert_duplicate_user(Integer id, String name, String email) {
        try {
            dbHelper.insertUser(id, name, email);
        } catch (SQLException e) {
            caughtException = e;
        }
    }

    @Then("a duplicate key exception should be thrown")
    public void a_duplicate_key_exception_should_be_thrown() {
        Assert.assertNotNull(caughtException, "An exception should have been thrown");
        Assert.assertTrue(caughtException.getMessage().contains("Unique index or primary key violation"),
                "Exception should be about duplicate key");
    }
}
