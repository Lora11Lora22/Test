package tests;

import org.testng.annotations.Test;
import steps.RunTestSteps;

public class MainPageVerifier extends BaseTest {

    RunTestSteps steps1;


    @Test(priority = 1)
    public void test1() {
        visit("");
        steps1 = steps.doLogin("standard_user", "secret_sauce");
        steps1.verifyThatTestStarted();
    }


    @Test(dependsOnMethods = "test1", priority = 2)
    public void checkDropdown() {
        steps1.verifyThatDropdownWork();
    }

    @Test(dependsOnMethods = "test1", priority = 3)
    public void checkSidebar() {
        steps1.verifyThatSidebarWork();
    }

     @Test(dependsOnMethods = "test1", priority = 4)
    public void checkTwitterLink() {
        steps1.verifyThatTwitterLinkOpen();
    }

    //TODO work only autonomously
    @Test(dependsOnMethods = "test1", priority = 5)
    public void checkFacebookLink() {
        steps1.verifyThatFacebookLinkOpen();
    }

    //TODO work only autonomously
    @Test(dependsOnMethods = "test1", priority = 6)
    public void checkLinkedinLink() {
        steps1.verifyThatLinkedinLinkOpen();
    }
}
