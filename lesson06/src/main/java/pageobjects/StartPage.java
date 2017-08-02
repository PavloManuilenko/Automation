package pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StartPage {
   private WebDriver driver;

   @FindBy(xpath = ".//input[@name = 'q']")
   private WebElement searchField;

   @FindBy(xpath = ".//div[@id = 'resultStats']")
   private WebElement resultStats;

   @FindBy(xpath = ".//*[@id='navcnt']")
   private WebElement paginationBlock;

    private static final String url = "https://www.google.com.ua";

    public StartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void init(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get(url);

    }

    public void goBack() {
        driver.navigate().back();
    }

    public void goForward() {
        driver.navigate().forward();
    }

    public void executeJS(String script) {
        ((JavascriptExecutor)driver).executeScript(script);
    }

    public String getCurrentTitleViaJS() {
        return (String)((JavascriptExecutor)driver).executeScript( "return document.title;");
    }

    public Alert getAlertFromThePageWithTimeout(int timeOutInSeconds) {
        return new WebDriverWait(driver, timeOutInSeconds)
                .until(ExpectedConditions.alertIsPresent());
    }

    public void waitOfElementPresenceByLocator(WebElement element, int timeOutInSeconds) {
        new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForDocumentReady(int timeOutInSeconds) {
        new WebDriverWait(driver, timeOutInSeconds).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                JavascriptExecutor js = (JavascriptExecutor) d;
                return js.executeScript("return document.readyState").equals("complete");
            }
        });
    }

    public void waitOfPaginationBlockOnSearchResultPage(int timeOutInSeconds) {
        new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.visibilityOf(paginationBlock));
    }

    public void search(String text) {
        waitOfElementPresenceByLocator(searchField, 5);
        searchField.click();
        searchField.sendKeys(text);
        searchField.submit();
        waitOfElementPresenceByLocator(resultStats, 5);
    }

    public boolean isResultStatsAppeared() {
        waitOfElementPresenceByLocator(resultStats, 5);
        return resultStats.isDisplayed();
    }

    public WebElement getWebElementFromCurrentPageViaCSSLocator(String locatorCSS) {
        return (WebElement)((JavascriptExecutor)driver).executeScript("return document.querySelector('" + locatorCSS +"')");
    }

    public void actionSearch(String text) {
        Actions builder = new Actions(driver);
        WebElement searchField = getWebElementFromCurrentPageViaCSSLocator("input[name=q]");
        builder.click(searchField).sendKeys(text).sendKeys(Keys.ENTER).build().perform();
    }

    public void actionOpenFirstSearchResult() {
        Actions builder = new Actions(driver);
        WebElement firstSearchResult = getWebElementFromCurrentPageViaCSSLocator(".r>a");
        builder.click(firstSearchResult).build().perform();
    }
}
