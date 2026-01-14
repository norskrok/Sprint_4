package page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import java.time.Duration;

public class OrderForm {
    private WebDriver driver;
    // 1 страница
    private By nameField = By.xpath("//input[@placeholder='* Имя']");
    private By surnameField = By.xpath("//input[@placeholder='* Фамилия']");
    private By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private By metroStationField = By.xpath("//input[@placeholder='* Станция метро']");
    private By phoneNumberField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private By nextBtn = By.xpath("//button[text()='Далее']");

    private By chooseDateField = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private By yesBtn = By.xpath("//button[text()='Да']");
    private By orderBtn1 = By.xpath("(//button[contains(.,'Заказать')])[1]");


    public OrderForm(WebDriver driver) {
        this.driver = driver;
    }


    // 1 страница
    public void writeName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void writeSurname(String surname) {
        driver.findElement(surnameField).sendKeys(surname);
    }

    public void writeAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    public void writeMetroStation(String metroStation) {
        driver.findElement(metroStationField).sendKeys(metroStation);
    }

    public void chooseMetroStation(WebDriverWait wait, String metroStation) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + metroStation + "']"))).click();
    }

    public void writePhoneNumber(String phoneNumber) {
        driver.findElement(phoneNumberField).sendKeys(phoneNumber);
    }

    public void clickNextButton() {
        driver.findElement(nextBtn).click();
    }

    // 2 страница
    public void chooseDate(String date) {
        driver.findElement(chooseDateField).sendKeys(date, Keys.ENTER);
    }

    public void clickYesButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(yesBtn))
                .click();
    }

    public void chooseColor(WebDriverWait wait, String color) {
        WebElement colorLabel = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[contains(normalize-space(.),'" + color.split(" ")[1] + "')]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", colorLabel);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", colorLabel);
    }

    public void clickOrderButton() {
        driver.findElement(orderBtn1).click();
    }
}