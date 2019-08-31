package com.google.play.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MusicLibrary extends BasePage{

    WebDriver driver;
    private String title = new String("Playlists - Google Play Music");

    @FindBy(xpath = "(.//div[@class='lane-content'])[3]//a[@class='title fade-out tooltip']")
    private List<WebElement> myPlayLists;

    @FindBy(xpath = ".//div[@class='material-card-grid cluster']") // ".//h2[@class='section-header']"
    private WebElement myPlaylists;

    public String getPlayListsTitle() {
        return title;
    }

    public MusicLibrary(WebDriver driver) throws MalformedURLException {
        super(driver);
    }
    public String signIn(String login, String pass) {
        return super.login(login, pass);
    }

    public String getTitleOfHomePage() {
        return super.getTitleOfHomePage();
    }

    public String goToAllPlayLists() {
        openLeftNavMenu();
        openMyLibrary();
        return getPlayListsTitle();
    }

    public void fillCollectionOfAllPlayLists() {

    }





    public MusicLibrary() throws MalformedURLException {
    }
}
