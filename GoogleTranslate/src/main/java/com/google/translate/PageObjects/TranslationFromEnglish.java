package com.google.translate.PageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class TranslationFromEnglish extends BasePage implements TranslationPage{

    private URL url = new URL("https", "translate.google.com", 443, "/#en");

    @FindBy(xpath = ".//*[@id='source']")
    private WebElement textField;

    @FindBy(xpath = ".//span[@id='result_box']/span")
    private WebElement resultBox;

    @FindBy(xpath = ".//*[@id='gt-sl-sugg']/div[1]")
    private WebElement firstPrePreparedSourceLanguage;

    @FindBy(xpath = ".//*[@id='gt-tl-sugg']/div[2]")
    private WebElement secondPrePreparedTargetLanguage;

    @FindBy(xpath = ".//*[@id='gt-sl-gms']")
    private WebElement theListWithAllSupportedSourceLanguages;

    @FindBy(xpath = ".//*[@id='gt-tl-gms']")
    private WebElement theListWithAllSupportedTargetLanguages;

    @FindBy(xpath = ".//div[@class='goog-menuitem-checkbox']/..")
    private List<WebElement> allSupportedSourceOrTargetLanguages;

    public TranslationFromEnglish(WebDriver driver) throws MalformedURLException {
        super(driver);
    }

    public void openThePage() {
        driver.navigate().to(url);
    }

    public void enterATextForTranslation(String text) {
        textField.sendKeys(text);
    }

    public Boolean isThereSomethingInTheResultBox() {
        try {
            resultBox.isDisplayed();
            return true;
        }
        catch (NoSuchElementException e) {
            return false;
        }
    }

    public String getTextFromTheResultBox() {
        return resultBox.getText();
    }

    public void choosePrePreparedLanguage(String sourceOrTarget) {
        if (sourceOrTarget.equalsIgnoreCase("source")) firstPrePreparedSourceLanguage.click();
        else if (sourceOrTarget.equalsIgnoreCase("target")) secondPrePreparedTargetLanguage.click();
    }

    public Boolean stateOfPrePreparedLanguage(String sourceOrTarget) {
        if (sourceOrTarget.equalsIgnoreCase("source")) {
            return Boolean.valueOf(firstPrePreparedSourceLanguage.getAttribute("aria-pressed"));
        }
        else if (sourceOrTarget.equalsIgnoreCase("target")) {
            return Boolean.valueOf(secondPrePreparedTargetLanguage.getAttribute("aria-pressed"));
        }
        else return false;
    }

    public void openTheListWithAllSupportedSourceOrTargetLanguages(String sourceOrTarget) {
        if (sourceOrTarget.equalsIgnoreCase("source")) theListWithAllSupportedSourceLanguages.click();
        else if (sourceOrTarget.equalsIgnoreCase("target")) theListWithAllSupportedTargetLanguages.click();
    }

    public int countOfAllSupportedSourceOrTargetLanguages() {
        return allSupportedSourceOrTargetLanguages.size();
        }

    public void chooseTheLanguageFromSourceList(String language){
        for (WebElement we : allSupportedSourceOrTargetLanguages) {
            System.out.println("getText: " + we.getText());

        }
    }

}
