package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.BaseTest;

public class BasePage {
    protected WebDriver driver;
    public WebDriverWait wait;

    public BasePage(){
        driver = BaseTest.getDriver();
        PageFactory.initElements(driver, this);
    }

    public void makePause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public WebElement clickWhenReady(WebElement element) {
        makePause(Variables.SHORT_SLEEP);
        waitForVisible(element);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        return element;

    }

    public WebElement waitForVisible(WebElement element){
        waitForJSToBeLoaded();
        wait.until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    public void waitForJSToBeLoaded() {
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public WebElement jsclick (WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
        return element;
    }


}
