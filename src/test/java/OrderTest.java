import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;

@RunWith(Enclosed.class)
public class OrderTest extends BaseTest {

    @RunWith(Parameterized.class)
    public static class QuestionsTest extends BaseTest {

        private final int questionNumber;

        public QuestionsTest(int questionNumber) {
            this.questionNumber = questionNumber;
        }

        // данные для параметризации
        @Parameterized.Parameters
        public static Object[][] getData() {
            return new Object[][]{
                    {0},
                    {1},
                    {2},
                    {3},
                    {4},
                    {5},
                    {6},
                    {7}
            };
        }

        @Test
        public void checkAnswerIsDisplayed() {

            // Скроллит вниз к вопросам
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

            // Кликает по вопросу
            driver.findElement(By.id("accordion__heading-" + questionNumber)).click();

            // Проверяет, что ответ появился
            assert driver.findElement(By.id("accordion__panel-" + questionNumber)).isDisplayed();
        }
    }

    @Test
    public void TestTopButton() {

        // Находит кнопку Заказать сверху и нажимает на нее
        mainPage.findsOrderButtonOnTheTop();

        // Ждет
        mainPage.Waiting();

        // Сравнивает
        mainPage.Comparing();
    }

    @Test
    public void TestBottomBotton() {

        // Пролистывает вниз
        mainPage.scrollsDown();

        // Находит кнопку Заказать снизу и нажимает на нее
        mainPage.findsButtonOnTheBottom();

        // Ждет
        mainPage.Waiting();

        // Сравнивает
        mainPage.Comparing();
    }

    @RunWith(Parameterized.class)

    public static class RegistrationTest extends BaseTest {

        public final String name;
        public final String surname;
        public final String address;
        public final String metroStation;
        public final String phoneNumber;
        public final String date;
        public final String period;
        public final String color;

        public RegistrationTest(String name, String surname, String address,
                                String metroStation, String phoneNumber,
                                String date, String period, String color) {
            this.name = name;
            this.surname = surname;
            this.address = address;
            this.metroStation = metroStation;
            this.phoneNumber = phoneNumber;
            this.date = date;
            this.period = period;
            this.color = color;
        }

        @Parameterized.Parameters
        public static Object[][] getData() {
            return new Object[][]{
                    {"Иван", "Иванов", "Пушкина 5", "Белорусская", "+77777777777",
                            "03.01.2026", "сутки", "черный жемчуг"},
                    {"Степан", "Степанов", "Лермонтова 5", "Аэропорт", "+76666666666",
                            "20.01.2026", "двое суток", "серая безысходность"}
            };
        }

        @Test
        public void testFullRegistration() {

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            // Нажимает кнопку Заказать
            mainPage.clickOrderButton();

            // Ожидает перехода на страницу заказа
            wait.until(ExpectedConditions.urlContains("/order"));

            // Вводит имя
            mainPage.writeName(name);

            // Вводит фамилию
            mainPage.writeSurname(surname);

            // Вводит адрес
            mainPage.writeAddress(address);

            // Вводит название станции метро
            mainPage.writeMetroStation(metroStation);

            // Выбирает станцию метро
            mainPage.chooseMetroStation(wait, metroStation);

            // Вводит номер телефона
            mainPage.writePhoneNumber(phoneNumber);

            // Нажимает кнопку Далее
            mainPage.clickNextButton();

            // Ждет следующую страницу
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Про аренду']")));

            // Вводит и нажимает дату
            mainPage.chooseDate(date);

            // Открываем дропдаун срока аренды
            wait.until(ExpectedConditions.elementToBeClickable(By.className("Dropdown-placeholder"))).click();

            // Выбирает срок аренды
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'Dropdown-option') and text()='" + period + "']"))).click();

            // Выбирает цвет
            mainPage.chooseColor(wait, color);

            // Нажимает кнопку Заказать
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[text()='Заказать'])[2]"))).click();

            // Ждет кнопку Да в окне подтверждения и кликает по ней
            mainPage.clickYesButton();

            // Ждет окно подтверждение
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Заказ оформлен']")));

            // Нажимает кнопку посмотреть статус
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Посмотреть статус']"))).click();

        }
    }
}