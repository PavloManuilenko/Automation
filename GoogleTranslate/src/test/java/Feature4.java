import com.google.translate.pageobjects.BasePage;
import com.google.translate.pageobjects.TranslatorPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class Feature4 {

    private WebDriver driver;
    private TranslatorPage translator;

    public Feature4() throws MalformedURLException {
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

    @AfterClass()
    public void finishRunningTheTestSuite() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

    @AfterMethod
    public void closeVirtualKeyboard() {
        if (translator.isVirtualKeyboardDisplayed()) translator.closeVirtualKeyboard();
    }

    @Test //US #27
    public void testingOfVirtualKeyboardAccessing() {
        translator.openThePage("/#auto/uk");
        assertFalse(translator.isVirtualKeyboardAccessible());
        translator.openTheListWithAllSupportedLanguages(TranslatorPage.Area.SOURCE);
        translator.changePrePreparedLanguageOnSomeoneElseFromTheList(TranslatorPage.Area.SOURCE);
        assertTrue(translator.isVirtualKeyboardAccessible());
    }

    @Test //US #27
    public void openingVirtualKeyboard() {
        translator.openThePage("/#en/uk");
        translator.openVirtualKeyboard();
        assertTrue(translator.isVirtualKeyboardDisplayed());
    }

    @Test //US #28
    public void enteringATextUsingVirtualKeyboard() {
        translator.openThePage("/#en/uk");
        translator.instantTranslationModeSwitch();
        translator.openVirtualKeyboard();
        translator.typeOnVirtualKeyboard("The Quick Brown Fox Jumps Over The Lazy Dog-1234567890.,");
        translator.clickTranslateButton();
        assertTrue(translator.isThereSomethingInTheResultBox());
        assertEquals(driver.getCurrentUrl(),
                "https://translate.google.com/#en/uk/The%20Quick%20Brown%20Fox%20Jumps%20Over%20The%20Lazy%20Dog-1234567890.%2C");
    }

    @Test //US #29
    public void closingOfVirtualKeyboard() {
        translator.openThePage("/#en/uk");
        translator.openVirtualKeyboard();
        translator.closeVirtualKeyboard();
        assertFalse(translator.isVirtualKeyboardDisplayed());
        assertTrue(translator.isVirtualKeyboardAccessible());
    }

}
