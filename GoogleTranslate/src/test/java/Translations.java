import com.google.translate.exceptions.DictionaryLineException;
import com.google.translate.pageobjects.BasePage;
import com.google.translate.pageobjects.TranslatorPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class Translations {

    private WebDriver driver;
    private TranslatorPage translator;

    public Translations() throws MalformedURLException {
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

    @Test //US #8 and #9
    public void translation() {
        translator.openThePage();
        if (translator.isInstantTranslationEnabled()) translator.instantTranslationModeSwitch();
        String exp = null;
        try {
            exp = translator.translate("uk", "ru", 0);
        } catch (DictionaryLineException e) {
            assertTrue(false, e.getMessage());
        }
        translator.clickTranslateButton();
        assertTrue(translator.isThereSomethingInTheResultBox(), "There is nothing in result.");
        assertEquals(translator.getTextFromTheResultBox(), exp, "Expected translation is different.");
    }

}
