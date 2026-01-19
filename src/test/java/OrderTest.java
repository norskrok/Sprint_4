import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

public class OrderTest extends BaseTest {

    @Test
    public void testTopButton() {

        // Находит кнопку Заказать сверху и нажимает на нее
        mainPage.orderBtnTop();

        // Ждет
        mainPage.waiting();

        // Сравнивает
        mainPage.checkOrderPageOpened();
    }

    @Test
    public void testBottomButton() {

        // Пролистывает вниз
        mainPage.scrollDown();

        // Находит кнопку Заказать снизу и нажимает на нее
        mainPage.orderBtnBottom();

        // Ждет
        mainPage.waiting();

        // Сравнивает
        mainPage.checkOrderPageOpened();
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
                    {"Иван", "Иванов", "Пушкина 5", "Белорусская", "+77777777777", "03.01.2026", "сутки", "черный жемчуг"},
                    {"Степан", "Степанов", "Лермонтова 5", "Аэропорт", "+76666666666", "20.01.2026", "двое суток", "серая безысходность"}
            };
        }

        @Test
        public void testFullRegistration() {

            // Нажимает на кнопку Закзать
            orderForm.clickOrderButton();

            // Вводит имя
            orderForm.writeName(name);

            // Вводит фамилию
            orderForm.writeSurname(surname);

            // Вводит адрес
            orderForm.writeAddress(address);

            // Вводит название станции метро
            orderForm.writeMetroStation(metroStation);

            // Вводит номер телефона
            orderForm.writePhoneNumber(phoneNumber);

            // Нажимает кнопку Далее
            orderForm.clickNextButton();

            // Выбирает дату
            orderForm.chooseDate(date);

            // Выбирает период
            orderForm.choosePeriod(period);

            // Выбирает цвет
            orderForm.chooseColor(color);

            // Нажимает кнопку Заказать
            orderForm.clickOrderButtonSecond();

            // Нажимает кнопку Да
            orderForm.clickYesButton();

            // Проверка
            Assert.assertTrue(orderForm.isOrderCreated());
        }

    }
}