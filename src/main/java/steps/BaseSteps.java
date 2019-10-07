package steps;


import utils.Attach;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BaseSteps {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    public static void startUp() {

        //Блокирование всплывающих окон
        Map <String, Object> prefs = new HashMap <>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver(options);

        //Установка времени ожидания загрузки страницы
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    }

    public static void shutDown() {
        driver.quit();
    }

    @Step("Пользователь переходит на главную страницу по url: {0}")
    public void openSite(String url) {
        //Разворачиваем окно во весь экран (или fullscreen)
        driver.manage().window().maximize();
        driver.get(url);
    }

    @Step("Пользователь делает скриншот")
    public void performScreenShot() {
        Attach.makeScreenshot();
    }
}
