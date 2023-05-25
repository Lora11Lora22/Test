package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


public class RunTestPage extends BasePage {

    ///////////////////////////////// LOGIN PAGE ///////////////////////////////////////

    @FindBy(css = "#header_container > div.primary_header > div.header_label > div")
    WebElement header;

    @FindBy(xpath = "//*[@id=\"login_button_container\"]/div/form/div[3]")
    WebElement errorMessage;

    @FindBy(id = "root")
    WebElement loginPage;

    ///////////////////////////////// MAIN PAGE ///////////////////////////////////////

    @FindBy(id = "react-burger-menu-btn")
    WebElement menuButton;

    @FindBy(id = "shopping_cart_container")
    WebElement shoppingCartButton;

    @FindBy(id = "inventory_container")
    WebElement catalogItems;

    @FindBy(id = "add-to-cart-sauce-labs-bolt-t-shirt")
    WebElement addItem;

    @FindBy(xpath = "//*[@id=\"page_wrapper\"]/footer/ul/li[1]/a")
    WebElement twitterButton;

    @FindBy(xpath = "//*[@id=\"page_wrapper\"]/footer/ul/li[2]/a")
    WebElement facebookButton;

    @FindBy(xpath = "//*[@id=\"page_wrapper\"]/footer/ul/li[3]/a")
    WebElement linkedinButton;

    ///////////////////////////////// SIDEBAR ///////////////////////////////////////

    @FindBy(id = "inventory_sidebar_link")
    WebElement sidebarAllItemsButton;

    @FindBy(id = "react-burger-cross-btn")
    WebElement closeSidebarButton;

    @FindBy(id = "about_sidebar_link")
    WebElement sidebarAboutButton;

    @FindBy(id = "reset_sidebar_link")
    WebElement sidebarResetAppStateButton;

    @FindBy(id = "logout_sidebar_link")
    WebElement sidebarLogoutButton;

    ///////////////////////////////// CART PAGE - CHECKOUT PROCESS ///////////////////////////////////////

    @FindBy(id = "continue-shopping")
    WebElement continueShoppingButton;

    @FindBy(id = "remove-sauce-labs-bolt-t-shirt")
    WebElement removeButtonInCart;

    @FindBy(id = "checkout")
    WebElement checkoutButton;

    @FindBy(id = "cart_contents_container")
    WebElement cartContainer;

    @FindBy(xpath = "//*[@id=\"header_container\"]/div[2]")
    WebElement headerCheckoutPage;

    @FindBy(id = "continue")
    WebElement continueButton;

    @FindBy(id = "cancel")
    WebElement cancelButton;

    @FindBy(css = "#checkout_info_container > div > form > div.checkout_info > div.error-message-container.error")
    WebElement errorMessageCheckoutPage;

    @FindBy(id = "first-name")
    WebElement fieldFirstName;

    @FindBy(id = "last-name")
    WebElement fieldLastName;

    @FindBy(id = "postal-code")
    WebElement fieldPostalCode;

    @FindBy(id = "checkout_summary_container")
    WebElement checkoutSummaryContainer;

    @FindBy(id = "finish")
    WebElement finishButton;

    @FindBy(xpath = "//*[@id=\"checkout_complete_container\"]")
    WebElement textSuccessMessage;

    @FindBy(id = "back-to-products")
    WebElement backHomeButton;

