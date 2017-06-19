import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DouTest {

    private static final String urlOfDOU = "https://dou.ua/";
    private static final String titleOfHomePage = "Сообщество программистов | DOU";
    private static final By topPanelForum = new By.ByXPath(".//a[text() = 'Форум']");
    private static final By topPanelLenta = new By.ByXPath(".//a[text() = 'Лента']");
    private static final By firstArticleInTheForum = new By.ByXPath(".//article[1]/h2/a");
    private static final By nameOfAnArticle = new By.ByXPath(".//article[@class = 'b-typo']/h1");
    private static final By eventBlock = new By.ByXPath(".//div[@class = 'b-adv-events']");
    private static final By eventFromBlock = new By.ByXPath(".//div[@class = 'adv-event-block']");

    private static WebDriver driver;

    @Before
    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\Geckodriver\\geckodriver.exe");
        driver = new FirefoxDriver();
    }

    @After
    public void driverQuit() {
        driver.quit();
    }

    @Test
    public void gettingTheNameOfFirstArticleInForum() {
        driver.get(urlOfDOU);
        driver.findElement(topPanelForum).click();

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(firstArticleInTheForum));

        driver.findElement(firstArticleInTheForum).click();

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(nameOfAnArticle));

        System.out.println("The name Of the Article is: " + driver.findElement(nameOfAnArticle).getText());
    }

    @Test
    public void countingEventsInTheBlock() {
        List<WebElement> arrOfEvents = new ArrayList<WebElement>();
        driver.get(urlOfDOU);
        driver.findElement(topPanelLenta).click();

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(eventBlock));

        arrOfEvents = driver.findElements(eventFromBlock);

        assertEquals(10, arrOfEvents.size());
    }

    @Test
    public void getEventsTextFromTheBlock() {
        List<WebElement> arrOfEvents;
        driver.get(urlOfDOU);
        driver.findElement(topPanelLenta).click();

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(eventBlock));

        arrOfEvents = driver.findElements(eventFromBlock);

        for (WebElement elm : arrOfEvents) {
            System.out.println(elm.getText());
        }
    }

    @Test
    public void getEventsDateAndCityFromTheBlock() {
        List<WebElement> arrOfEvents;
        driver.get(urlOfDOU);
        driver.findElement(topPanelLenta).click();

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(eventBlock));

        arrOfEvents = driver.findElements(eventFromBlock);

        for (WebElement elm : arrOfEvents) {
            System.out.println(elm.findElement(By.className("date")).getText());
        }
    }

    @Test
    public void validatingOfEventDates() {
        List<WebElement> arrOfEvents;
        ArrayList<String> arrOfSourceDate = new ArrayList<String>();
        ArrayList<String> arrOfDateFromWEB = new ArrayList<String>();
        driver.get(urlOfDOU);
        driver.findElement(topPanelLenta).click();

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(eventBlock));

        arrOfEvents = driver.findElements(eventFromBlock);

        arrOfSourceDate.add("17 июня");
        arrOfSourceDate.add("17 — 18 июня");
        arrOfSourceDate.add("17 июня");
        arrOfSourceDate.add("20 июня");
        arrOfSourceDate.add("22 — 23 июня");
        arrOfSourceDate.add("22 июня");
        arrOfSourceDate.add("23 — 25 июня");
        arrOfSourceDate.add("24 июня");
        arrOfSourceDate.add("26 июня");
        arrOfSourceDate.add("19 — 20 августа");

        for (int i = 0; i < arrOfEvents.size(); i++) {
            arrOfDateFromWEB.add(arrOfEvents.get(i).findElement(By.className("date")).getText());
            arrOfDateFromWEB.set(i, pickTheDate(arrOfDateFromWEB.get(i), ','));
            System.out.println(arrOfDateFromWEB.get(i));
        }

        assertEquals(arrOfSourceDate, arrOfDateFromWEB);
    }

    private String pickTheDate (String sourceStr, char lastChar) {
        int lastIndex = sourceStr.indexOf(lastChar);
        return sourceStr.substring(0, lastIndex);
    }
}
