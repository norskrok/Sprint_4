package page;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;

public class MainPage {
    private WebDriver driver;

    private String URL = "https://qa-scooter.praktikum-services.ru/order";
    private By orderBtnTop = By.className("Button_Button__ra12g");
    private By orderBtnBottom = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }


    public void comparing() {
        Assert.assertEquals(URL, driver.getCurrentUrl());
    }

    public void waiting() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlContains("/order"));
    }

    public void findsOrderButtonOnTheTop() {
        driver.findElement(orderBtnTop).click();
    }

    public void findsButtonOnTheBottom() {
        driver.findElement(orderBtnBottom).click();
    }

    public void scrollsDown() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }
}