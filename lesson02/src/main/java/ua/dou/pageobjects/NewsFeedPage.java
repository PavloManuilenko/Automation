package ua.dou.pageobjects;

import org.openqa.selenium.By;

public class NewsFeedPage {
    private static final String urlOfForum = "https://dou.ua/lenta/";
    private static final String titleOfForumPage = "Новые записи — Лента | DOU";
    private static final By topPanelLenta = new By.ByXPath(".//a[text() = 'Лента']");
    private static final By eventBlock = new By.ByXPath(".//div[@class = 'b-adv-events']");
    private static final By eventFromBlock = new By.ByXPath(".//div[@class = 'adv-event-block']");

    public String getURL() {
        return urlOfForum;
    }

    public String getTheTitle() {
        return titleOfForumPage;
    }
}
