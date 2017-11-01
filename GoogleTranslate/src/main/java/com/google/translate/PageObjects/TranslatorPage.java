package com.google.translate.PageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.net.MalformedURLException;
import java.util.List;

public class TranslatorPage extends BasePage {

    public final Footer footer = new Footer(driver);

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

    @FindBy(xpath = ".//*[@id='gt-submit']")
    private WebElement translateButton;

    @FindBy(xpath = ".//div[@id='gt-src-cc-ctr']")
    private WebElement characterCounter;

    @FindBy(xpath = ".//*[@id='t-new-user']")
    private WebElement translateCommunity;

    @FindBy(xpath = ".//div[@id='gt-pb-sw1']")
    private WebElement phrasebook;

    @FindBy(xpath = ".//a[@id='gt-otf-switch']")
    private WebElement instantTranslation;

    @FindBy(xpath = ".//*[@id='gt-ft-res']")
    private WebElement footerPanel;

    //@FindBy(xpath = ".//*[@id='gt-text-c']")//gt-text-c // gt-c
    //private WebElement pageContent;

    @FindBy(xpath = ".//a[@aria-label='Turn on Virtual Keyboard']")
    private WebElement virtualKeyboardSwitcher;

    @FindBy(xpath = ".//div[@id='kbd']")
    private WebElement virtualKeyboard;

    @FindBy(xpath = "//*[@id='kbd']/div[1]/div[2]/div/div")
    private WebElement virtualKeyboardCloseButton;

    @FindBy(xpath = "//*[@id='kbd']/div/div/button")
    private List<WebElement> buttonsOfVirtualKeyboard;

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
        textField.sendKeys(text);
    }

    public void clickTranslateButton() {
        translateButton.click();
    }

    public Boolean isThereSomethingInTheResultBox() {
        try {
            return resultBox.isDisplayed();
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

    public String changePrePreparedLanguageOnSomeoneElseFromTheList(Area area) {
        String selectedLanguage = "";
        List<WebElement> prePreparedLanguages;
        int flag;
        switch (area) {
            case SOURCE:
                prePreparedLanguages = prePreparedSourceLanguages;
                break;
            case TARGET:
                prePreparedLanguages = prePreparedTargetLanguages;
                break;
            default:
                prePreparedLanguages = prePreparedSourceLanguages;
                break;
        }

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

    public void openVirtualKeyboard() {
        virtualKeyboardSwitcher.click();
    }

    public Boolean isVirtualKeyboardAccessible() {
        try {
            return virtualKeyboardSwitcher.isDisplayed();
        }
        catch (NoSuchElementException e) {
            return false;
        }

    }

    public Boolean isVirtualKeyboardDisplayed() {
        try {
            return virtualKeyboard.isDisplayed();
        }
        catch (NoSuchElementException e) {
            return false;
        }

    }

    private void hitKeyOnVirtualKeyboard(char ch) {
        switch (ch) {
            case 'a':
            case 'A':
                buttonsOfVirtualKeyboard.get(29).click();
                break;
            case 'b':
            case 'B':
                buttonsOfVirtualKeyboard.get(46).click();
                break;
            case 'c':
            case 'C':
                buttonsOfVirtualKeyboard.get(44).click();
                break;
            case 'd':
            case 'D':
                buttonsOfVirtualKeyboard.get(31).click();
                break;
            case 'e':
            case 'E':
                buttonsOfVirtualKeyboard.get(17).click();
                break;
            case 'f':
            case 'F':
                buttonsOfVirtualKeyboard.get(32).click();
                break;
            case 'g':
            case 'G':
                buttonsOfVirtualKeyboard.get(33).click();
                break;
            case 'h':
            case 'H':
                buttonsOfVirtualKeyboard.get(34).click();
                break;
            case 'i':
            case 'I':
                buttonsOfVirtualKeyboard.get(22).click();
                break;
            case 'j':
            case 'J':
                buttonsOfVirtualKeyboard.get(35).click();
                break;
            case 'k':
            case 'K':
                buttonsOfVirtualKeyboard.get(36).click();
                break;
            case 'l':
            case 'L':
                buttonsOfVirtualKeyboard.get(37).click();
                break;
            case 'm':
            case 'M':
                buttonsOfVirtualKeyboard.get(48).click();
                break;
            case 'n':
            case 'N':
                buttonsOfVirtualKeyboard.get(47).click();
                break;
            case 'o':
            case 'O':
                buttonsOfVirtualKeyboard.get(23).click();
                break;
            case 'p':
            case 'P':
                buttonsOfVirtualKeyboard.get(24).click();
                break;
            case 'q':
            case 'Q':
                buttonsOfVirtualKeyboard.get(15).click();
                break;
            case 'r':
            case 'R':
                buttonsOfVirtualKeyboard.get(18).click();
                break;
            case 's':
            case 'S':
                buttonsOfVirtualKeyboard.get(30).click();
                break;
            case 't':
            case 'T':
                buttonsOfVirtualKeyboard.get(19).click();
                break;
            case 'u':
            case 'U':
                buttonsOfVirtualKeyboard.get(21).click();
                break;
            case 'v':
            case 'V':
                buttonsOfVirtualKeyboard.get(45).click();
                break;
            case 'w':
            case 'W':
                buttonsOfVirtualKeyboard.get(16).click();
                break;
            case 'x':
            case 'X':
                buttonsOfVirtualKeyboard.get(43).click();
                break;
            case 'y':
            case 'Y':
                buttonsOfVirtualKeyboard.get(20).click();
                break;
            case 'z':
            case 'Z':
                buttonsOfVirtualKeyboard.get(42).click();
                break;
            case ' ':
                buttonsOfVirtualKeyboard.get(54).click();
                break;
            case '.':
                buttonsOfVirtualKeyboard.get(50).click();
                break;
            case ',':
                buttonsOfVirtualKeyboard.get(49).click();
                break;
            case '-':
                buttonsOfVirtualKeyboard.get(11).click();
                break;
            case '1':
                buttonsOfVirtualKeyboard.get(1).click();
                break;
            case '2':
                buttonsOfVirtualKeyboard.get(2).click();
                break;
            case '3':
                buttonsOfVirtualKeyboard.get(3).click();
                break;
            case '4':
                buttonsOfVirtualKeyboard.get(4).click();
                break;
            case '5':
                buttonsOfVirtualKeyboard.get(5).click();
                break;
            case '6':
                buttonsOfVirtualKeyboard.get(6).click();
                break;
            case '7':
                buttonsOfVirtualKeyboard.get(7).click();
                break;
            case '8':
                buttonsOfVirtualKeyboard.get(8).click();
                break;
            case '9':
                buttonsOfVirtualKeyboard.get(9).click();
                break;
            case '0':
                buttonsOfVirtualKeyboard.get(10).click();
                break;
        }
    }

    public void typeOnVirtualKeyboard(String text) {
        char[] chars = text.toCharArray();
        for (char ch : chars) {
            if (Character.isUpperCase(ch)) {
                buttonsOfVirtualKeyboard.get(41).click();
                hitKeyOnVirtualKeyboard(ch);
            }
            else hitKeyOnVirtualKeyboard(ch);
        }
    }

    public void closeVirtualKeyboard() {
        virtualKeyboardCloseButton.click();
    }

}
