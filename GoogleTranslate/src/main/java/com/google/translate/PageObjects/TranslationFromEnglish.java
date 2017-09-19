package com.google.translate.PageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.net.MalformedURLException;
import java.net.URL;

public class TranslationFromEnglish extends BasePage implements TranslationPage{

    private URL url = new URL("https", "translate.google.com", 443, "/#en");

    @FindBy(xpath = ".//*[@id='source']")
    private WebElement textField;

    @FindBy(xpath = ".//span[@id='result_box']/span")
    private WebElement resultBox;

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
}
