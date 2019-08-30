package com.google.play.pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.MalformedURLException;
import java.net.URL;

public class BasePage {

    WebDriver driver;
    private URL googlePlayMusicURL = new URL("https", "play.google.com/music/listen", "");
    private String titleOfHomePage = new String("Home - Google Play Music");
    //private String titleOfLoginPage = new String("Google Play");

    @FindBy(xpath = ".//a[@id='gb_70']")
    private WebElement signInButton;

    @FindBy(xpath = ".//input[@id='identifierId']")
    private WebElement inputEmail;

    @FindBy(name = "password") //xpath = ".//input[@name='password']"
    private WebElement inputPass;

    @FindBy(id = "left-nav-open-button") //xpath = ".//input[@id='left-nav-open-button']"
    private WebElement leftNavOpenButton;

    @FindBy(xpath = ".//a[@data-type='mylibrary']")
    private WebElement myLibrary;

    @FindBy(xpath = ".//div[@class='material-card-grid cluster']") // ".//h2[@class='section-header']"
    private WebElement myPlaylists;

    public BasePage(WebDriver driver) throws MalformedURLException {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static WebDriver getTheDriver(int implicitlyWaitInSeconds) {
        WebDriver driver = null;
        System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver76.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        return driver;
    }

    protected String getTitleOfHomePage() {
        return titleOfHomePage;
    }

    private void openGooglePlayMusic() {
        driver.navigate().to(googlePlayMusicURL);
    }

    protected String login(String login, String pass) {
        openGooglePlayMusic();
        signInButton.click();
        inputEmail.click();
        inputEmail.sendKeys(login);
        inputEmail.sendKeys(Keys.ENTER);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        inputPass = wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
        inputPass.sendKeys(pass);
        inputPass.sendKeys(Keys.ENTER);
        inputPass = wait.until(ExpectedConditions.elementToBeClickable(By.className("music-logo")));
        return driver.getTitle();
    }

    protected void openLeftNavMenu() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        inputPass = wait.until(ExpectedConditions.elementToBeClickable(By.id("left-nav-open-button")));
        leftNavOpenButton.click();
    }

    protected void openMyLibrary() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        //sinputPass = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Music library")));
        myLibrary.click();
    }

    BasePage() throws MalformedURLException {
    }
}
