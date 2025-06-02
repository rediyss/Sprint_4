package ru.praktikum_services.qa_scooter.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static ru.praktikum_services.qa_scooter.Constants.BASE_URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.praktikum_services.qa_scooter.pageobject.MainPage;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertTrue;


@RunWith(Parameterized.class)
public class MainPageTest {

    private WebDriver driver;

    private final int index; // номер вопроса
    private final String expectedAnswer; // ожидаемый текст ответа

    public MainPageTest(int index, String expectedAnswer) {
        this.index = index;
        this.expectedAnswer = expectedAnswer;
    }

    // параметры: индекс и текст ответа
    @Parameterized.Parameters
    public static Collection<Object[]> getFaqData() {
        return Arrays.asList(new Object[][]{
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        });
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
    }

    @Test
    public void checkFaqAnswersText() {

        driver.get(BASE_URL);

        MainPage mainPage = new MainPage(driver);
        mainPage.acceptCookies(); // закрываем куки
        mainPage.expandFaqButtonPanel(index); // кликаем по вопросу
        String actualText = mainPage.getFaqAnswerText(index); // получаем текст ответа
        assertTrue("Ответ не совпадает", actualText.contains(expectedAnswer)); // проверяем
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}