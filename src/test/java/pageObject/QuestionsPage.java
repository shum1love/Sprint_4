package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class QuestionsPage {
    private WebDriver driver;

    // Конструктор
    public QuestionsPage(WebDriver driver) {
        this.driver = driver;
    }

    // Локаторы
    private By cookieButton = By.xpath("//button[@class='App_CookieButton__3cvqF']");
    private By questionButton = By.xpath("//div[@id='accordion__heading-0']");
    private By answerText = By.xpath("//div[@id='accordion__panel-0']");

    // Методы взаимодействия
    public void acceptCookies() {
        driver.findElement(cookieButton).click();
    }

    public void clickQuestion() {
        WebElement question = driver.findElement(questionButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", question);
        question.click();
    }

    public boolean isAnswerDisplayed() {

        return driver.findElement(answerText).isDisplayed();
    }
}