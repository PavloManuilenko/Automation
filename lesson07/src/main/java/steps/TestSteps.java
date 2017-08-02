package steps;

import org.jbehave.core.annotations.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageobjects.OurTeam;
import story.WebDriverStories;

public class TestSteps {

    static WebDriver driver;
    OurTeam teamPage;

    @BeforeScenario
    public void driverStart() {
        System.setProperty("webdriver.gecko.driver", "./src/main/resources/geckodriver.exe");
        driver = new FirefoxDriver();
        teamPage = new OurTeam(driver);
    }

    @AfterScenario
    public void drivertStop() {
        driver.quit();
    }

    @Given("Navigate to Team page from Home page")
    public void openTeamPage() throws InterruptedException {
        teamPage.navigateFromMainPage();
    }

    @Then("$coach has position $position")
    public void searchCoachInListOfTheTeam(String coach, String position) {
        Assert.assertEquals(teamPage.getCoachPositionViaName(coach), position);
    }

}