    ///////////////////////////////////////  METHODS  ///////////////////////////////////////

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
        (sidebarLogoutButton).click();
        makePause(2000);
        assertThat(loginPage.isDisplayed()).isTrue();
    }

    ///////////////////////// BUY PROCESS METHODS/////////////////////////////////

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
        fieldFirstName.sendKeys("TestQA");
        fieldLastName.clear();
        fieldLastName.sendKeys("TestQA");
        fieldPostalCode.clear();
        fieldPostalCode.sendKeys("123Test");
        continueButton.click();
        checkoutSummaryContainer.isDisplayed();

        WebElement paymentInfoElement = driver.findElement(By.cssSelector("#checkout_summary_container > div > div.summary_info > div:nth-child(2)"));
        String paymentInfoText = paymentInfoElement.getText();
        System.out.println("Payment Information\n" + paymentInfoText);

        WebElement shippingInfoElement = driver.findElement(By.cssSelector("#checkout_summary_container > div > div.summary_info > div:nth-child(4)"));
        String shippingInfoText = shippingInfoElement.getText();
        System.out.println("Shipping Information\n" + shippingInfoText);

        WebElement subtotalLabelElement = driver.findElement(By.cssSelector("#checkout_summary_container > div > div.summary_info > div.summary_subtotal_label"));
        String subtotalLabelText = subtotalLabelElement.getText();
        System.out.println(subtotalLabelText);

        WebElement taxLabelElement = driver.findElement(By.cssSelector("#checkout_summary_container > div > div.summary_info > div.summary_tax_label"));
        String taxLabelText = taxLabelElement.getText();
        System.out.println(taxLabelText);

        WebElement totalLabelElement = driver.findElement(By.cssSelector("#checkout_summary_container > div > div.summary_info > div.summary_info_label.summary_total_label"));
        String totalLabelText = totalLabelElement.getText();
        System.out.println(totalLabelText);

        cancelButton.isDisplayed();
        finishButton.click();
        assertThat(textSuccessMessage.getText()).isEqualTo("Thank you for your order!\n" +
                "Your order has been dispatched, and will arrive just as fast as the pony can get there!\n" +
                "Back Home");
        backHomeButton.click();
        assertThat(catalogItems.isDisplayed()).isTrue();
    }

    public void checkCancellationOfPurchase(String expectedItemTitle) {
        assertThat(shoppingCartButton.isDisplayed()).isTrue();
        shoppingCartButton.click();
        assertThat(checkoutButton.isDisplayed()).isTrue();
        checkoutButton.click();
        fieldFirstName.clear();
        fieldFirstName.sendKeys("TestQA");
        fieldLastName.clear();
        fieldLastName.sendKeys("TestQA");
        fieldPostalCode.clear();
        fieldPostalCode.sendKeys("123Test");
        continueButton.click();
        cancelButton.isDisplayed();
        cancelButton.click();
        assertThat(catalogItems.isDisplayed()).isTrue();
        assertThat(shoppingCartButton.isDisplayed()).isTrue();
        shoppingCartButton.click();
        assertThat(cartContainer.isDisplayed()).isTrue();

        WebElement itemTitleElement = driver.findElement(By.id("item_1_title_link"));
        String actualItemTitle = itemTitleElement.getText();
        System.out.println(actualItemTitle);
        assertThat(actualItemTitle).isEqualTo(expectedItemTitle);
        removeButtonInCart.click();
    }

    public void itemOnPage() {
        List<WebElement> items = driver.findElements(By.xpath("//*[@id=\"inventory_container\"]/div/div"));
        int itemCount = items.size();
        System.out.println("Item on Page: " + itemCount);
    }

    public void descriptionProduct() {
        List<WebElement> items = driver.findElements(By.xpath("//*[@id=\"inventory_container\"]/div/div"));
        for (WebElement item : items) {
            boolean hasPrice = item.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[2]/div[2]/div[2]/div")).isDisplayed();
            boolean hasDescription = item.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[2]/div[2]/div")).isDisplayed();
            boolean hasPhoto = item.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[2]/div")).isDisplayed();
            System.out.println("The item contains a price: " + hasPrice);
            System.out.println("The item contains a description: " + hasDescription);
            System.out.println("The item contains a photo: " + hasPhoto);
        }

         ///////////////////////// MAIN PAGE VERIFIER METHODS/////////////////////////////////
    }
    public void dropdownMenuTest() {
        WebElement dropdownElement = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select"));
        Select dropdown = new Select(dropdownElement);
        int optionsCount = dropdown.getOptions().size();

        for (int i = 0; i < optionsCount; i++) {
            WebElement option = dropdown.getOptions().get(i);
            String optionText = option.getText();
            dropdown.selectByIndex(i);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            dropdownElement = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select"));
            dropdown = new Select(dropdownElement);

            if (dropdown.getFirstSelectedOption().getText().equals(optionText)) {
                System.out.println("Option \"" + optionText + "\" selected successfully. ");
            } else {
                System.out.println("Option selection error \"" + optionText + "\".");
            }
        }
    }

    public void sidebarMenuTest() {
        menuButton.click();
        makePause(2000);
        assertThat(sidebarAllItemsButton.isDisplayed()).isTrue();
        assertThat(sidebarAboutButton.isDisplayed()).isTrue();
        assertThat(sidebarResetAppStateButton.isDisplayed()).isTrue();
        assertThat(sidebarLogoutButton.isDisplayed()).isTrue();
        closeSidebarButton.click();
        makePause(Variables.SHORT_SLEEP);
        assertThat(sidebarAllItemsButton.isDisplayed()).isFalse();
        menuButton.click();
        sidebarAllItemsButton.click();
        makePause(5000);
        sidebarAboutButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://saucelabs.com/"));
         String currentUrl = driver.getCurrentUrl();
        if (currentUrl.equals("https://saucelabs.com/")) {
            System.out.println("Navigation was successful.");
        } else {
            System.out.println("Navigation error.");
        }
            driver.navigate().back();
        makePause(Variables.NORMAL_SLEEP);
    }

    public void twitterLink() {
        String mainWindowHandle = driver.getWindowHandle();
        jsclick(twitterButton);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            if (!handle.equals(mainWindowHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.contains("https://twitter.com/saucelabs")) {
            System.out.println("Navigation to the Twitter page was successful.");
        } else {
            System.out.println("Error navigation to the Twitter page.");
        }
    }

    public void facebookLink() {
        String mainWindowHandle = driver.getWindowHandle();
        jsclick(facebookButton);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            if (!handle.equals(mainWindowHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.contains("https://www.facebook.com/saucelabs")) {
            System.out.println("Navigation to the Facebook page was successful.");
        } else {
            System.out.println("Error navigation to the Facebook page.");
        }
    }

    public void linkedinLink() {
        driver.navigate().refresh();
        makePause(Variables.LONG_SLEEP);
        String mainWindowHandle = driver.getWindowHandle();
        jsclick(linkedinButton);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            if (!handle.equals(mainWindowHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.contains("https://www.linkedin.com/authwall?trk=gf&trkInfo=AQG3S24ekjLK7AAAAYhQCaOInnw189HxSUzu9VVot4ZsP2INOg42I_" +
                "qgFEw-ne4goS7bNWuFD_UtfyTLAbDOB30Ky9vHEvGA8dixnakp_OzwmyhWw9sJ0C5k5fhNPEXb70eT2dY=&original_" +
                "referer=&sessionRedirect=https%3A%2F%2Fwww.linkedin.com%2Fcompany%2Fsauce-labs%2F")) {
            System.out.println("Navigation to the Linkedin page was successful.");
        } else {
            System.out.println("Error navigation to the Linkedin page.");
        }
    }




}
