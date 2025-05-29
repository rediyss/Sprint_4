package ru.praktikum_services.qa_scooter.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//класс главной страницы
public class MainPage{
    private final WebDriver driver;

    //Кнопка заказать на главной странице
    private final By orderButton = By.className("Button_Button__ra12g");
    //Кнопка статус заказа на главной странице
    private final By statusOrderButton = By.className("Header_Link__1TAG7");
    //Панель с вопросами
    private final By faqButtonPanel = By.className("accordion__button");
    //Кнопка панели с вопросами
    private final By faqPannelIndex = By.className("accordion__panel");
    private final By cookieAcceptButton = By.className("App_CookieButton__3cvqF");

    public MainPage(WebDriver driver){
        this.driver = driver;
    }
    public void acceptCookies() {
        if (driver.findElements(cookieAcceptButton).size() > 0) {
            WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(5));
            WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(cookieAcceptButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cookieButton);
        }
    }
    //Находим кнопку заказать и нажимаем на нее
    public void clickOrderButton(){
        driver.findElement(orderButton).click();
    }
    //Находим кнопку статус заказа и нажимаем на нее
    public void clickStatusOrderButton(){
        driver.findElement(statusOrderButton).click();
    }
    //Находим кнопку панелей с определенным индексом и нажимаем ее
    public void expandFaqButtonPanel(int index){
        driver.findElements(faqButtonPanel).get(index).click();
    }
    //Находим панель с определенным индексом и получаем ее отображение
    public boolean isFaqPannelIndex(int index){
        return driver.findElements(faqPannelIndex).get(index).isDisplayed();
    }
}
