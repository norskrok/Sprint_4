package page;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import java.time.Duration;


public class MainPage {
    private WebDriver driver;
    private String URL = "https://qa-scooter.praktikum-services.ru/order";
    private By orderBtnTop = By.className("Button_Button__ra12g");
    private By orderBtnBottom = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");
    private By yesBtn = By.xpath("//button[text()='Да']");
    private By chooseDateField = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private By nextBtn = By.xpath("//button[text()='Далее']");
    private By phoneNumberField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private By metroStationField = By.xpath("//input[@placeholder='* Станция метро']");
    private By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private By surnameField = By.xpath("//input[@placeholder='* Фамилия']");
    private By nameField = By.xpath("//input[@placeholder='* Имя']");
    private By orderBtn1 = By.xpath("(//button[contains(.,'Заказать')])[1]");


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }



    public void Comparing() {
        Assert.assertEquals(URL, driver.getCurrentUrl());
    }

    public void Waiting() {
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

    public void chooseColor(WebDriverWait wait, String color) {
        WebElement colorLabel = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[contains(normalize-space(.),'" + color.split(" ")[1] + "')]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", colorLabel);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", colorLabel);
    }

    public void clickYesButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(yesBtn))
                .click();
    }

    public void chooseDate(String date) {
        driver.findElement(chooseDateField).sendKeys(date, Keys.ENTER);
    }

    public void clickNextButton() {
        driver.findElement(nextBtn).click();
    }

    public void writePhoneNumber(String phoneNumber) {
        driver.findElement(phoneNumberField).sendKeys(phoneNumber);
    }

    public void chooseMetroStation(WebDriverWait wait, String metroStation) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + metroStation + "']"))).click();
    }

    public void writeMetroStation(String metroStation) {
        driver.findElement(metroStationField).sendKeys(metroStation);
    }

    public void writeAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    public void writeSurname(String surname) {
        driver.findElement(surnameField).sendKeys(surname);
    }

    public void writeName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void clickOrderButton() {
        driver.findElement(orderBtn1).click();
    }
}