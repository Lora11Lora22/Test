package steps;

import pages.RunTestPage;

public class RunTestSteps {

    RunTestPage page = new RunTestPage();

    public void verifyThatTestStarted() {
        page.assertThatHeaderExists();
    }

    public void verifyThatUserLocked() {
        page.assertThatLockedUser();
    }

    public void verifyInvalidLoginDetails() {
        page.assertInvalidLogin();
    }

    public void verifyThatNotLoggedIn() {
        page.assertNotLoggedIn();
    }

    public void verifyLogout() {
        page.assertCheckLogout();
    }

    public void addItemToCart() {
        page.addItem();
    }

    public void cleanCart() {
        page.cartClean();
    }

    public void returnToShopping() {
        page.continueShopping();
    }

    public void checkoutProcess() {
        page.fillDeliveryDetails();
    }
}
