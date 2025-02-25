package my.pages;
import my.core.BasePage;
import net.bytebuddy.implementation.bytecode.Throw;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Set;

public class WeatherMapHomePage extends BasePage{
    @FindBy(xpath = "//input[@placeholder='Search city']")
    private WebElement searchBox;

    @FindBy(xpath = "//button[text()='Search']")
    private WebElement searchButton;

    @FindBy(xpath = "//div[@class='current-container mobile-padding']//h2")
    private WebElement cityName;

    @FindBy(xpath = "//div[@class='current-container mobile-padding']//span[@class='orange-text']")
    private WebElement currentDate;

    @FindBy(xpath = "//div[@class='current-temp']/span")
    private WebElement temperature;

    private final String searchDropdown = "//ul[@class='search-dropdown-menu']//span[contains(text(),'%s')]";

    public WeatherMapHomePage inputCityname(String city) {
        sendKeysToElement(searchBox, city);
        return this;
    }
    public WeatherMapHomePage clickSearchBtn() {
        clickOnElement(searchButton);
        return this;
    }
    public WeatherMapHomePage selectCityOptions(String city) {
        clickOnElement(searchDropdown, city);
        return this;
    }
    public WeatherMapHomePage verifyCityName(String city) {
        boolean isVisible = waitUntilTextVisibleInElement(cityName, city);
        Assert.assertTrue(isVisible);
        return this;
    }
    public WeatherMapHomePage verifyCurrentDateUS(String zone) {
        String dateTimeStr = getTextElement(currentDate).split(",")[0];
        ZoneId losAngelesZone = ZoneId.of(zone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd");
        String currentDate = LocalDateTime.now(losAngelesZone).format(formatter);
        Assert.assertEquals(currentDate, dateTimeStr);
        return this;
    }
    public String getTemperature() {
        String tempText = getTextElement(temperature);
        return tempText.replaceAll("[^0-9.-]", "");
    }
    public WeatherMapHomePage verifyTemperature() {
        Assert.assertTrue(getTemperature().matches("-?\\d+(\\.\\d+)?"));
        return this;
    }


}
