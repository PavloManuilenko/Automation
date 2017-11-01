import com.google.translate.PageObjects.BasePage;
import com.google.translate.PageObjects.Footer;
import com.google.translate.PageObjects.TranslatorPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class Feature3 {

    private WebDriver driver;
    private TranslatorPage translator;

    public Feature3() throws MalformedURLException {
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

    @Test //US #20
    public void openingHelpPageFromTheFooter() {
        translator.openThePage();
        translator.footer.openHelpPage();
        assertEquals(driver.getTitle(), "Google Translate Help");
    }

    @Test(enabled = false) //US #21
    public void openingFeedbackPopUp() {
        translator.openThePage();
        translator.footer.openFeedbackPopUp();
        assertEquals(translator.footer.getFeedbackCaptionText(), "Send feedback");

    }

    @Test //US #22
    public void openingPrivacyAndTermsPage() {
        translator.openThePage();
        translator.footer.openPrivacyAndTermsPage();
        assertEquals(driver.getTitle(), "Privacy & Terms – Google");
    }

    @Test //US #23
    public void openingAboutGooglePage() {
        translator.openThePage();
        translator.footer.openAboutGooglePage();
        assertEquals(driver.getCurrentUrl(),"https://www.google.com/about/");
    }

    @Test //US #24
    public void openingAboutGoogleTranslatePage() {
        translator.openThePage();
        translator.footer.openAboutGoogleTranslatePage();
        assertEquals(driver.getCurrentUrl(), "https://translate.google.com/intl/en/about/");
    }

    @Test //US #25
    public void openingCommunityPage() {
        translator.openThePage();
        translator.footer.openCommunityPage();
        assertEquals(driver.getTitle(),"Translate Community");
    }

    @Test //US #26
    public void openingMobileAppPage() {
        translator.openThePage();
        translator.footer.openThePageWithTheMobileApp();
        assertEquals(driver.getCurrentUrl(),
                "https://play.google.com/store/apps/details?id=com.google.android.apps.translate");
    }
}