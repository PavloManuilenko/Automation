import com.google.play.pageobjects.BasePage;
import com.google.play.pageobjects.MusicLibrary;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

public class ExportAllPlayLists {

    private WebDriver driver;
    private MusicLibrary music;
    private String titleOfThePageAfterTryToLogin;

    @BeforeAll
    public void openGooglePlayMusicAndSignIntoGoogleAccount() throws MalformedURLException {
        music = new MusicLibrary(driver = BasePage.getTheDriver(10));
        titleOfThePageAfterTryToLogin = music.signIn("0978768117", "Paul753357");
    }

    @AfterAll
    public void endOfTesting() {
        driver.quit();
    }

    @Test
    public void verifyTitleAfterLogin() throws MalformedURLException {
        Assert.assertEquals(music.getTitleOfHomePage(), driver.getTitle());
    }

    @Test
    public void navigateToAllPlayLists() {
        Assert.assertEquals(music.goToAllPlayLists(), music.getPlayListsTitle());
    }

}
