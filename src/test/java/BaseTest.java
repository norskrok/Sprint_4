import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import page.MainPage;
import page.OrderForm;

public class BaseTest {

    protected WebDriver driver;
    MainPage mainPage;
    OrderForm orderForm;

    @Before
    public void start() {

        String browser = System.getProperty("browser", "chrome");

        if (browser.equalsIgnoreCase("firefox")) {

            driver = new FirefoxDriver();
        } else {

            driver = new ChromeDriver();
        }

        mainPage = new MainPage(driver);
        orderForm = new OrderForm(driver);

        driver.get("https://qa-scooter.praktikum-services.ru/");
        driver.findElement(By.className("App_CookieButton__3cvqF")).click();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}