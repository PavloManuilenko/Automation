package com.google.translate.PageObjects;
//This is class for implementation Page Element abstraction level

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Footer {

    @FindBy(xpath = ".//div[@id='gt-ft-res']/*[@id='ft-l']/a")
    private List<WebElement> leftAreaOfFooter;

    @FindBy(xpath = ".//div[@id='gt-ft-res']/*[@id='ft-r']/a")
    private List<WebElement> rightAreaOfFooter;

    public Footer(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void openHelpPage() {

    }
    public void openFeedbackPopUp() {

    }
    public void openPrivacyAndTermsPage() {

    }
    public void openAboutGooglePage() {

    }
    public void openAboutGoogleTranslatePage() {

    }

    public void openCommunityPage() {

    }

    public void openThePageWithTheMobileApp() {

    }
}
