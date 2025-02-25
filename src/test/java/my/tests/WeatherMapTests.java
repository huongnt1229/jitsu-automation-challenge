package my.tests;


import my.core.BaseTest;
import my.core.PageFactoryManager;
import my.pages.WeatherMapHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WeatherMapTests extends BaseTest {
    @Test
    public void testWeatherSearch(){
        String weatherMapUrl = "https://openweathermap.org/";
        String cityName = "Los Angeles, US";
        String timeZone = "America/Los_Angeles";
        WeatherMapHomePage weatherMapHomePage = PageFactoryManager.get(WeatherMapHomePage.class);

        //Open the Weather Map and search the city
        weatherMapHomePage.navigateToUrl(weatherMapUrl);
        weatherMapHomePage.inputCityname(cityName)
                .clickSearchBtn()
                .selectCityOptions(cityName)
                .waitForPageLoaded();
        //Verify result
        weatherMapHomePage.verifyCityName((cityName))
                .verifyCurrentDateUS(timeZone)
                .verifyTemperature();
    }
}
