package my.core;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;
import java.util.Map;

public class DriverManager {
    private static WebDriver driver;

    public static WebDriver createDriver(String browser, boolean  isIncognito) {
        if (driver == null) {
            if ("chrome".equalsIgnoreCase(browser)) {
                ChromeOptions options = new ChromeOptions();
                if (isIncognito) {
                    options.addArguments("--incognito");
                }
                options.addArguments("--disable-geolocation");
                options.addArguments("--disable-extensions");
                options.addArguments("--disable-popup-blocking");
                Map<String, Object> prefs = new HashMap<String, Object>();
                prefs.put("profile.default_content_setting_values.geolocation", 2); // 2: Block geolocation
                options.setExperimentalOption("prefs", prefs);
                driver = new ChromeDriver(options);
            } else if ("firefox".equalsIgnoreCase(browser)) {
                FirefoxOptions options = new FirefoxOptions();
                if (isIncognito) {
                    options.addArguments("-private");
                }
                driver = new FirefoxDriver(options);
            } else {
                throw new IllegalArgumentException("Invalid browser specified: " + browser);
            }
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            throw new IllegalStateException("Driver has not been initialized. Call createDriver first.");
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
