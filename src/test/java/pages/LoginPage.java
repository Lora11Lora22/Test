package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    WebElement loginUser;

    @FindBy(id = "password")
    WebElement loginPassword;

    @FindBy(id = "login-button")
    WebElement loginButton;


    public void fillLoginUser(String text) {
        makePause(3000);
        loginUser.clear();
        loginUser.sendKeys(text);
    }

    public void fillLoginPassword(String text) {
        loginPassword.clear();
        loginPassword.sendKeys(text);
    }

    public void clickLoginButton() {
        loginButton.click();
    }
}
