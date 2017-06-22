package ua.dou.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ua.dou.pageobjects.ForumPage;
import ua.dou.pageobjects.HomePage;
import ua.dou.pageobjects.NewsFeedPage;

public class FirstTestSuite {
    private static WebDriver driver;
    private HomePage homePage;
    private ForumPage forumPage;
    private NewsFeedPage newsFeedPage;

    @Before
    public void setUp() throws InterruptedException {
        //System.setProperty("webdriver.gecko.driver", "./src/main/resources/geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver.exe");
        //driver = new FirefoxDriver();
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
        System.out.println("There are " + forumPage.countingTopicsOnThePage() + " topics on one Forum page");
    }

    @Test
    public void gettingTheNameOfTheLastArticleOnForumPage() {
        forumPage.openArticleInTheForum(forumPage.countingTopicsOnThePage());
        System.out.println("The name of the Last article on Forum page: "+ forumPage.getNameOfAnArticle());
    }

}