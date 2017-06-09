import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main {

    static final String urlOfDOU = "https://dou.ua/";
    static final String titleOfHomePage = "Сообщество программистов | DOU";
    static final By topPanelForum = new By.ByXPath(".//a[text() = 'Форум']");
    static final By firstArticleInTheForum = new By.ByXPath(".//article[1]/h2/a");
    static final By nameOfAnArticle = new By.ByXPath(".//article[@class = 'b-typo']/h1");

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "C:\\\\Program Files\\\\Gecko\\\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        driver.get(urlOfDOU);
        driver.findElement(topPanelForum).click();

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(firstArticleInTheForum));

        driver.findElement(firstArticleInTheForum).click();

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(nameOfAnArticle));

        System.out.println("The name Of the Article is: " + driver.findElement(nameOfAnArticle).getText());

        driver.quit();
    }
}
