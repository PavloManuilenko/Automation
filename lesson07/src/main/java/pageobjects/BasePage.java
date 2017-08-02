package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

    private static final String baseURL = "http://skillsup.ua/";
    WebDriver driver;

    BasePage(WebDriver driver) {
        this.driver = driver;
    }

    static String getBaseURL() {
        return baseURL;
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