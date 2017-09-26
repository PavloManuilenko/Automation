import com.google.translate.PageObjects.TranslatorPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.*;

public class Regression {

    WebDriver driver;
    TranslatorPage translator;

    public Regression() throws MalformedURLException {
    }

    @BeforeClass
    public void preparationOfTheTestSuiteToRun() throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver2.32.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        translator = new TranslatorPage(driver);
    }

    @BeforeClass(dependsOnMethods = {"preparationOfTheTestSuiteToRun"}, enabled = true)
    public void bringingUIToTheReadyState() {
        if (!translator.isUILanguageEnglish()) {
            translator.switchLanguageOfGoogleUIToEnglish();
        }
    }

    @AfterClass(enabled = true)
    public void finishRunningTheTestSuite() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

    @Test //US #1
    public void openingTheURLOfBeginPage() {
        translator.openThePage();
        assertEquals(driver.getTitle(), translator.getTitle(), "Titles are not the same.");
    }

    @Test //US #2
    public void clickingOnTheLogoForOpeningCleanTranslator() {
        translator.openThePage();
        translator.enterATextForTranslation("Some text");
        assertTrue(translator.isThereSomethingInTheResultBox(), "Expected that text will displayed.");
        translator.clickOnTheAppLogo();
        assertFalse(translator.isThereSomethingInTheResultBox(), "Expected that text will absent.");
    }

    @Test //US #3
    public void choosingASourceLanguage() {
        translator.openThePage();
        assertFalse(translator.stateOfPrePreparedLanguage("source", 0),
                "The language has already pressed.");
        translator.choosePrePreparedLanguage("source");
        assertTrue(translator.stateOfPrePreparedLanguage("source", 0),
                "The language was not pressed.");
    }

    @Test //US #4
    public void choosingATargetLanguage() {
        translator.openThePage();
        assertFalse(translator.stateOfPrePreparedLanguage("target", 1),
                "The language has already pressed.");
        translator.choosePrePreparedLanguage("target");
        assertTrue(translator.stateOfPrePreparedLanguage("target", 1),
                "The language was not pressed.");
    }

    @Test //US #5
    public void openingTheListWithAllSupportedSourceLanguages() {
        translator.openThePage();
        translator.openTheListWithAllSupportedLanguages("source");
        assertEquals(104, translator.countOfAllSupportedSourceOrTargetLanguages(),
                "Count of all supported languages did not match.");
    }

    @Test //US #6
    public void openingTheListWithAllSupportedTargetLanguages() {
        translator.openThePage();
        translator.openTheListWithAllSupportedLanguages("target");
        assertEquals(104, translator.countOfAllSupportedSourceOrTargetLanguages(),
                "Count of all supported languages did not match.");
    }

    @Test //US #7
    public void selectingALanguageFromSourceList() {
        translator.openThePage();
        translator.openTheListWithAllSupportedLanguages("source");
        String exp = translator.changePrePreparedLanguageOnSomeoneElseFromTheList("source");
        String actual = translator.whichLanguageIsTurnOn("source");
        assertEquals(actual, exp, "The turned on language is different than expected.");
    }

    @Test //US #7
    public void selectingALanguageFromTargetList() {
        translator.openThePage();
        translator.openTheListWithAllSupportedLanguages("target");
        String exp = translator.changePrePreparedLanguageOnSomeoneElseFromTheList("target");
        String actual = translator.whichLanguageIsTurnOn("target");
        assertEquals(actual, exp, "The turned on language is different than expected.");
    }

    @Test //US #8
    public void wordTranslating() {
        translator.openThePage();
        String exp = translator.translate("en", "uk", "translator", "перекладач");
        assertTrue(translator.isThereSomethingInTheResultBox(), "There is nothing in result.");
        assertEquals(translator.getTextFromTheResultBox(), exp, "Expected translation is different.");
    }

    @Test //US #9
    public void sentenceTranslating() {
        translator.openThePage();
        String exp = translator.translate("en", "uk", "Hello World!", "Привіт Світ!");
        assertTrue(translator.isThereSomethingInTheResultBox(), "There is nothing in result.");
        assertEquals(translator.getTextFromTheResultBox(), exp, "Expected translation is different.");
    }

    @Test //US #10
    public void switchingTargetAndSourceLanguages() {
        translator.openThePage();
        translator.choosePrePreparedLanguage("source");
        translator.choosePrePreparedLanguage("target");
        String s = translator.whichLanguageIsTurnOn("source");
        String t = translator.whichLanguageIsTurnOn("target");
        assertNotEquals(s, t, "Source and target languages are the same.");
        translator.switchTargetAndSourceLanguages();
        assertEquals(translator.whichLanguageIsTurnOn("source"), t, "Switching was not made");
        assertEquals(translator.whichLanguageIsTurnOn("target"), s, "Switching was not made");
    }

    @Test //US #11 and #12
    public void testingCharacterCounterAndMaxLimit() {
        translator.openThePage();
        assertTrue(translator.characterCounterState() == 0);
        translator.enterATextForTranslation("a");
        assertTrue(translator.characterCounterState() == 1);
        //Flood up to limit
        for (int i = 0; i < 100; i++) {
            translator.enterATextForTranslation("a a a a a a a a a a a a a a a a a a a a a a a a a a ");
        }
        assertTrue(translator.characterCounterState() == 5000);
    }

    @Test //US #13
    public void goToTranslateCommunityFromTranslatorPage() {
        translator.openThePage();
        translator.goToTranslateCommunity();
        assertEquals(driver.getCurrentUrl(), "https://translate.google.com/community?source=t-new-user");
        assertEquals(driver.getTitle(), "Translate Community");
    }

    @Test //US #14
    public void redirectingToLoginPageIfTryToSeePhrasebook() {
        translator.openThePage();
        translator.openPhrasebook();
        assertEquals(driver.getTitle(), "Sign in - Google Accounts");
    }

    @Test //US #15
    public void redirectingToLoginPageIfClickOnSignInButtonFromTopPanel() {
        translator.openThePage();
        translator.clickOnSignInButton();
        assertEquals(driver.getTitle(), "Sign in - Google Accounts");
    }

}
