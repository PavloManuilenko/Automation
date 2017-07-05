import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StartPage {
   private WebDriver driver;
   private By searchField = new By.ByXPath(".//input[@name = 'q']");
   private By resultStats = new By.ByXPath(".//div[@id = 'resultStats']");

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

    public void waitOfElementPresence(By locator, int timeOutInSeconds) {
        new WebDriverWait(driver, timeOutInSeconds)
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void search(String text) {
        waitOfElementPresence(searchField, 5);
        driver.findElement(searchField).click();
        driver.findElement(searchField).sendKeys(text);
        driver.findElement(searchField).submit();
        waitOfElementPresence(resultStats, 5);
    }

    public boolean isResultStatsAppeared() {
        waitOfElementPresence(resultStats, 5);
        return driver.findElement(resultStats).isDisplayed();
    }

    public WebElement getWebElementFromCurrentPage(String locatorCSS) {
        return (WebElement)((JavascriptExecutor)driver).executeScript("return document.querySelector('" + locatorCSS +"')");
    }
}
