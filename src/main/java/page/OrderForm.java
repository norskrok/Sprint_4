package page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

    // 2 страница
    private By chooseDateField = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private By yesBtn = By.xpath("//button[text()='Да']");
    private final By orderCreatedTitle = By.xpath("//div[text()='Заказ оформлен']");

    // Кнопки
    private By orderBtn1 = By.xpath("(//button[contains(.,'Заказать')])[1]");
    private By orderBtn2 = By.xpath("(//button[text()='Заказать'])[2]");

    public OrderForm(WebDriver driver) {
        this.driver = driver;
    }

    private WebDriverWait getWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Нажать "Заказать" и дождаться /order
    public void clickOrderButton() {
        driver.findElement(orderBtn1).click();
        getWait().until(ExpectedConditions.urlContains("/order"));
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
        getWait().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[text()='" + metroStation + "']")
        )).click();
    }

    public void writePhoneNumber(String phoneNumber) {
        driver.findElement(phoneNumberField).sendKeys(phoneNumber);
    }

    public void clickNextButton() {
        driver.findElement(nextBtn).click();
        getWait().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[text()='Про аренду']")
        ));
    }

    // 2 страница
    public void chooseDate(String date) {
        driver.findElement(chooseDateField).sendKeys(date, Keys.ENTER);
    }

    public void choosePeriod(String period) {
        getWait().until(ExpectedConditions.elementToBeClickable(By.className("Dropdown-placeholder"))).click();
        getWait().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class,'Dropdown-option') and text()='" + period + "']")
        )).click();
    }

    public void chooseColor(String color) {
        WebElement colorLabel = getWait().until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//label[contains(normalize-space(.),'" + color.split(" ")[1] + "')]")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", colorLabel);
    }

    public void clickOrderButtonSecond() {
        getWait().until(ExpectedConditions.elementToBeClickable(orderBtn2)).click();
    }

    public void clickYesButton() {
        getWait().until(ExpectedConditions.elementToBeClickable(yesBtn)).click();
    }

    public boolean isOrderCreated() {
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(orderCreatedTitle)).isDisplayed();
    }
}

