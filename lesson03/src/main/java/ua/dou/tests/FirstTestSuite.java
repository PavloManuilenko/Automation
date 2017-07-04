package ua.dou.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ua.dou.pageobjects.BasePage;
import ua.dou.pageobjects.ForumPage;
import ua.dou.pageobjects.HomePage;
import ua.dou.pageobjects.NewsFeedPage;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FirstTestSuite {
    private WebDriver driver;
    private HomePage homePage;
    private ForumPage forumPage;
    private NewsFeedPage newsFeedPage;

    @Before
    public void setUp() throws InterruptedException {
        driver = BasePage.makeTheDriver("./src/main/resources/config.properties");
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
        homePage.open();
        assertEquals("Wrong title of the Page!", homePage.getTheTitle(), driver.getTitle());
    }

    @Test
    public void verifyingTheTitleOfForumPage() {
        forumPage.open();
        assertEquals("Wrong title of the Page!", forumPage.getTheTitle(), driver.getTitle());
    }

    @Test
    public void verifyingTheTitleOfNewsFeedPage() {
        newsFeedPage.open();
        assertEquals("Wrong title of the Page!", newsFeedPage.getTheTitle(), driver.getTitle());
    }

    @Test
    public void verifyingOfTopPanelIsVisible() {
        homePage.open();
        Assert.assertTrue(homePage.isTopPanelVisible());
    }

    @Test
    public void openingForumFromTopPanel() {
        forumPage.openFromTopPanel();
        assertEquals("Wrong title of the Page!", forumPage.getTheTitle(), driver.getTitle());
    }

    @Test
    public void openingNewsFeedFromTopPanel() {
        newsFeedPage.openFromTopPanel();
        assertEquals("Wrong title of the Page!", newsFeedPage.getTheTitle(), driver.getTitle());
    }

    @Test
    public void gettingTheNameOfTheFirstArticleInForum() {
        forumPage.openArticleInTheForum(1);
        System.out.println("The name of the first article is: "+ forumPage.getNameOfAnArticle());
    }

    @Test
    public void countingTopicsOnForumPage() {
        forumPage.open();
        System.out.println("There are " + forumPage.countTopicsOnThePage() + " topics on one Forum page");
    }

    @Test
    public void gettingTheNameOfTheLastArticleOnForumPage() {
        forumPage.openArticleInTheForum(forumPage.countTopicsOnThePage());
        System.out.println("The name of the Last article on Forum page: "+ forumPage.getNameOfAnArticle());
    }

    @Test
    public void countingEventsInTheBlock() {
        newsFeedPage.open();
        System.out.println("There are " + newsFeedPage.countEventsInTheBlock() + " events in the block");
    }

    @Test
    public void gettingEventsDateAndCityFromTheBlock() {
        newsFeedPage.open();
        List<String> listOfEventsDateAndCity = newsFeedPage.getListOfEventsDateAndCityFromTheBlock();

        for (String str : listOfEventsDateAndCity) {
            System.out.println(str);
        }
    }

    @Test
    public void gettingListOfEventsDates() {
        newsFeedPage.open();
        ArrayList<String> eventsDates= newsFeedPage.getListOfEventsDates();

        for (int i = 0; i < eventsDates.size(); i++) {
            System.out.println(eventsDates.get(i));
        }
    }

}