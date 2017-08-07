package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CoachPage extends BasePage {
    private By courses = By.xpath(".//ul/li/a");

    public CoachPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
    }

    public boolean hasCurs(String curs) {
        List<WebElement> coursesOnPage = driver.findElements(courses);
        for (WebElement element : coursesOnPage) {
            if (curs.equals(element.getText())) return true;
        }
        return false;
    }
}
