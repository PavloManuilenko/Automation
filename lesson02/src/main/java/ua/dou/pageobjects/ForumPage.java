package ua.dou.pageobjects;

import org.openqa.selenium.By;

public class ForumPage {
    private static final String urlOfForum = "https://dou.ua/forums/";
    private static final String titleOfForumPage = "Форум программистов | DOU";
    private static final By ForumFromtopPanel = new By.ByXPath(".//a[text() = 'Форум']");
    private static final By firstArticleInTheForum = new By.ByXPath(".//article[1]/h2/a");
    private static final By nameOfAnArticle = new By.ByXPath(".//article[@class = 'b-typo']/h1");

    public String getURL() {
        return urlOfForum;
    }

    public String getTheTitle() {
        return titleOfForumPage;
    }

    public By getForumFromTopPanel() {
        return ForumFromtopPanel;
    }

    public By getFirstArticleInTheForum() {
        return firstArticleInTheForum;
    }

    public By getNameOfAnArticle() {
        return nameOfAnArticle;
    }
}
