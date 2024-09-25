package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class OrderPage {
    private WebDriver driver;

    // Конструктор класса
    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // Метод для клика по кнопке "Заказать" в шапке
    public void clickFirstOrderButton() {
        WebElement orderButton = driver.findElement(By.xpath("//button[@class='Button_Button__ra12g']"));
        orderButton.click();
    }
    // Метод для клика по кнопке "Заказать" внизу
    public void clickSecondOrderButton() {
        WebElement orderButton = driver.findElement(By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']"));
        orderButton.click();
    }

    // Метод для заполнения первой части формы заказа
    public void fillFirstPartOfOrderForm(String firstName, String secondName, String address, String metroStation,
                                         String phoneNumber) {
        WebElement firstNameField = driver.findElement(By.xpath("//input[@placeholder='* Имя']"));
        WebElement secondNameField = driver.findElement(By.xpath("//input[@placeholder='* Фамилия']"));
        WebElement addressField = driver.findElement(By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']"));
        WebElement metroStationField = driver.findElement(By.xpath("//input[@placeholder='* Станция метро']"));
        WebElement phoneField = driver.findElement(By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']"));
        WebElement submitButton = driver.findElement(By.xpath("//button[text()='Далее']"));

        // Заполняем поля формы
        firstNameField.sendKeys(firstName);
        secondNameField.sendKeys(secondName);
        addressField.sendKeys(address);
        metroStationField.click();

        // Ожидаем и выбираем станцию метро
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@value='4']"))); // Заменить на нужный индекс
        WebElement metroSokolniki = driver.findElement(By.xpath("//button[@value='4']"));
        metroSokolniki.click();

        phoneField.sendKeys(phoneNumber);
        submitButton.click();
    }

    // Метод для заполнения второй части формы заказа
    public void fillSecondPartOfOrderForm(String rentalPeriod, String comment) {
        // Поле "Когда привезти самокат"
        WebElement dateField = driver.findElement(By.xpath("//input[@placeholder='* Когда привезти самокат']"));
        dateField.click();

        // Дожидаемся появления всех доступных дней в календаре
        List<WebElement> availableDays = driver.findElements(By.xpath("//div[contains(@class, 'react-datepicker__day react-datepicker__day--')]"));

        // Генерация случайного дня
        Random random = new Random();
        int randomIndex = random.nextInt(availableDays.size());

        // Выбор случайного дня
        WebElement randomDay = availableDays.get(randomIndex);
        randomDay.click();

        // Поле "Срок аренды"
        WebElement rentalPeriodDropdown = driver.findElement(By.className("Dropdown-control"));
        rentalPeriodDropdown.click();
        WebElement rentalOption = driver.findElement(By.xpath("//div[@class='Dropdown-option' and text()='" + rentalPeriod + "']"));
        rentalOption.click();

        WebElement colorScooter = driver.findElement(By.xpath("//input[@id='black']"));
        colorScooter.click();

        // Поле "Комментарий для курьера"
        WebElement commentField = driver.findElement(By.xpath("//input[@placeholder='Комментарий для курьера']"));
        commentField.sendKeys(comment);

        // Кнопка "Заказать"
        WebElement orderButton = driver.findElement(By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']"));
        orderButton.click();
        //Кнопка "Да" в окне
        WebElement yesButton = driver.findElement(By.xpath("//button[text()='Да']"));
        yesButton.click();
    }


    // Метод для проверки успешного оформления заказа
    public boolean isOrderSuccessful() {
        WebElement successMessage = driver.findElement(By.xpath("//div[text()='Заказ оформлен']"));
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(successMessage));
        return successMessage.isDisplayed();
    }
}
