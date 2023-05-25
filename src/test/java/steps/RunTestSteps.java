package steps;

import pages.RunTestPage;

public class RunTestSteps {

    RunTestPage page = new RunTestPage();

    ///////////////////////// LOGIN METHODS/////////////////////////////////

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

    ///////////////////////// BUY PROCESS METHODS/////////////////////////////////

    public void countItemOnMainPage() {
        page.itemOnPage();
    }

    public void productDescription() {
        page.descriptionProduct();
    }

    public void cancelCheckoutProcess() {
        page.checkCancellationOfPurchase("Sauce Labs Bolt T-Shirt");
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

    ///////////////////////// MAIN PAGE VERIFIER METHODS/////////////////////////////////

    public void verifyThatDropdownWork() {
        page.dropdownMenuTest();
    }

    public void verifyThatSidebarWork() {
        page.sidebarMenuTest();
    }

    public void verifyThatTwitterLinkOpen() {
        page.twitterLink();
    }

    public void verifyThatFacebookLinkOpen() {
        page.facebookLink();
    }

    public void verifyThatLinkedinLinkOpen() {
        page.linkedinLink();
    }

}
