import com.google.translate.pageobjects.BasePage;
import com.google.translate.pageobjects.TranslatorPage;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import java.net.MalformedURLException;
import static org.testng.Assert.*;

public class Feature1 {

    private WebDriver driver;
    private TranslatorPage translator;

    public Feature1() throws MalformedURLException {
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

    @Test //US #16
    public void turningOffInstantTranslation() {
        translator.openThePage();
        if (translator.isInstantTranslationEnabled()) translator.instantTranslationModeSwitch();
        translator.enterATextForTranslation("test");
        assertFalse(translator.isThereSomethingInTheResultBox(), "There is something in the resultBox");
    }

    @Test //US #17
    public void turningOnInstantTranslation() {
        translator.openThePage();
        if (translator.isInstantTranslationEnabled()) translator.instantTranslationModeSwitch();
        translator.instantTranslationModeSwitch();
        translator.enterATextForTranslation("test");
        assertTrue(translator.isThereSomethingInTheResultBox(), "There is nothing in the resultBox");
    }
}
