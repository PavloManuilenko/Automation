package ua.dou.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public abstract class BasePage {

    private static final String urlOfDOU = "https://dou.ua/";
    WebDriver driver;

   BasePage(WebDriver driver) {
        this.driver = driver;
    }

    static String getBaseURL() {
        return urlOfDOU;
    }

    public abstract void open();

    void openThePageFromTopPanel(By linkOfThePageOnTopPanel) {
        open();
        driver.findElement(linkOfThePageOnTopPanel).click();
    }

    void waitPresenceOfElement(By locator, int timeOutInSeconds) {
        new WebDriverWait(driver, timeOutInSeconds)
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}