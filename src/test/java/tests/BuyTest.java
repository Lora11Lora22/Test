package tests;

import org.testng.annotations.Test;
import steps.RunTestSteps;

public class BuyTest extends BaseTest {


    RunTestSteps steps1;


    @Test(priority = 1)
    public void test1() {
        visit("");
        steps1 = steps.doLogin("standard_user", "secret_sauce");
        steps1.verifyThatTestStarted();
        steps1.cleanCart();
        steps1.returnToShopping();

    }

    @Test(dependsOnMethods = "test1", priority = 2)
    public void buyProduct() {
        steps1.addItemToCart();
        steps1.checkoutProcess();
    }

}
