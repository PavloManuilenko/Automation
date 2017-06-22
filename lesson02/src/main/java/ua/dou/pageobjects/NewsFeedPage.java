package ua.dou.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class NewsFeedPage extends BasePage {
    private static final String urlOfForum = "https://dou.ua/lenta/";
    private static final String titleOfForumPage = "Новые записи — Лента | DOU";
    private static final By lentaFromtopPanel = new By.ByXPath(".//a[text() = 'Лента']");
    private static final By eventBlock = new By.ByXPath(".//div[@class = 'b-adv-events']");
    private static final By eventFromBlock = new By.ByXPath(".//div[@class = 'adv-event-block']");

    public NewsFeedPage(WebDriver driver) {
        super(driver);
    }

    public String getURL() {
        return urlOfForum;
    }

    public String getTheTitle() {
        return titleOfForumPage;
    }

    public By getLentaFromTopPanel() {
        return lentaFromtopPanel;
    }

    public int countEventsInTheBlock() {
        List<WebElement> arrOfEvents = new ArrayList<WebElement>();

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(eventBlock));

        arrOfEvents = driver.findElements(eventFromBlock);

        return arrOfEvents.size();
    }

    public void soutEventsDateAndCityFromTheBlock() {
        List<WebElement> arrOfEvents;

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(eventBlock));

        arrOfEvents = driver.findElements(eventFromBlock);

        for (WebElement elm : arrOfEvents) {
            System.out.println(elm.findElement(By.className("date")).getText());
        }
    }

    public ArrayList<String> getListOfEventsDates() {
        List<WebElement> arrOfEvents;
        ArrayList<String> arrOfDateFromWEB = new ArrayList<String>();

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(eventBlock));

        arrOfEvents = driver.findElements(eventFromBlock);

        for (int i = 0; i < arrOfEvents.size(); i++) {
            arrOfDateFromWEB.add(arrOfEvents.get(i).findElement(By.className("date")).getText());
            arrOfDateFromWEB.set(i, pickTheDate(arrOfDateFromWEB.get(i), ','));
        }
        return arrOfDateFromWEB;
    }

    private String pickTheDate(String sourceStr, char lastChar) {
        int lastIndex = sourceStr.indexOf(lastChar);
        return sourceStr.substring(0, lastIndex);
    }
}