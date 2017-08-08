package steps;

import org.jbehave.core.annotations.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageobjects.CoachPage;
import pageobjects.OurTeam;

import java.util.ArrayList;

public class TestSteps {

    private static WebDriver driver;
    private OurTeam teamPage;
    private CoachPage coachPage;

    @BeforeStory()
    public void driverStart() {
        System.setProperty("webdriver.gecko.driver", "./src/main/resources/geckodriver.exe");
        driver = new FirefoxDriver();
    }

    @AfterStory
    public void drivertStop() {
        driver.quit();
    }

    @Given("Navigate to Team page from Home page")
    public void openTeamPage() throws InterruptedException {
        teamPage = new OurTeam(driver);
        teamPage.navigateFromMainPage();
    }

    @When("I open $coach page")
    public void openCoachPage(String coach) {
        teamPage.openCoachPage(coach);
        coachPage = new CoachPage(driver);
    }

    @Then("$coach has position $position")
    @Alias("<coach> has position <position>")
    public void searchCoachInListOfTheTeam(@Named("coach") String coach, @Named("position") String position) {
        Assert.assertEquals(teamPage.getCoachPositionViaName(coach), position);
    }

    @Then("Coach has $curs")
    public void verifyCourses(String curs){
        Assert.assertTrue(coachPage.hasCurs(curs));
    }

}

