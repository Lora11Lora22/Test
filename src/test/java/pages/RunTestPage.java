package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.SHORT;

public class RunTestPage extends BasePage{



    @FindBy(css = "#header_container > div.primary_header > div.header_label > div")
    WebElement header;

    @FindBy(xpath = "//*[@id=\"login_button_container\"]/div/form/div[3]")
    WebElement errorMessage;

    @FindBy(id = "root")
    WebElement loginPage;

    @FindBy(id = "react-burger-menu-btn")
    WebElement menuButton;

    @FindBy(id = "inventory_sidebar_link")
    WebElement sidebarAllItemsButton;

    @FindBy(id = "about_sidebar_link")
    WebElement sidebarAboutButton;

    @FindBy(id = "reset_sidebar_link")
    WebElement sidebarResetAppStateButton;

  /*  @FindBy(className = "bm-item menu-item")
    List<WebElement> sidebarButtons;*/

    @FindBy(id = "logout_sidebar_link")
    WebElement  sidebarLogoutButton;

    @FindBy(id = "shopping_cart_container")
    WebElement  shoppingCartButton;

    @FindBy(id = "inventory_container")
    WebElement  catalogItems;


    @FindBy(id = "continue-shopping")
    WebElement  continueShoppingButton;

    @FindBy(id = "add-to-cart-sauce-labs-bolt-t-shirt")
    WebElement  addItem;

    @FindBy(id = "remove-sauce-labs-bolt-t-shirt")
    WebElement  removeButtonInCart;

    @FindBy(id = "checkout")
    WebElement  checkoutButton;

    @FindBy(xpath = "//*[@id=\"header_container\"]/div[2]")
    WebElement  headerCheckoutPage;

    @FindBy(id = "continue")
    WebElement  continueButton;

    @FindBy(id = "cancel")
    WebElement  cancelButton;

    @FindBy(css = "#checkout_info_container > div > form > div.checkout_info > div.error-message-container.error")
    WebElement  errorMessageCheckoutPage;


    @FindBy(id = "first-name")
    WebElement  fieldFirstName;

    @FindBy(id = "last-name")
    WebElement  fieldLastName;

    @FindBy(id = "postal-code")
    WebElement  fieldPostalCode;

    @FindBy(id = "checkout_summary_container")
    WebElement  checkoutSummaryContainer;


    @FindBy(id = "finish")
    WebElement  finishButton;

    @FindBy(id = "#checkout_complete_container > h2")
    WebElement  successMessage;

    @FindBy(xpath = "//*[@id=\"checkout_complete_container\"]")
    WebElement  textSuccessMessage;


    @FindBy(id = "back-to-products")
    WebElement  backHomeButton;







    public void assertThatHeaderExists() {
        assertThat(header.getText()).isEqualTo("Swag Labs");
        assertThat(shoppingCartButton.isDisplayed()).isTrue();
        assertThat(menuButton.isDisplayed()).isTrue();
        assertThat(catalogItems.isDisplayed()).isTrue();
    }

    public void assertThatLockedUser() {
        makePause(1000);
        assertThat(errorMessage.getText()).isEqualTo("Epic sadface: Sorry, this user has been locked out.");
    }

    public void assertInvalidLogin() {
        makePause(1000);
        assertThat(errorMessage.getText()).isEqualTo("Epic sadface: Username and password do not match any user in this service");
    }

    public void assertNotLoggedIn() {
        makePause(1000);
        assertThat(errorMessage.getText()).isEqualTo("Epic sadface: Username is required");
        assertThat(loginPage.isDisplayed()).isTrue();
    }

    public void assertCheckLogout() {
        makePause(2000);
        assertThat(menuButton.isDisplayed()).isTrue();
        (menuButton).click();
        makePause(2000);
        assertThat(sidebarAllItemsButton.isDisplayed()).isTrue();
        assertThat(sidebarAboutButton.isDisplayed()).isTrue();
        assertThat(sidebarResetAppStateButton.isDisplayed()).isTrue();
        assertThat(sidebarLogoutButton.isDisplayed()).isTrue();
      //  assertThat(sidebarButtons.size()).isEqualTo(4);
        (sidebarLogoutButton).click();
        makePause(2000);
        assertThat(loginPage.isDisplayed()).isTrue();
    }

    public void addItem() {
        assertThat(catalogItems.isDisplayed()).isTrue();
        (addItem).click();
    }

    public void cartClean() {
        assertThat(shoppingCartButton.isDisplayed()).isTrue();
        (shoppingCartButton).click();
        java.util.List<WebElement> deleteButtons = driver.findElements(By.xpath("//*[@class=\"btn btn_secondary btn_small cart_button\"]"));
        int cartItemCount = deleteButtons.size();
        while (cartItemCount > 0) {
            deleteButtons.get(0).click();
            driver.switchTo().alert().accept();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            deleteButtons = driver.findElements(By.xpath("//*[@class=\"btn btn_secondary btn_small cart_button\"]"));
            cartItemCount = deleteButtons.size();
        }
    }

    public void continueShopping() {
        makePause(2000);
        (continueShoppingButton).click();
        assertThat(catalogItems.isDisplayed()).isTrue();
    }

    public void fillDeliveryDetails() {
        (shoppingCartButton).click();
        assertThat(removeButtonInCart.isDisplayed()).isTrue();
        assertThat(continueShoppingButton.isDisplayed()).isTrue();
        assertThat(checkoutButton.isDisplayed()).isTrue();
        (checkoutButton).click();
        assertThat(headerCheckoutPage.getText()).isEqualTo("Checkout: Your Information");
        assertThat(continueButton.isDisplayed()).isTrue();
        assertThat(cancelButton.isDisplayed()).isTrue();
        continueButton.click();
        assertThat(errorMessageCheckoutPage.getText()).isEqualTo("Error: First Name is required");
        fieldFirstName.clear();
        fieldFirstName.sendKeys("TestQa");
        fieldLastName.clear();
        fieldLastName.sendKeys("TestQA");
        fieldPostalCode.clear();
        fieldPostalCode.sendKeys("123Test");
        continueButton.click();
        checkoutSummaryContainer.isDisplayed();
        checkoutSummaryContainer.getText();
        System.out.println(checkoutSummaryContainer);
        cancelButton.isDisplayed();
        finishButton.click();
      //  assertThat(successMessage.getText()).isEqualTo("Thank you for your order!");
        assertThat(textSuccessMessage.getText()).isEqualTo("Thank you for your order!\n" +
                "Your order has been dispatched, and will arrive just as fast as the pony can get there!\n" +
                "Back Home");
        backHomeButton.click();
        assertThat(catalogItems.isDisplayed()).isTrue();
    }
}
