package ua.dou.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ua.dou.pageobjects.HomePage;

import static org.junit.Assert.assertEquals;

public class FirstTestSuite {
    private static WebDriver driver;
    private static HomePage homePage = new HomePage();

    @Before
    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "D:\\GitHub\\Automation\\lesson02\\src\\main\\resources\\geckodriver.exe"); //./src/main/resources/geckodriver.exe
        driver = new FirefoxDriver();
    }

    @After
    public void driverQuit() {
        driver.quit();
    }

    @Test
    public void verifyingTheTitleOfHomePage() {
        driver.get(homePage.getUrlOfHomePage());
        assertEquals("Wrong title of HomePage", homePage.getTheTitle(), driver.getTitle());
    }
}
