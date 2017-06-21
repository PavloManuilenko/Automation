package ua.dou.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertEquals;

public class BasePage {

    private static final String urlOfDOU = "https://dou.ua/";

    protected WebDriver driver;

    public BasePage() {
    }

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public static final String getBaseURL() {
        return urlOfDOU;
    }

    public void isTitleCorrect(String url, String title) {
        driver.get(url);
        assertEquals("Wrong title of the Page!", title, driver.getTitle());
    }


}
