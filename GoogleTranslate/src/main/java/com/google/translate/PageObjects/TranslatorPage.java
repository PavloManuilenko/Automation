package com.google.translate.PageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    @FindBy(xpath = ".//*[@id='t-new-user']")
    private WebElement translateCommunity;

    @FindBy(xpath = ".//div[@id='gt-pb-sw1']")
    private WebElement phrasebook;

    @FindBy(xpath = ".//a[@id='gt-otf-switch']")
    private WebElement instantTranslation;

    @FindBy(xpath = ".//*[@id='gt-ft-res']")
    private WebElement footer;

    @FindBy(xpath = ".//*[@id='gt-text-c']")//gt-text-c // gt-c
    private WebElement pageContent;

    public TranslatorPage(WebDriver driver) throws MalformedURLException {
        super(driver);
    }

    public enum Area {SOURCE, TARGET}

    public void openThePage() {
        driver.navigate().to(translatorURL);
    }

    public void openThePage(String urlEnds) {
        driver.navigate().to(translatorURL.toString() + urlEnds);
    }

    public void enterATextForTranslation(String text) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement element = wait.until(ExpectedConditions.visibilityOf(textField));
        //textField.sendKeys(text);
        element.sendKeys(text);
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

    public String getTextOFTheLanguageButton(int numOfLang) {
        return prePreparedSourceLanguages.get(numOfLang).getText();
    }

    public void choosePrePreparedLanguage(Area area) {
        switch (area) {
            case SOURCE:
                prePreparedSourceLanguages.get(0).click();
                break;
            case TARGET:
                prePreparedTargetLanguages.get(1).click();
                break;
        }
    }

    public void choosePrePreparedLanguage(Area area, int numOfLang) {
        switch (area) {
            case SOURCE:
                prePreparedSourceLanguages.get(numOfLang).click();
                break;
            case TARGET:
                prePreparedTargetLanguages.get(numOfLang).click();
                break;
        }
    }

    public Boolean stateOfPrePreparedLanguage(Area area, int numOfLang) {
        switch (area) {
            case SOURCE:
                return Boolean.valueOf(prePreparedSourceLanguages.get(numOfLang).getAttribute("aria-pressed"));
            case TARGET:
                return Boolean.valueOf(prePreparedTargetLanguages.get(numOfLang).getAttribute("aria-pressed"));
            default:
                return false;
        }
    }

    public Boolean isSwitchButtonEnabled() {
        return !Boolean.valueOf(switchButton.getAttribute("aria-disabled"));
    }

    public void openTheListWithAllSupportedLanguages(Area area) {
        switch (area) {
            case SOURCE:
                theListWithAllSupportedSourceLanguages.click();
                break;
            case TARGET:
                theListWithAllSupportedTargetLanguages.click();
                break;
        }
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

    public String whichLanguageIsTurnOn(Area area) {
        String lang = "Cannot recognize which language is turn on";
        switch (area) {
            case SOURCE:
                for (int i = 0; i < 3; i++) {
                    if (stateOfPrePreparedLanguage(area, i)) {
                        lang = prePreparedSourceLanguages.get(i).getText();
                    }
                }
                break;
            case TARGET:
                for (int i = 0; i < 3; i++) {
                    if (stateOfPrePreparedLanguage(area, i)) {
                        lang = prePreparedTargetLanguages.get(i).getText();
                    }
                }
                break;
        }
        return lang;
    }

    public String translate(String translateFrom, String translateTo, String source, String expected) {
        driver.get(translatorURL + "/#" + translateFrom + "/" + translateTo);
        enterATextForTranslation(source);
        return expected;
    }

    public void switchTargetAndSourceLanguages() {
        if (isSwitchButtonEnabled()) switchButton.click();
        else System.out.println("Switch button is disabled!");
    }

    public int characterCounterState() {
        String tempStr = characterCounter.getText();
        int lastIndex = tempStr.indexOf('/');
        return Integer.parseInt(tempStr.substring(0, lastIndex));
    }

    public void goToTranslateCommunity() {
        translateCommunity.click();
    }

    public void openPhrasebook() {
        phrasebook.click();
    }

    public Boolean isInstantTranslationEnabled() {
        return instantTranslation.getText().equalsIgnoreCase("Turn on instant translation");
    }

    public void instantTranslationModeSwitch() {
        instantTranslation.click();
    }

    public void scrollIntoViewOfCharacterCounter() {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("document.getElementById('gt-src-cc-ctr').scrollIntoView(true);");
    }

}
