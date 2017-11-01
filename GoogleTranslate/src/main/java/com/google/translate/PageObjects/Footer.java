package com.google.translate.PageObjects;
//This is class for implementation Page Element abstraction level + composition

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Footer {

    WebDriver driver;

    @FindBy(xpath = ".//div[@id='gt-ft-res']/*[@id='ft-l']/a")
    private List<WebElement> leftAreaOfFooter;

    @FindBy(xpath = ".//div[@id='gt-ft-res']/*[@id='ft-r']/a")
    private List<WebElement> rightAreaOfFooter;

    @FindBy(xpath = ".//*[@id='google-feedback-wizard']")
    private WebElement iframeFeedback1;
    //-@FindBy(xpath = ".//*[@id='xh-bar']")
    //-@FindBy(xpath = ".//*[@id='google-feedback-render-frame']")
    //-@FindBy(xpath = ".//*[@id='I0_1509471755617']")
    //-@FindBy(xpath = ".//*[@id='snapshot-frame']")
    //-@FindBy(xpath = ".//*[@id='google-feedback-submit-frame']")
    private WebElement iframeFeedback;

    @FindBy(xpath = "/html/body/div[2]/div/div/uf-describe-page/form/header/h1")
    private WebElement caption;

    public Footer(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openAboutGoogleTranslatePage() {
        leftAreaOfFooter.get(0).click();
    }

    public void openCommunityPage() {
        leftAreaOfFooter.get(1).click();
    }

    public void openThePageWithTheMobileApp() {
        leftAreaOfFooter.get(2).click();
    }

    public void openAboutGooglePage() {
        rightAreaOfFooter.get(0).click();
    }

    public void openPrivacyAndTermsPage() {
        rightAreaOfFooter.get(1).click();
    }

    public void openHelpPage() {
        rightAreaOfFooter.get(2).click();
    }

    public void openFeedbackPopUp() {
        rightAreaOfFooter.get(3).click();
        System.out.println(caption.getText());
    }

    public String getFeedbackCaptionText() {
        driver.switchTo().frame(iframeFeedback);
        return caption.getText();
    }
}
