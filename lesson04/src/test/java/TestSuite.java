import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    public void verifyingTitleOfStartPageViaJS() {
        startPage.open();
        assertEquals("Wrong Title!", "Google", startPage.getCurrentTitleViaJS());
    }

    @Test
    public void comparingTextFromJSAlertAndExpected() {
        String text = "TEST";
        startPage.open();
        startPage.executeJS("alert('TEST');");
        String actual = startPage.getAlertFromThePageWithTimeout(5).getText();
        assertEquals("The text is different!", text, actual);
    }

    @Test
    public void testingOfSearchingOnce() {
        startPage.open();
        startPage.search("QA / QC");
        assertTrue("There is no result of searching", startPage.isResultStatsAppeared());
    }

    @Test
    public void testingOfGoingBackAndForwardAfterSearching() {
        startPage.open();
        startPage.search("QA / QC");
        startPage.goBack();
        startPage.goForward();
        assertTrue("There is no result of searching", startPage.isResultStatsAppeared());
    }

    @Test
    public void testingOfOpenRequiredSearchResultViaJS() {
        WebElement requiredSearchResult;
        startPage.open();
        startPage.search("ISTQB");
        requiredSearchResult = startPage.getWebElementFromCurrentPage("a[href = \"http://www.istqb.org/\"]");
        requiredSearchResult.click();
        assertEquals("Wrong Title!", "Certifying Software Testers Worldwide - ISTQB® International Software Testing Qualifications Board", startPage.getCurrentTitleViaJS());
    }

}
