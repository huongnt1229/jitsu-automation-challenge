package my.core;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    private WebDriverWait wait;
    By byLocator;

    private static final long DEFAULT_TIMEOUT = 30; // Seconds
    public BasePage(){
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
    }

    public void navigateToUrl(String url) {
        driver.get(url);
        waitForPageLoaded();
    }
    public void waitForPageLoaded() {
        wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }
    public void dismissAlertIfExists() {
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            if (alert != null) {
                alert.dismiss();
            }
        } catch (org.openqa.selenium.TimeoutException e) {
            // No alert was present within the timeout.
        } catch (org.openqa.selenium.NoAlertPresentException e) {
            //No alert was present.
        }
    }
    public WebElement waitForElementClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitForElementVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForElementVisible(String locator, String... values) {
        locator = String.format(locator, (Object[])values);
        this.byLocator = By.xpath(locator);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(this.byLocator));
    }

    public void sendKeysToElement(WebElement element, String text) {
        WebElement visibleElement = waitForElementVisible(element);
        visibleElement.clear();
        visibleElement.sendKeys(text);
    }

    public void clickOnElement(WebElement element) {
        WebElement clickableElement = waitForElementClickable(element);
        clickableElement.click();
    }

    public void clickOnElement(String locator, String... values) {
        WebElement visibleElement = waitForElementVisible(locator, values);
        clickOnElement(visibleElement);
    }

    public String getTextElement(WebElement element) {
        WebElement visibleElement = waitForElementVisible(element);
        return visibleElement.getText();
    }

    public boolean waitUntilTextVisibleInElement(WebElement element, String text) {
        return wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }
}
