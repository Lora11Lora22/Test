package tests;

import org.testng.annotations.Test;
import steps.RunTestSteps;

public class LoginTest extends BaseTest {

    RunTestSteps steps1;

    @Test
    public void testSuccessLoginAndLogout() {
        visit("");
        steps1 = steps.doLogin("standard_user", "secret_sauce");
        steps1.verifyThatTestStarted();
        steps1.verifyLogout();
    }

    @Test
    public void testLockedUser() {
        visit("");
        steps1 = steps.doLogin("locked_out_user", "secret_sauce");
        steps1.verifyThatUserLocked();
    }

    @Test
    public void testIncorrectLoginDetails() {
        visit("");
        steps1 = steps.doLogin("standard_user", "Test1234#");
        steps1.verifyInvalidLoginDetails();
    }

    @Test
    public void testEmptyFields() {
        visit("");
        steps1 = steps.doLogin("", "");
        steps1.verifyThatNotLoggedIn();
    }


}
