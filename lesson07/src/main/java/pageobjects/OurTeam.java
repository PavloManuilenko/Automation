package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class OurTeam extends BasePage {
    private static final String urlOfTeamPage = "http://skillsup.ua/about/our-team/";
    private By aboutUsTopMenu = By.xpath(".//div[@id='menu3039']");
    private By ourTeamInAboutUsMenu = By.xpath(".//a[@href='/about/our-team/']");
    private By areaWithAllCoaches = By.xpath(".//div[@class='team-list clearfix']");
    private By coaches = By.xpath(".//div[@class='member']");

    private List<WebElement> namsOfAllCoaches;

    public OurTeam(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get(urlOfTeamPage);
    }

    public void navigateFromMainPage() {
        driver.get(getBaseURL());
        Actions builder = new Actions(driver);
        waitPresenceOfElement(aboutUsTopMenu, 2);
        builder.moveToElement(driver.findElement(aboutUsTopMenu)).build().perform();
        builder.moveToElement(driver.findElement(ourTeamInAboutUsMenu)).click().build().perform();
        waitPresenceOfElement(areaWithAllCoaches, 2);
    }

    public void getListOfTeam() {
        namsOfAllCoaches = driver.findElements(coaches);
    }

    public String getCoachPositionViaName(String coachName) {
        getListOfTeam();
        String coachPosition = "Unknown";
        for (WebElement li : namsOfAllCoaches) {
            String nameAndPosition = li.getText();
            int separator = nameAndPosition.indexOf("\n");
            String name = nameAndPosition.substring(0, separator);
            String position = nameAndPosition.substring(separator + 1);
            if (coachName.equals(name)) {
                coachPosition = position;
            }
        }
        return coachPosition;
    }

    public void openCoachPage(String coachName) {
        driver.findElement(By.xpath(".//*[@class=\"name\"][contains(text(),'" + coachName + "')]")).click();
        /*getListOfTeam();
        waitPresenceOfElement(coaches, 2);
        for (WebElement coach : namsOfAllCoaches) {
            String nameAndPosition = coach.getText();
            int separator = nameAndPosition.indexOf("\n");
            String name = nameAndPosition.substring(0, separator);
            if (coachName.equals(name)) {
                coach.click();
            }
        }*/
    }
}
