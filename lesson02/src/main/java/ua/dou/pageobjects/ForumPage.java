package ua.dou.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class ForumPage extends BasePage{
    private static final String urlOfForum = "https://dou.ua/forums/";
    private static final String titleOfForumPage = "Форум программистов | DOU";
    private static final By ForumFromTopPanel = new By.ByXPath(".//a[text() = 'Форум']");
    private static By articleInTheForum;
    //private static final By firstArticleInTheForum = new By.ByXPath(".//article["+ 1 +"]/h2/a");
    private static final By nameOfAnArticle = new By.ByXPath(".//article[@class = 'b-typo']/h1");

    //private final WebDriver driver;

    public ForumPage(WebDriver driver) {
        super(driver);
    }

    public String getURL() {
        return urlOfForum;
    }

    public String getTheTitle() {
        return titleOfForumPage;
    }

    public static void setNumOfAnArticleInTheForum(int numOfTheArticle) {
        ForumPage.articleInTheForum = new By.ByXPath(".//article["+ numOfTheArticle +"]/h2/a");;
    }

    public void openArticleInTheForum(int numOfTheArticle) {
        //driver.get(super.getBaseURL());
        driver.findElement(ForumFromTopPanel).click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(articleInTheForum));

        driver.findElement(articleInTheForum).click();
    }

    public String getNameOfAnArticle(int numOfTheArticle) {
        openArticleInTheForum(numOfTheArticle);
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(nameOfAnArticle));
        return driver.findElement(nameOfAnArticle).getText();
    }

}
