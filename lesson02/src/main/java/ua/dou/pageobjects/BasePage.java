package ua.dou.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.assertEquals;

public class BasePage {

    private static final String urlOfDOU = "https://dou.ua/";

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public static final String getBaseURL() {
        return urlOfDOU;
    }

    public void isTitleCorrect(String title) {
        assertEquals("Wrong title of the Page!", title, driver.getTitle());
    }

    public void openThePageFromTopPanel(By linkOfThePageOnTopPanel) {
        driver.get(urlOfDOU);
        driver.findElement(linkOfThePageOnTopPanel).click();
    }

}