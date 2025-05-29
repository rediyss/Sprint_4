package ru.praktikum_services.qa_scooter.tests;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import ru.praktikum_services.qa_scooter.pageobject.OrderPage;
import ru.praktikum_services.qa_scooter.pageobject.MainPage;

@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver driver;

    private final String firstName;
    private final String secondName;
    private final String address;
    private final String metro;
    private final String phone;
    private final String date;
    private final boolean grey;
    private final boolean black;
    private final String comment;

    public OrderTest(String firstName, String secondName, String address, String metro, String phone, String date, boolean grey, boolean black, String comment){
        this.firstName = firstName;
        this.secondName = secondName;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.grey = grey;
        this.black = black;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] testData(){
        return new Object[][]{
                {"Иван", "Петров", "ул. Ленина, 1", "Черкизовская", "89001112233", "24.05.2025", true, false, "оставить у двери"},
                {"Анна", "Козлова", "пр-т Мира, 5", "ВДНХ", "89992223344", "25.05.2025", false, true, "не звонить"},
        };
    }

    @Test
    public void testFullOrder(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");

        MainPage mainPage = new MainPage(driver);
        mainPage.acceptCookies();
        mainPage.clickOrderButton();

        OrderPage orderPage = new OrderPage(driver);
        orderPage.enterFirstForm(firstName, secondName, address, metro, phone);
        orderPage.enterSecondForm(date, grey, black, comment);

        Assert.assertTrue("Заказ не подтвержден", orderPage.isOrderConfirmed());
    }
    @After
    public void teardown(){
        driver.quit();
    }
}
