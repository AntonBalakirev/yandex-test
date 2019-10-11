package steps;


import utils.Attach;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.Stash;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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

    @Step("Производится расчет результата")
    public void attachResult() throws IOException {
        Map.Entry<String, HashMap<String, Object>> maxEntry = null;
        for (Map.Entry<String, HashMap<String, Object>> mapEntry : Stash.getStash().entrySet()) {
            double diagonalValue = Double.parseDouble((String) mapEntry.getValue().get("Диагональ"));
            if(maxEntry == null ||
                    diagonalValue > Double.parseDouble((String) maxEntry.getValue().get("Диагональ"))){
                maxEntry = mapEntry;
            } else if (diagonalValue == Double.parseDouble((String) maxEntry.getValue().get("Диагональ"))) {
                if (mapEntry.getValue().get("Вес") != null &&
                        Double.parseDouble((String) mapEntry.getValue().get("Вес")) > Double.parseDouble((String) maxEntry.getValue().get("Вес"))) {
                    maxEntry = mapEntry;
                }
            }
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter("result_output.txt"));
        writer.write(maxEntry.toString());
        writer.close();
    }
}
