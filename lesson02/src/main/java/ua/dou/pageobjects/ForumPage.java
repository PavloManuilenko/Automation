package ua.dou.pageobjects;

import org.openqa.selenium.By;

public class ForumPage {
    private static final String urlOfForum = "https://dou.ua/forums/";
    private static final String titleOfForumPage = "Форум программистов | DOU";
    private static final By topPanelForum = new By.ByXPath(".//a[text() = 'Форум']");
}
