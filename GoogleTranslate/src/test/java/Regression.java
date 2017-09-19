import com.google.translate.PageObjects.TranslationFromEnglish;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.*;

public class Regression {

    WebDriver driver;
    TranslationFromEnglish englishTranslationPage;

    public Regression() throws MalformedURLException {
    }

    @BeforeClass
    public void preparationOfTheTestSuiteToRun() throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver2.32.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        englishTranslationPage = new TranslationFromEnglish(driver);
    }

    @BeforeClass(dependsOnMethods = {"preparationOfTheTestSuiteToRun"}, enabled = true)
    public void bringingUIToTheReadyState() {
        if (!englishTranslationPage.isUILanguageEnglish()) {
            englishTranslationPage.switchLanguageOfGoogleUIToEnglish();
        }
    }

    @AfterClass
    public void finish() {
        driver.quit();
    }

    @Test
    public void openingTheURLOfBeginPage() {
        englishTranslationPage.openTheBaseURL();
        englishTranslationPage.openThePage();
        assertEquals(driver.getTitle(), englishTranslationPage.getTitle(), "Titles are not the same.");
    }

    @Test
    public void clickingOnTheLogoForOpeningCleanTranslator() {
        englishTranslationPage.openThePage();
        englishTranslationPage.enterATextForTranslation("Some text");
        assertTrue(englishTranslationPage.isThereSomethingInTheResultBox(), "Expected that text will displayed.");
        englishTranslationPage.clickOnTheAppLogo();
        assertFalse(englishTranslationPage.isThereSomethingInTheResultBox(), "Expected that text will absent.");
    }

}
