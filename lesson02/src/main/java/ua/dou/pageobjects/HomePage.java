package ua.dou.pageobjects;

public class HomePage extends BasePage {
    private static final String titleOfHomePage = "Сообщество программистов | DOU";

    public String getUrlOfHomePage() {
        return super.getUrlOfDOU();
    }

    public String getTheTitle() {
        return titleOfHomePage;
    }
}
