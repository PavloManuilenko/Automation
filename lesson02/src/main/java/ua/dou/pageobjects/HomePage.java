package ua.dou.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private static final String titleOfHomePage = "Сообщество программистов | DOU";
    private static final By topPanel = By.xpath(".//header[@class = 'b-head']/ul");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String getURL() {
        return super.getBaseURL();
    }

    public String getTheTitle() {
        return titleOfHomePage;
    }

    public boolean isTopPanelVisible() {
        return driver.findElement(topPanel).isDisplayed();
    }
}