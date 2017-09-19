import com.google.translate.PageObjects.TranslationFromEnglish;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class Regression {

    WebDriver driver;
    TranslationFromEnglish englishTranslationPage;

    public Regression() throws MalformedURLException {
    }

    @BeforeClass
    public void preparationOfTheTestSuiteToRun() throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver2.32.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        englishTranslationPage = new TranslationFromEnglish(driver);
    }

    @BeforeClass(dependsOnMethods = {"preparationOfTheTestSuiteToRun"})
    public void bringingUIToTheReadyState() {
        if (!englishTranslationPage.isUILanguageEnglish()) {
            englishTranslationPage.switchLanguageOfGoogleUIToEnglish();
        }
    }

    @AfterClass
    public void finish() {
        driver.quit();
    }

    @Test
    public void openTheURLOfBeginPage() {
        englishTranslationPage.openTheBaseURL();
        assertEquals(driver.getTitle(), englishTranslationPage.getTitle());
    }

}
