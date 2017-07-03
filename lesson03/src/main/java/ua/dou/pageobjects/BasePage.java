package ua.dou.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

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

    public static WebDriver makeTheDriver(String pathToProperties) {

        FileInputStream fileInputStream;
        Properties property = new Properties();

        WebDriver driver = null;

        try {
            fileInputStream = new FileInputStream(pathToProperties);
            property.load(fileInputStream);

            String typeOfDriver = property.getProperty("driverToUse");
            String pathToChromeDriver = property.getProperty("pathToChromeDriver");
            String pathToFirefoxDriver = property.getProperty("pathToFirefoxDriver");

            if (null != typeOfDriver) {
                if ("ChromeDriver".equals(typeOfDriver)) {
                    System.setProperty("webdriver.chrome.driver", pathToChromeDriver);
                    return driver = new ChromeDriver();
                }
                else {
                    System.setProperty("webdriver.gecko.driver", pathToFirefoxDriver);
                    return driver = new FirefoxDriver();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            //**** Default: make ChromeDriver if property wasn't found
            System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver.exe");
            driver = new ChromeDriver();
            //****
        }
        return driver;
    }
}