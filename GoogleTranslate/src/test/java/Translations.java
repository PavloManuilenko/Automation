import com.google.translate.exceptions.DictionaryLineException;
import com.google.translate.pageobjects.BasePage;
import com.google.translate.pageobjects.TranslatorPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Translations {

    private WebDriver driver;
    private TranslatorPage translator;
    private List<String> locales = new ArrayList<>();
    private String[][] testData = new String[625][2];

    public Translations() throws MalformedURLException {
    }

    @BeforeClass
    public void preparationOfTheTestSuiteToRun() throws MalformedURLException {
        translator = new TranslatorPage(driver = BasePage.getTheDriver(5));
    }

    @BeforeClass(dependsOnMethods = {"preparationOfTheTestSuiteToRun"})
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

    @DataProvider
    public Object[][] language() {
        {
            locales.add("af");
            locales.add("az");
            locales.add("bg");
            locales.add("bs");
            locales.add("cs");
            locales.add("de");
            locales.add("el");
            locales.add("en");
            locales.add("es");
            locales.add("et");
            locales.add("ga");
            locales.add("hy");
            locales.add("is");
            locales.add("it");
            locales.add("ja");
            locales.add("ko");
            locales.add("nl");
            locales.add("no");
            locales.add("pl");
            locales.add("ru");
            locales.add("sq");
            locales.add("sr");
            locales.add("sv");
            locales.add("tr");
            locales.add("uk");
        }
        for (int k = 0, tmp = 0; k < locales.size() * locales.size(); k += locales.size()) {
            for (int i = 0; i < locales.size(); i++) {
                testData[i + k][0] = locales.get(tmp);
                for (int j = 1; j < testData[i].length; j++) {
                    testData[i + k][1] = locales.get(i);
                }
            }
            tmp++;
        }
        return testData;
    }

    @Test(dataProvider = "language") //US #8 and #9
    public void translation(String translateFromLang, String translateToLang) {
        for (int i = 0; i < 2; i++) {
            translator.openThePage();
            if (translator.isInstantTranslationEnabled()) translator.instantTranslationModeSwitch();
            String exp = null;
            try {
                exp = translator.translate(translateFromLang, translateToLang, i);
            } catch (DictionaryLineException e) {
                assertTrue(false, e.getMessage());
            }
            translator.clickTranslateButton();
            assertTrue(translator.isThereSomethingInTheResultBox(), "There is nothing in result.");
            assertEquals(translator.getTextFromTheResultBox().toLowerCase(), exp.toLowerCase(),
                    "Expected translation is different.");
        }
    }
}