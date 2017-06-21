package ua.dou.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewsFeedPage extends BasePage {
    private static final String urlOfForum = "https://dou.ua/lenta/";
    private static final String titleOfForumPage = "Новые записи — Лента | DOU";
    private static final By topPanelLenta = new By.ByXPath(".//a[text() = 'Лента']");
    private static final By eventBlock = new By.ByXPath(".//div[@class = 'b-adv-events']");
    private static final By eventFromBlock = new By.ByXPath(".//div[@class = 'adv-event-block']");

    public NewsFeedPage(WebDriver driver) {
        super(driver);
    }

    public String getURL() {
        return urlOfForum;
    }

    public String getTheTitle() {
        return titleOfForumPage;
    }
}
