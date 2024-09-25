package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import pageObject.OrderPage;

import static org.junit.Assert.assertTrue;

public class OrderTestFireFox {
    private WebDriver driver;
    private OrderPage orderPage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "C:\\tools\\firefoxdriver\\geckodriver.exe");
        FirefoxOptions options = new FirefoxOptions();
        driver = new FirefoxDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        orderPage = new OrderPage(driver);
    }

    @Test
    public void testOrderFlow() {
        // Заказ через первую кнопку
        orderPage.clickFirstOrderButton();
        orderPage.fillFirstPartOfOrderForm("Владимир", "Маяковский", "Ломоносова 100", "Сокольники", "89012345678");

        // Заполнение второй части формы
        orderPage.fillSecondPartOfOrderForm( "двое суток",  "Оставьте у двери");

        // Проверка успешного оформления заказа
        assertTrue(orderPage.isOrderSuccessful());

        // Перезагрузка страницы для тестирования второй кнопки
        driver.navigate().refresh();
        orderPage.clickSecondOrderButton();

        // Заполнение формы с другими данными
        orderPage.fillFirstPartOfOrderForm("Алексей", "Твардовский", "Невский проспект 100", "Краснопресненская", "89087654321");
        orderPage.fillSecondPartOfOrderForm( "трое суток",  "Пожалуйста, позвоните перед доставкой");

        // Проверка успешного оформления заказа
        assertTrue(orderPage.isOrderSuccessful());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Закрытие браузера после теста
        }
    }
}