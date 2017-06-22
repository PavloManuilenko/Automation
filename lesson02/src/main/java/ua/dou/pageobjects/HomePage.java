package ua.dou.pageobjects;

import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private static final String titleOfHomePage = "Сообщество программистов | DOU";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String getURL() {
        return super.getBaseURL();
    }

    public String getTheTitle() {
        return titleOfHomePage;
    }
}