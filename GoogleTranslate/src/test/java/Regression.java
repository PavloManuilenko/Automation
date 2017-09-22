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
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        englishTranslationPage = new TranslationFromEnglish(driver);
    }

    @BeforeClass(dependsOnMethods = {"preparationOfTheTestSuiteToRun"}, enabled = true)
    public void bringingUIToTheReadyState() {
        if (!englishTranslationPage.isUILanguageEnglish()) {
            englishTranslationPage.switchLanguageOfGoogleUIToEnglish();
        }
    }

    @AfterClass
    public void finishRunningTheTestSuite() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

    @Test(enabled = false) //US #1
    public void openingTheURLOfBeginPage() {
        englishTranslationPage.openTheBaseURL();
        englishTranslationPage.openThePage();
        assertEquals(driver.getTitle(), englishTranslationPage.getTitle(), "Titles are not the same.");
    }

    @Test(enabled = false) //US #2
    public void clickingOnTheLogoForOpeningCleanTranslator() {
        englishTranslationPage.openThePage();
        englishTranslationPage.enterATextForTranslation("Some text");
        assertTrue(englishTranslationPage.isThereSomethingInTheResultBox(), "Expected that text will displayed.");
        englishTranslationPage.clickOnTheAppLogo();
        assertFalse(englishTranslationPage.isThereSomethingInTheResultBox(), "Expected that text will absent.");
    }

    @Test(enabled = false) //US #3
    public void choosingASourceLanguage() {
        englishTranslationPage.openThePage();
        assertFalse(englishTranslationPage.stateOfPrePreparedLanguage("source"),
                "The language has already pressed.");
        englishTranslationPage.choosePrePreparedLanguage("source");
        assertTrue(englishTranslationPage.stateOfPrePreparedLanguage("source"),
                "The language was not pressed.");
    }

    @Test(enabled = false) //US #4
    public void choosingATargetLanguage() {
        englishTranslationPage.openThePage();
        assertFalse(englishTranslationPage.stateOfPrePreparedLanguage("target"),
                "The language has already pressed.");
        englishTranslationPage.choosePrePreparedLanguage("target");
        assertTrue(englishTranslationPage.stateOfPrePreparedLanguage("target"),
                "The language was not pressed.");
    }

    @Test //US #5
    public void openingTheListWithAllSupportedSourceLanguages() {
        englishTranslationPage.openThePage();
        englishTranslationPage.openTheListWithAllSupportedSourceOrTargetLanguages("source");
        assertEquals(104, englishTranslationPage.countOfAllSupportedSourceOrTargetLanguages(),
                "Count of all supported languages did not match.");
    }

    @Test //US #6
    public void openingTheListWithAllSupportedTargetLanguages() {
        englishTranslationPage.openThePage();
        englishTranslationPage.openTheListWithAllSupportedSourceOrTargetLanguages("target");
        assertEquals(104, englishTranslationPage.countOfAllSupportedSourceOrTargetLanguages(),
                "Count of all supported languages did not match.");
    }

    @Test //US #7
    public void selectingALanguageFromSourceList() {
        englishTranslationPage.openThePage();
        englishTranslationPage.openTheListWithAllSupportedSourceOrTargetLanguages("target");
        englishTranslationPage.chooseTheLanguageFromSourceList("");
    }

}
