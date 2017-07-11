import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StartPage {
   private WebDriver driver;
   private By searchField = new By.ByXPath(".//input[@name = 'q']");
   private By resultStats = new By.ByXPath(".//div[@id = 'resultStats']");
   private By firstRresult = new By.ByXPath("(//h3)[1]/a");
   private By pagination = new By.ByXPath(".//*[@id='navcnt']");

    private static final String url = "https://www.google.com.ua";

    public StartPage(WebDriver driver) {
        this.driver = driver;
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

    public void waitOfElementPresenceByLocator(By locator, int timeOutInSeconds) {
        new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitForDocumentReady(int timeOutInSeconds) {
        new WebDriverWait(driver, timeOutInSeconds).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                JavascriptExecutor js = (JavascriptExecutor) d;
                return js.executeScript("return document.readyState").equals("complete");

            }
        });
    }

    public void waitOfPaginationOnSearchResult(int timeOutInSeconds) {
        new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.presenceOfElementLocated(pagination));
    }

    public void search(String text) {
        waitOfElementPresenceByLocator(searchField, 5);
        driver.findElement(searchField).click();
        driver.findElement(searchField).sendKeys(text);
        driver.findElement(searchField).submit();
        waitOfElementPresenceByLocator(resultStats, 5);
    }

    public boolean isResultStatsAppeared() {
        waitOfElementPresenceByLocator(resultStats, 5);
        return driver.findElement(resultStats).isDisplayed();
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
        //waitForDocumentReady(5);
        waitOfPaginationOnSearchResult(5);
        WebElement firstSearchResult = getWebElementFromCurrentPageViaCSSLocator(".r>a");
        builder.click(firstSearchResult).build().perform();
    }
}
