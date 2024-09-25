package tests;
import pageObject.QuestionsPage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.assertTrue;


public class QuestionsTestChrome {
    private WebDriver driver;
    private QuestionsPage questionsPage; // Объект страницы

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\tools\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        questionsPage = new QuestionsPage(driver); // Инициализация объекта страницы
        questionsPage.acceptCookies(); // Принятие cookies
    }

    @Test
    public void testQuestionsDropdown() {
        questionsPage.clickQuestion(); // Клик по вопросу
        assertTrue(questionsPage.isAnswerDisplayed()); // Проверка отображения ответа
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
