package ua.dou.pageobjects;

import org.openqa.selenium.By;

public class NewsFeedPage {
    private static final String urlOfForum = "https://dou.ua/lenta/";
    private static final String titleOfForumPage = "Новые записи — Лента | DOU";
    private static final By topPanelLenta = new By.ByXPath(".//a[text() = 'Лента']");
    private static final By firstArticleInTheForum = new By.ByXPath(".//article[1]/h2/a");
    private static final By nameOfAnArticle = new By.ByXPath(".//article[@class = 'b-typo']/h1");
    private static final By eventBlock = new By.ByXPath(".//div[@class = 'b-adv-events']");
    private static final By eventFromBlock = new By.ByXPath(".//div[@class = 'adv-event-block']");

}
