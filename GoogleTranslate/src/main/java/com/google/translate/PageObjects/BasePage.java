package com.google.translate.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public abstract class BasePage {

    WebDriver driver;
    private URL googleURL = new URL("https", "google.com", 443, "");
    private URL googleLanguageSettings = new URL("https", "google.com", 443, "/preferences#languages");
    URL translatorURL = new URL("https", "translate.google.com", 443, "");
    private String title = new String("Google Translate");

    @FindBy(xpath = ".//a[@id='gb_70']")
    private WebElement signInButton;

    @FindBy(xpath = ".//div[@id='langten']/div/span")
    private WebElement englishLanguageSettingsItem;

    @FindBy(xpath = ".//*[@id='form-buttons']/div[1]")
    private WebElement submitLanguageSettingsButton;

    @FindBy(xpath = ".//a[contains(text(), 'Google.com')]")
    private WebElement offerToUseGoogleCOM;

    @FindBy(xpath = ".//a[@id='gt-appname']")
    private WebElement appLogo;

    public BasePage(WebDriver driver) throws MalformedURLException {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static WebDriver getTheDriver(int implicitlyWaitInSeconds) {
        WebDriver driver = null;
        String browser = "";

        try (BufferedReader reader = new BufferedReader(new FileReader("testng.xml"))) {
            String line;
            while (null != (line = reader.readLine())) {
                if (line.trim().startsWith("<parameter name=\"browser\"")) {
                    String parameter = line.trim();
                    browser = parameter.substring(33, parameter.length()-4);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (browser) {
            case "edge":
                System.setProperty("webdriver.edge.driver", "./src/main/resources/MicrosoftWebDriver.exe");
                driver = new EdgeDriver();
                driver.manage().window().maximize();
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver2.32.exe");
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                break;
            case "firefox":
            default:
                System.setProperty("webdriver.gecko.driver", "./src/main/resources/geckodriver0.19.exe");
                driver = new FirefoxDriver();
                break;
        }
        driver.manage().timeouts().implicitlyWait(implicitlyWaitInSeconds, TimeUnit.SECONDS);
        return driver;
    }

    public void openTheBaseURL() {
        driver.navigate().to(translatorURL);
    }

    public String getTitle() {
        return title;
    }

    public Boolean isUILanguageEnglish() {
        driver.navigate().to(googleURL);
        if ("Sign in".equalsIgnoreCase(signInButton.getText())) {
            return true;
        }
        else return false;
    }

    public void switchLanguageOfGoogleUIToEnglish() {
        if (offerToUseGoogleCOM.isDisplayed()) {
            offerToUseGoogleCOM.click();
        }
        driver.navigate().to(googleLanguageSettings);
        englishLanguageSettingsItem.click();
        submitLanguageSettingsButton.click();
        driver.switchTo().alert().accept();
    }

    public void clickOnTheAppLogo() {
        appLogo.click();
    }

    public void clickOnSignInButton() {
        signInButton.click();
    }
}