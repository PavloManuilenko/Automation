package ua.dou.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ua.dou.pageobjects.ForumPage;
import ua.dou.pageobjects.HomePage;
import ua.dou.pageobjects.NewsFeedPage;

import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FirstTestSuite {
    private static WebDriver driver;
    private HomePage homePage;
    private ForumPage forumPage;
    private NewsFeedPage newsFeedPage;

    @Before
    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "./src/main/resources/geckodriver.exe");
        driver = new FirefoxDriver();
        homePage = new HomePage(driver);
        forumPage = new ForumPage(driver);
        newsFeedPage = new NewsFeedPage(driver);
    }

    @After
    public void driverQuit() {
        driver.quit();
    }

    @Test
    public void verifyingTheTitleOfHomePage() {
        homePage.isTitleCorrect(homePage.getURL(), homePage.getTheTitle());
    }

    @Test
    public void verifyingTheTitleOfForumPage() {
        forumPage.isTitleCorrect(forumPage.getURL(), forumPage.getTheTitle());
    }

    @Test
    public void verifyingTheTitleOfNewsFeedPage() {
        newsFeedPage.isTitleCorrect(newsFeedPage.getURL(), newsFeedPage.getTheTitle());
    }

}
