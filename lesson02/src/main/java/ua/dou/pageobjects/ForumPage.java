package ua.dou.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class ForumPage extends BasePage{
    private static final String urlOfForum = "https://dou.ua/forums/";
    private static final String titleOfForumPage = "Форум программистов | DOU";
    private static final By ForumFromTopPanel = new By.ByXPath(".//a[text() = 'Форум']");
    private static By specificArticleInTheForum;
    private static By articlesInTheForum = new By.ByXPath(".//article");
    private static final By nameOfAnArticle = new By.ByXPath(".//article[@class = 'b-typo']/h1");

    public ForumPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(urlOfForum);
    }

    public String getTheTitle() {
        return titleOfForumPage;
    }

    public By getForumFromTopPanel() {
        return ForumFromTopPanel;
    }

    private static void setNumOfAnArticleInTheForum(int numOfTheArticle) {
        ForumPage.specificArticleInTheForum = new By.ByXPath(".//article["+ numOfTheArticle +"]/h2/a");;
    }

    public void openArticleInTheForum(int numOfTheArticle) {
        setNumOfAnArticleInTheForum(numOfTheArticle);
        open();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(specificArticleInTheForum));

        driver.findElement(specificArticleInTheForum).click();
    }

    public String getNameOfAnArticle() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(nameOfAnArticle));
        return driver.findElement(nameOfAnArticle).getText();
    }

    public int countTopicsOnThePage() {
        List<WebElement> arrOfTopics = new ArrayList<WebElement>();

        open();
        arrOfTopics = driver.findElements(articlesInTheForum);
        return arrOfTopics.size();
    }

}