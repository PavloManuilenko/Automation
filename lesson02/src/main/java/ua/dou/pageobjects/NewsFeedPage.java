package ua.dou.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class NewsFeedPage extends BasePage {
    private static final String urlOfNewsFeed = "https://dou.ua/lenta/";
    private static final String titleOfNewsFeed = "Новые записи — Лента | DOU";
    private static final By lentaFromTopPanel = new By.ByXPath(".//a[text() = 'Лента']");
    private static final By eventBlock = new By.ByXPath(".//div[@class = 'b-adv-events']");
    private static final By eventFromBlock = new By.ByXPath(".//div[@class = 'adv-event-block']");

    public NewsFeedPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(urlOfNewsFeed);
    }

    public String getTheTitle() {
        return titleOfNewsFeed;
    }

    public void openFromTopPanel() {
        openThePageFromTopPanel(lentaFromTopPanel);
    }

    public int countEventsInTheBlock() {
        List<WebElement> arrOfEvents;

        waitPresenceOfElement(eventBlock, 5);
        arrOfEvents = driver.findElements(eventFromBlock);
        return arrOfEvents.size();
    }

    public ArrayList<String> getListOfEventsDateAndCityFromTheBlock() {
        List<WebElement> listOfEvents;
        ArrayList<String> listOfEventsDateAndCity = new ArrayList<String>();

        waitPresenceOfElement(eventBlock, 5);

        listOfEvents = driver.findElements(eventFromBlock);

        for (int i = 0; i < listOfEvents.size(); i++) {
            listOfEventsDateAndCity.add(listOfEvents.get(i).findElement(By.className("date")).getText());
        }
        return listOfEventsDateAndCity;
    }

    public ArrayList<String> getListOfEventsDates() {
        List<String> listOfEventsDateAndCity = getListOfEventsDateAndCityFromTheBlock();
        ArrayList<String> arrOfDateFromEvents = new ArrayList<String>();

        waitPresenceOfElement(eventBlock, 5);

        for (int i = 0; i < listOfEventsDateAndCity.size(); i++) {
            arrOfDateFromEvents.add(pickTheDate(listOfEventsDateAndCity.get(i), ','));
        }
        return arrOfDateFromEvents;
    }

    private String pickTheDate(String sourceStr, char lastChar) {
        int lastIndex = sourceStr.indexOf(lastChar);
        if (lastIndex >= 0) return sourceStr.substring(0, lastIndex);
        else return sourceStr;
    }
}