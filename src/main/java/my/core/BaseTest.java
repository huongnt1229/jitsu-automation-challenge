package my.core;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest {
    @Parameters({"browser", "isIncognito"})
    @BeforeMethod
    public void setup() {
        DriverManager.createDriver("chrome", true);
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }

}
