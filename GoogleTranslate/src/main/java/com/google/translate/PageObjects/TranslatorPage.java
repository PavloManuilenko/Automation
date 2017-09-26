package com.google.translate.PageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.net.MalformedURLException;
import java.util.List;

public class TranslatorPage extends BasePage {

    @FindBy(xpath = ".//*[@id='source']")
    private WebElement textField;

    @FindBy(xpath = ".//span[@id='result_box']/span")
    private WebElement resultBox;

    @FindBy(xpath = ".//*[@id='gt-sl-sugg']/div")
    private List<WebElement> prePreparedSourceLanguages;

    @FindBy(xpath = ".//*[@id='gt-tl-sugg']/div")
    private List<WebElement> prePreparedTargetLanguages;

    @FindBy(xpath = ".//*[@id='gt-sl-gms']")
    private WebElement theListWithAllSupportedSourceLanguages;

    @FindBy(xpath = ".//*[@id='gt-tl-gms']")
    private WebElement theListWithAllSupportedTargetLanguages;

    @FindBy(xpath = ".//div[@class='goog-menuitem-checkbox']/..")
    private List<WebElement> allSupportedSourceOrTargetLanguages;

    @FindBy(xpath = ".//*[@id='gt-swap']")
    private WebElement switchButton;

    @FindBy(xpath = ".//div[@id='gt-src-cc-ctr']")
    private WebElement characterCounter;

    public TranslatorPage(WebDriver driver) throws MalformedURLException {
        super(driver);
    }

    public void openThePage() {
        driver.navigate().to(translatorURL);
    }

    public void enterATextForTranslation(String text) {
        textField.sendKeys(text);
    }

    public Boolean isThereSomethingInTheResultBox() {
        try {
            resultBox.isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public String getTextFromTheResultBox() {
        return resultBox.getText();
    }

    public void choosePrePreparedLanguage(String sourceOrTarget) {
        if (sourceOrTarget.equalsIgnoreCase("source")) prePreparedSourceLanguages.get(0).click();
        else if (sourceOrTarget.equalsIgnoreCase("target")) prePreparedTargetLanguages.get(1).click();
    }

    public Boolean stateOfPrePreparedLanguage(String sourceOrTarget, int numOfLang) {
        if (sourceOrTarget.equalsIgnoreCase("source")) {
            return Boolean.valueOf(prePreparedSourceLanguages.get(numOfLang).getAttribute("aria-pressed"));
        } else if (sourceOrTarget.equalsIgnoreCase("target")) {
            return Boolean.valueOf(prePreparedTargetLanguages.get(numOfLang).getAttribute("aria-pressed"));
        } else return false;
    }

    public void openTheListWithAllSupportedLanguages(String sourceOrTarget) {
        if (sourceOrTarget.equalsIgnoreCase("source")) theListWithAllSupportedSourceLanguages.click();
        else if (sourceOrTarget.equalsIgnoreCase("target")) theListWithAllSupportedTargetLanguages.click();
    }

    public int countOfAllSupportedSourceOrTargetLanguages() {
        return allSupportedSourceOrTargetLanguages.size();
    }

    public String changePrePreparedLanguageOnSomeoneElseFromTheList(String sourceOrTarget) {
        String selectedLanguage = "";
        List<WebElement> prePreparedLanguages;
        int flag;

        if (sourceOrTarget.equalsIgnoreCase("source")) {
            prePreparedLanguages = prePreparedSourceLanguages;
        } else prePreparedLanguages = prePreparedTargetLanguages;

        for (int i = 1; i < allSupportedSourceOrTargetLanguages.size(); i++) { //i starts from 1, because on 0-index 'Detect language' item.
            flag = 0;
            for (int j = 0; j < 3; j++) { //3 pre prepared languages
                if (!prePreparedLanguages.get(j).getText().equals(allSupportedSourceOrTargetLanguages.get(i)
                        .getText())) flag++;
            }
            if (flag == 3) {
                selectedLanguage = allSupportedSourceOrTargetLanguages.get(i).getText();
                allSupportedSourceOrTargetLanguages.get(i).click();
                break;
            }
        }
        return selectedLanguage;
    }

    public String whichLanguageIsTurnOn(String sourceOrTarget) {
        if (sourceOrTarget.equalsIgnoreCase("source")) {
            for (int i = 0; i < 3; i++) {
                if (stateOfPrePreparedLanguage("source", i)) {
                    return prePreparedSourceLanguages.get(i).getText();
                }
            }
        } else if (sourceOrTarget.equalsIgnoreCase("target")) {
            for (int i = 0; i < 3; i++) {
                if (stateOfPrePreparedLanguage("target", i)) {
                    return prePreparedTargetLanguages.get(i).getText();
                }
            }
        }
        return "Cannot recognize which language is turn on";
    }

    public String translate(String translateFrom, String translateTo, String source, String expected) {
        driver.get(translatorURL + "/#" + translateFrom + "/" + translateTo);
        enterATextForTranslation(source);
        return expected;
    }

    public void switchTargetAndSourceLanguages() {
        switchButton.click();
    }

    public int characterCounterState() {
        String tempStr = characterCounter.getText();
        int lastIndex = tempStr.indexOf('/');
        return Integer.parseInt(tempStr.substring(0, lastIndex));
    }
}
