import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestSuite {
    WebDriver driver;
    private StartPage startPage;

    @Before
    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        startPage = new StartPage(driver);
    }

    @After
    public void driverQuit() {
        driver.quit();

    }

    @Test
    public void openGoogleStartPage() {
        startPage.open();
        assertEquals("Wrong Title!", "Google", driver.getTitle());
    }

    @Test
    public void verifyingTitleOfStartPageViaJS() {
        startPage.open();
        String title = (String) ((JavascriptExecutor)driver).executeScript( "return document.title;");
        assertEquals("Wrong Title!", "Google", title);
    }

    @Test
    public void comparingTextFromJSAlertAndExpected() {
        String text = "TEST";
        startPage.open();
        startPage.executeJS(driver, "alert('TEST');");
        String actual = startPage.getAlertFromThePageWithTimeout(5).getText();
        assertEquals("The text is different!", text, actual);
    }

    @Test
    public void testOfSearchingOnce() {
        startPage.open();
        startPage.search("QA / QC");
        assertTrue("There is no result of searching", startPage.isResultStatsAppeared());
    }

    @Test
    public void testOfGoingBackAndForwardAfterSearching() {
        startPage.open();
        startPage.search("QA / QC");
        startPage.goBack();
        startPage.goForward();
        assertTrue("There is no result of searching", startPage.isResultStatsAppeared());
    }
}
