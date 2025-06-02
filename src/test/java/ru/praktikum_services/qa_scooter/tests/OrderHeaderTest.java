package ru.praktikum_services.qa_scooter.tests;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static ru.praktikum_services.qa_scooter.Constants.BASE_URL;

import ru.praktikum_services.qa_scooter.pageobject.OrderPage;
import ru.praktikum_services.qa_scooter.pageobject.MainPage;

@RunWith(Parameterized.class)
public class OrderHeaderTest {
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

    public OrderHeaderTest(String firstName, String secondName, String address, String metro, String phone, String date, boolean grey, boolean black, String comment){
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
    @Before
    public void setUp() {
        // Запускаем браузер без headless
        driver = new ChromeDriver();
        driver.manage().window(); // чтобы точно был виден
    }

    @Test
    public void testFullOrder(){

        driver.get(BASE_URL);

        MainPage mainPage = new MainPage(driver);
        mainPage.acceptCookies();
        mainPage.clickOrderHeaderButton();

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
