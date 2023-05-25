package tests;

import org.testng.annotations.Test;
import steps.RunTestSteps;

public class BuyProcessTest extends BaseTest {


    RunTestSteps steps1;


    @Test(priority = 1)
    public void test1() {
        visit("");
        steps1 = steps.doLogin("standard_user", "secret_sauce");
        steps1.verifyThatTestStarted();
    }

    @Test(dependsOnMethods = "test1", priority = 2)
    public void checkItemInformation() {
        steps1.countItemOnMainPage();
        steps1.productDescription();
    }

    @Test(dependsOnMethods = "test1", priority = 3)
    public void cartClean() {
        steps1.cleanCart();
        steps1.returnToShopping();
    }

    @Test(dependsOnMethods = "test1", priority = 4)
    public void buyProduct() {
        steps1.addItemToCart();
        steps1.checkoutProcess();
    }

    @Test(dependsOnMethods = "test1", priority = 5)
    public void cancelOfPurchase() {
        steps1.addItemToCart();
        steps1.cancelCheckoutProcess();

    }

}
