import com.google.play.pageobjects.BasePage;
import com.google.play.pageobjects.MusicLibrary;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class ExportAllPlayLists {

    private WebDriver driver;
    private MusicLibrary music;
    private String titleOfThePageAfterTryToLogin;

    @BeforeClass
    public void openGooglePlayMusicAndSignIntoGoogleAccount() throws MalformedURLException {
        music = new MusicLibrary(driver = BasePage.getTheDriver(10));
        titleOfThePageAfterTryToLogin = music.signIn("", "");
    }

    @AfterClass
    public void endOfTesting() {
        //driver.quit();
    }

    @Test
    public void verifyTitleAfterLogin() throws MalformedURLException {
        Assert.assertEquals(music.getTitleOfHomePage(), driver.getTitle());
    }

    @Test
    public void navigateToAllPlayLists() {
        Assert.assertEquals(music.goToAllPlayLists(), driver.getTitle());
    }

    @Test
    public void CollectAllPlayLists() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Music library")));
    }

}
