package page;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;

    private static final String URLMainPage = "https://qa-scooter.praktikum-services.ru/";
    private static final String URLOrderPage = "https://qa-scooter.praktikum-services.ru/order";
    private final By cookieButton = By.className("App_CookieButton__3cvqF");

    // Скроллит вниз к вопросам
    public void scrollDown() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    // Кликает по вопросу
    public void clickQuestion(int questionNumber) {
        By q = By.id("accordion__heading-" + questionNumber);

        WebElement el = new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(q));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", el);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
    }

    // Проверяет текст
    public String getAnswerText(int questionNumber) {
        By a = By.id("accordion__panel-" + questionNumber);

        WebElement answer = new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(a));

        return answer.getText().trim();
    }

    public By orderBtnTop = By.className("Button_Button__ra12g");
    public By orderBtnBottom = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openMainPage() {
        driver.get(URLMainPage);
    }

    public void acceptCookies() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(cookieButton)).click();
    }

    public void checkOrderPageOpened() {
        Assert.assertEquals(URLOrderPage, driver.getCurrentUrl());
    }

    public void waiting() {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.urlContains("/order"));
    }

    public void orderBtnTop() {
        driver.findElement(orderBtnTop).click();
    }

    public void orderBtnBottom() {
        driver.findElement(orderBtnBottom).click();
    }
}