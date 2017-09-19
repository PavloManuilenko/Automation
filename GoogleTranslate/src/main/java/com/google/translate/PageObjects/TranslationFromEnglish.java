package com.google.translate.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class TranslationFromEnglish extends BasePage implements TranslationPage{

    private WebDriver driver;
    private URL url = new URL("https", "translate.google.com", 443, "/#en");

    @FindBy(xpath = ".//a[@id='gt-appname']")
    private WebElement appLogo;

    public TranslationFromEnglish(WebDriver driver) throws MalformedURLException {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void openThePage() {
        driver.get(url.toString());
    }
}
