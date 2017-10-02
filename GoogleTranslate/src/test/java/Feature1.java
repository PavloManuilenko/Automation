import com.google.translate.PageObjects.TranslatorPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.*;

public class Feature1 {

    WebDriver driver;
    TranslatorPage translator;

    public Feature1() throws MalformedURLException {
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

    @Test //US #16
    public void turningOffInstantTranslation() {
        translator.openThePage();
        if (!translator.isInstantTranslationEnabled()) translator.instantTranslationModeSwitch();
        translator.enterATextForTranslation("test");
        assertFalse(translator.isThereSomethingInTheResultBox(), "There is something in the resultBox");
    }

    @Test //US #17
    public void turningOnInstantTranslation() {
        translator.openThePage();
        if (!translator.isInstantTranslationEnabled()) translator.instantTranslationModeSwitch();
        translator.instantTranslationModeSwitch();
        translator.enterATextForTranslation("test");
        assertTrue(translator.isThereSomethingInTheResultBox(), "There is nothing in the resultBox");
    }
}
