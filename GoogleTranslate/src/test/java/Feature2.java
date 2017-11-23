import com.google.translate.pageobjects.BasePage;
import com.google.translate.pageobjects.TranslatorPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class Feature2 {

    private WebDriver driver;
    private TranslatorPage translator;

    public Feature2() throws MalformedURLException {
    }

    @BeforeClass
    public void preparationOfTheTestSuiteToRun() throws MalformedURLException {
        translator = new TranslatorPage(driver = BasePage.getTheDriver(5));
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

    @Test //US #18
    public void turningOnDetectingLanguageMode() {
        translator.openThePage("/#en/ru/");
        assertFalse(translator.stateOfPrePreparedLanguage(TranslatorPage.Area.SOURCE, 4),
                "The language has already pressed.");
        translator.choosePrePreparedLanguage(TranslatorPage.Area.SOURCE, 4);
        assertTrue(translator.stateOfPrePreparedLanguage(TranslatorPage.Area.SOURCE, 4),
                "The language was not pressed.");
    }

    @Test //US #18
    public void detectingLanguage() {
        translator.openThePage("/#ru/en/");
        translator.choosePrePreparedLanguage(TranslatorPage.Area.SOURCE, 4);
        assertEquals(translator.getTextOFTheLanguageButton(4), "Detect language");
        translator.enterATextForTranslation("Do you speak English?");
        assertTrue(translator.isThereSomethingInTheResultBox());
        assertEquals(translator.getTextOFTheLanguageButton(4), "English - detected");
    }

    @Test //US #19
    public void impossibleToSwitchTargetAndSourceLanguagesIfSourceIsNotDetected() {
        translator.openThePage("/#ru/en/");
        assertTrue(translator.isSwitchButtonEnabled());
        translator.choosePrePreparedLanguage(TranslatorPage.Area.SOURCE, 4);
        assertFalse(translator.isSwitchButtonEnabled(), "Switch Button should be Disabled");
    }

    @Test //US #19
    public void possibleToSwitchTargetAndSourceLanguagesIfSourceIsDetected() {
        translator.openThePage("/#en/uk/");
        translator.choosePrePreparedLanguage(TranslatorPage.Area.SOURCE, 4);
        translator.enterATextForTranslation("Ви говорите українською мовою?");
        assertTrue(translator.isThereSomethingInTheResultBox());
        assertEquals(translator.getTextOFTheLanguageButton(4), "Ukrainian - detected");
        assertFalse(translator.isSwitchButtonEnabled(), "Switch Button should be Disabled");
    }
}
