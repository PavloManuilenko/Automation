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
    private By coaches = By.xpath(".//div[@class='member']");
    private By coachNames = By.xpath(".//span[@class='name']");

    private List<WebElement> allCoaches;

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
        waitPresenceOfElement(coaches, 2);
    }

    public void getListOfTeam() {
        allCoaches = driver.findElements(coaches);
    }

    public String getCoachPositionViaName(String coachName) {
        getListOfTeam();
        String coachPosition = "Unknown";
        for (WebElement li : allCoaches) {
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
        waitPresenceOfElement(coachNames, 5);
        List<WebElement> namesOfAllCoaches = driver.findElements(coachNames);
        for (WebElement coach : namesOfAllCoaches) {
            String name = coach.getText().trim();
            if (coachName.equalsIgnoreCase(name)) {
                coach.click(); break;
            }
        }
    }
}
