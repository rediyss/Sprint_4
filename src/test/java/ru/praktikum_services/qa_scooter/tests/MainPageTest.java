package ru.praktikum_services.qa_scooter.tests;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import ru.praktikum_services.qa_scooter.pageobject.MainPage;

public class MainPageTest {
    private WebDriver driver;

    @Test
    public void testPanelAnswer() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);


        driver.get("https://qa-scooter.praktikum-services.ru/");


        MainPage mainPage = new MainPage(driver);
        mainPage.acceptCookies(); // Убираем cookie-баннер
        mainPage.expandFaqButtonPanel(0); // Нажимаем на первый вопрос

        Assert.assertTrue("Ответ не отображается", mainPage.isFaqPannelIndex(0)); // Проверяем, что ответ открыт
    }
    @After
    public void teardown(){
        driver.quit();
    }
}
