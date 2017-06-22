package ua.dou.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ua.dou.pageobjects.ForumPage;
import ua.dou.pageobjects.HomePage;
import ua.dou.pageobjects.NewsFeedPage;

import java.util.ArrayList;

public class FirstTestSuite {
    private static WebDriver driver;
    private HomePage homePage;
    private ForumPage forumPage;
    private NewsFeedPage newsFeedPage;

    @Before
    public void setUp() throws InterruptedException {
        //System.setProperty("webdriver.gecko.driver", "./src/main/resources/geckodriver.exe");
        //driver = new FirefoxDriver();
        System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
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
        driver.get(homePage.getURL());
        homePage.isTitleCorrect(homePage.getTheTitle());
    }

    @Test
    public void verifyingTheTitleOfForumPage() {
        driver.get(forumPage.getURL());
        forumPage.isTitleCorrect(forumPage.getTheTitle());
    }

    @Test
    public void verifyingTheTitleOfNewsFeedPage() {
        driver.get(newsFeedPage.getURL());
        newsFeedPage.isTitleCorrect(newsFeedPage.getTheTitle());
    }

    @Test
    public void verifyingOfTopPanelIsVisible() {
        driver.get(homePage.getURL());
        Assert.assertTrue(homePage.isTopPanelVisible());
    }

    @Test
    public void openingForumFromTopPanel() {
        forumPage.openThePageFromTopPanel(forumPage.getForumFromTopPanel());
        forumPage.isTitleCorrect(forumPage.getTheTitle());
    }

    @Test
    public void openingNewsFeedFromTopPanel() {
        newsFeedPage.openThePageFromTopPanel(newsFeedPage.getLentaFromTopPanel());
        newsFeedPage.isTitleCorrect(newsFeedPage.getTheTitle());
    }

    @Test
    public void gettingTheNameOfTheFirstArticleInForum() {
        forumPage.openArticleInTheForum(1);
        System.out.println("The name of the first article is: "+ forumPage.getNameOfAnArticle());
    }

    @Test
    public void countingTopicsOnForumPage() {
        driver.get(forumPage.getURL());
        System.out.println("There are " + forumPage.countTopicsOnThePage() + " topics on one Forum page");
    }

    @Test
    public void gettingTheNameOfTheLastArticleOnForumPage() {
        forumPage.openArticleInTheForum(forumPage.countTopicsOnThePage());
        System.out.println("The name of the Last article on Forum page: "+ forumPage.getNameOfAnArticle());
    }

    @Test
    public void countingEventsInTheBlock() {
        driver.get(newsFeedPage.getURL());
        System.out.println("There are " + newsFeedPage.countEventsInTheBlock() + " events in the block");
    }

    @Test
    public void gettingEventsDateAndCityFromTheBlock() {
        driver.get(newsFeedPage.getURL());
        newsFeedPage.soutEventsDateAndCityFromTheBlock();
    }

    @Test
    public void gettingListOfEventsDates() {
        driver.get(newsFeedPage.getURL());
        ArrayList<String> eventsDates= newsFeedPage.getListOfEventsDates();

        for (int i = 0; i < eventsDates.size(); i++) {
            System.out.println(eventsDates.get(i));
        }
    }

}