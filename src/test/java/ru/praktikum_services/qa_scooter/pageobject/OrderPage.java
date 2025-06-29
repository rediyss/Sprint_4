package ru.praktikum_services.qa_scooter.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class OrderPage {
    private final WebDriver driver;

    //Поле имя
    private final By firstNameInput = By.xpath(".//input[@placeholder='* Имя']");
    //Поле фамилия
    private final By secondNameInput = By.xpath(".//input[@placeholder='* Фамилия']");
    //Поле Адресс
    private final By addressInput = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //Поле Станция метро
    private final By metroStationInput = By.xpath(".//input[@placeholder='* Станция метро']");
    //Поле Телефон
    private final By phoneInput = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Кнопка Далее
    private final By nextButton = By.xpath(".//button[text()='Далее']");

    //Поле с выбором даты заказа
    private final By dateInput = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //Поле с временем аренды
    private final By rentPeriod = By.xpath("//div[@class='Dropdown-root']");
    //выпадающий список с сутками
    private final By periodOption = By.xpath("//div[@class='Dropdown-menu']");

    //серый чекбокс без выбора цвета
    private final By blackColorCheckbox = By.id("black");
    //черный чекбокс с выбранным цветом
    private final By greyColorCheckbox = By.id("grey");
    //Поле с коментарием для курьера
    private final By commentInput = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //Кнопка заказать
    private final By nextOrderButton = By.xpath("//div[contains(@class,'Order_Buttons')]//button[text()='Заказать']");
    //Кнопка Да с подтверждением заказа
    private final By confirmOrderButton = By.xpath("//div[@class='Order_Modal__YZ-d3']//button[text()='Да']");

    public OrderPage(WebDriver driver){
        this.driver = driver;
    }

    public void enterFirstForm(String firstName, String secondName, String address, String metro, String phone){
        //Заполняем поле имя
        driver.findElement(firstNameInput).sendKeys(firstName);
        //Заполняем поле фамилия
        driver.findElement(secondNameInput).sendKeys(secondName);
        //Заполняем поле адресс
        driver.findElement(addressInput).sendKeys(address);
        //Заполняем поле метро
        driver.findElement(metroStationInput).sendKeys(metro);
        driver.findElement(By.className("select-search__select")).click();
        //Заполняем поле номе
        driver.findElement(phoneInput).sendKeys(phone);
        //кликаем на кнопку Далее
        driver.findElement(nextButton).click();
    }
    public void enterSecondForm(String date, boolean grey, boolean black, String comment){
        //Присваеваем переменной элемент даты
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(5));
        WebElement dateEl = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("input[placeholder='* Когда привезти самокат']")));
        //Очищаем поле даты
        dateEl.clear();
        //Заполняем дату
        dateEl.sendKeys(date);
        dateEl.sendKeys(Keys.ESCAPE);
        //Кликаем на поле срок арненды и выбираем срок
        driver.findElement(rentPeriod).click();
        driver.findElement(periodOption).click();

        //Кликаем на чекбоксы от их цвета
        if (black) driver.findElement(blackColorCheckbox).click();
        if (grey) driver.findElement(greyColorCheckbox).click();

        //Заполняем поле Комментарий для курьера
        driver.findElement(commentInput).sendKeys(comment);
        //Кликаем на кнопку заказать
        driver.findElement(nextOrderButton).click();
        //Кликаем на кнопку Да
        driver.findElement(confirmOrderButton).click();
    }
    public boolean isOrderConfirmed(){
        return  driver.getPageSource().contains("Заказ оформлен");
    }
}
