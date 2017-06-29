package ua.dou.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ua.dou.utility.Property;

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

    public static WebDriver makeTheDriver(Property property) {
        WebDriver driver;

        if ("ChromeDriver".equals(property.getTypeOfDriver())) {
            System.setProperty("webdriver.chrome.driver", property.getPathToChromeDriver());
            return driver = new ChromeDriver();
        }
        else {
            System.setProperty("webdriver.gecko.driver", property.getPathToFirefoxDriver());
            return driver = new FirefoxDriver();
        }

    }
}