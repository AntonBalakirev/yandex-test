package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import utils.Attach;
import utils.FileUtils;
import utils.Stash;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TechnicalSteps {
    @Step("Пользователь переходит на главную страницу по url: {0}")
    public void openSite(String url) {
        //Разворачиваем окно во весь экран (или fullscreen)
        WebDriver driver = BaseSteps.getDriver();
        driver.manage().window().maximize();
        driver.get(url);
    }

    @Step("Пользователь делает скриншот")
    public void performScreenShot() {
        Attach.makeScreenshot();
    }

    @Step("Результат теста")
    public void summarizeResult() throws IOException {
        Map.Entry<String, HashMap<String, Object>> maxEntry = null;
        for (Map.Entry<String, HashMap<String, Object>> mapEntry : Stash.getStash().entrySet()) {
            double diagonalValue = Double.parseDouble((String) mapEntry.getValue().get("Диагональ"));
            if(maxEntry == null ||
                    diagonalValue > Double.parseDouble((String) maxEntry.getValue().get("Диагональ"))){
                maxEntry = mapEntry;
            } else if (diagonalValue == Double.parseDouble((String) maxEntry.getValue().get("Диагональ"))) {
                if (mapEntry.getValue().get("Вес") != null &&
                        Double.parseDouble((String) mapEntry.getValue().get("Вес")) < Double.parseDouble((String) maxEntry.getValue().get("Вес"))) {
                    maxEntry = mapEntry;
                }
            }
        }
        FileUtils.writeResultsToFile(maxEntry, "result_output.txt");
        Attach.getTextFiles("result_output.txt");
    }
}
