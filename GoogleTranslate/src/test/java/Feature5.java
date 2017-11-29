import com.google.translate.pageobjects.BasePage;
import com.google.translate.pageobjects.TranslatorPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

import static org.testng.Assert.*;

public class Feature5 {

    private WebDriver driver;
    private TranslatorPage translator;

    public Feature5() throws MalformedURLException {
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

    @AfterClass(enabled = false)
    public void finishRunningTheTestSuite() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

    @AfterMethod
    public void closeVirtualKeyboard() {
        if (translator.isVirtualKeyboardDisplayed()) translator.closeVirtualKeyboard();
    }

    @Test //US #30
    public void impossibleToUseHandwritingModeIfLanguageWasNotDetected() {
        translator.openThePage("/#auto/uk");
        assertFalse(translator.isVirtualKeyboardAccessible());
    }

    @Test //US #30
    public void possibleToUseHandwritingModeIfLanguageWasDetected() {
        translator.openThePage("/#en/uk");
        assertTrue(translator.isVirtualKeyboardAccessible());
        translator.openListWithAdditionalEntryTypes();
        translator.enableHandwritingMode();
        //<canvas class="ita-hwt-canvas" width="425" height="194"></canvas>
    }

}
