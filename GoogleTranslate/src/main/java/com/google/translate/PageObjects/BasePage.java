package com.google.translate.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class BasePage {

    private WebDriver driver;
    private URL googleURL = new URL("https", "google.com", 443, "");
    private URL googleLanguageSettings = new URL("https", "google.com", 443, "/preferences#languages");
    private URL baseURL = new URL("https", "translate.google.com", 443, "");
    private String title = new String("Google Translate");

    @FindBy(xpath = ".//a[@id='gb_70']")
    private WebElement signInButton;

    @FindBy(xpath = ".//div[@id='langten']/div/span")
    private WebElement englishLanguageSettingsItem;

    @FindBy(xpath = ".//*[@id='form-buttons']/div[1]")
    private WebElement submitLanguageSettingsButton;

    @FindBy(xpath = ".//a[contains(text(), 'Google.com')]")
    private WebElement offerToUseGoogleCOM;

    public BasePage(WebDriver driver) throws MalformedURLException {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openTheBaseURL() {
        driver.get(baseURL.toString());
    }

    public String getTitle() {
        return title;
    }

    public Boolean isUILanguageEnglish() {
        driver.get(googleURL.toString());
        if ("Sign in".equalsIgnoreCase(signInButton.getText())) {
            return true;
        }
        else return false;
    }

    public void switchLanguageOfGoogleUIToEnglish() {
        if (offerToUseGoogleCOM.isDisplayed()) {
            offerToUseGoogleCOM.click();
        }
        driver.get(googleLanguageSettings.toString());
        englishLanguageSettingsItem.click();
        submitLanguageSettingsButton.click();
        driver.switchTo().alert().accept();
    }

}