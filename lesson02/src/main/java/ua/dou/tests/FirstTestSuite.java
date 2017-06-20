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

import static org.junit.Assert.assertEquals;

public class FirstTestSuite {
    private static WebDriver driver;
    private static HomePage homePage = new HomePage();
    private static ForumPage forumPage = new ForumPage();
    private static NewsFeedPage newsFeedPage = new NewsFeedPage();

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
        driver.get(homePage.getURL());
        assertEquals("Wrong title of Home Page", homePage.getTheTitle(), driver.getTitle());
    }

    @Test
    public void verifyingTheTitleOfForumPage() {
        driver.get(forumPage.getURL());
        assertEquals("Wrong title of Forum Page", forumPage.getTheTitle(), driver.getTitle());
    }

    @Test
    public void verifyingTheTitleOfNewsFeedPage() {
        driver.get(newsFeedPage.getURL());
        assertEquals("Wrong title of News Feed Page", newsFeedPage.getTheTitle(), driver.getTitle());
    }

    @Test
    public void gettingTheNameOfFirstArticleInForum() {
        driver.get(homePage.getURL());
        driver.findElement(forumPage.getForumFromTopPanel()).click();

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(forumPage.getFirstArticleInTheForum()));

        driver.findElement(forumPage.getFirstArticleInTheForum()).click();

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(forumPage.getNameOfAnArticle()));

        System.out.println("The name Of the Article is: " + driver.findElement(forumPage.getNameOfAnArticle()).getText());
    }
}
