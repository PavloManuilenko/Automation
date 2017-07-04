import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
    public void openGoogle() {
        startPage.open();

    }

    @Test
    public void testOfJS() throws InterruptedException {
        startPage.open();
        String text = "TEST";
        startPage.executeJS(driver, "alert('TEST');");
        String actual = startPage.getAlertFromPageAfterTimeout(5).getText();
        if (actual.equals(text)){
            System.out.println("OK");
        } else {
            throw new RuntimeException("value " + actual);
        }
    }

    @Test
    public void testOfJS2() throws InterruptedException {
        startPage.open();
        String res = (String) ((JavascriptExecutor)driver).executeScript( "return document.title;");
        System.out.println(res);
    }



    @Test
    public void testOfSearching() {
        startPage.open();
        startPage.search("QA / QC");
    }

    @Test
    public void testOfGoinbGackAndForwardAfterSearching() {
        startPage.open();
        startPage.search("QA / QC");
        startPage.goBack();
        startPage.goForward();
    }
}
