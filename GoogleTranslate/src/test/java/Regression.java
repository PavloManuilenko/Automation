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

    @AfterClass(enabled = true)
    public void finishRunningTheTestSuite() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

    @Test(enabled = true) //US #1
    public void openingTheURLOfBeginPage() {
        //englishTranslationPage.openTheBaseURL();
        englishTranslationPage.openThePage();
        assertEquals(driver.getTitle(), englishTranslationPage.getTitle(), "Titles are not the same.");
    }

    @Test(enabled = true) //US #2
    public void clickingOnTheLogoForOpeningCleanTranslator() {
        englishTranslationPage.openThePage();
        englishTranslationPage.enterATextForTranslation("Some text");
        assertTrue(englishTranslationPage.isThereSomethingInTheResultBox(), "Expected that text will displayed.");
        englishTranslationPage.clickOnTheAppLogo();
        assertFalse(englishTranslationPage.isThereSomethingInTheResultBox(), "Expected that text will absent.");
    }

    @Test(enabled = true) //US #3
    public void choosingASourceLanguage() {
        englishTranslationPage.openThePage();
        assertFalse(englishTranslationPage.stateOfPrePreparedLanguage("source", 0),
                "The language has already pressed.");
        englishTranslationPage.choosePrePreparedLanguage("source");
        assertTrue(englishTranslationPage.stateOfPrePreparedLanguage("source", 0),
                "The language was not pressed.");
    }

    @Test(enabled = true) //US #4
    public void choosingATargetLanguage() {
        englishTranslationPage.openThePage();
        assertFalse(englishTranslationPage.stateOfPrePreparedLanguage("target", 1),
                "The language has already pressed.");
        englishTranslationPage.choosePrePreparedLanguage("target");
        assertTrue(englishTranslationPage.stateOfPrePreparedLanguage("target", 1),
                "The language was not pressed.");
    }

    @Test(enabled = true) //US #5
    public void openingTheListWithAllSupportedSourceLanguages() {
        englishTranslationPage.openThePage();
        englishTranslationPage.openTheListWithAllSupportedLanguages("source");
        assertEquals(104, englishTranslationPage.countOfAllSupportedSourceOrTargetLanguages(),
                "Count of all supported languages did not match.");
    }

    @Test(enabled = true) //US #6
    public void openingTheListWithAllSupportedTargetLanguages() {
        englishTranslationPage.openThePage();
        englishTranslationPage.openTheListWithAllSupportedLanguages("target");
        assertEquals(104, englishTranslationPage.countOfAllSupportedSourceOrTargetLanguages(),
                "Count of all supported languages did not match.");
    }

    @Test(enabled = true) //US #7
    public void selectingALanguageFromSourceList() {
        englishTranslationPage.openThePage();
        englishTranslationPage.openTheListWithAllSupportedLanguages("source");
        String exp = englishTranslationPage.changePrePreparedLanguageOnSomeoneElseFromTheList("source");
        String actual = englishTranslationPage.whichLanguageIsTurnOn("source");
        assertEquals(actual, exp, "The turned on language is different than expected.");
    }

    @Test(enabled = true) //US #7
    public void selectingALanguageFromTargetList() {
        englishTranslationPage.openThePage();
        englishTranslationPage.openTheListWithAllSupportedLanguages("target");
        String exp = englishTranslationPage.changePrePreparedLanguageOnSomeoneElseFromTheList("target");
        String actual = englishTranslationPage.whichLanguageIsTurnOn("target");
        assertEquals(actual, exp, "The turned on language is different than expected.");
    }

}
