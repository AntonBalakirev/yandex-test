package steps;


import enums.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseSteps {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    private static void setDriver(WebDriver driver) {
        BaseSteps.driver = driver;
    }

    public static void startUp() {
        String headlessProp = "";
        String browserProp = "";
        try {
            Properties appProps = new Properties();
            appProps.load(new FileInputStream("src/test/resources/application.properties"));
            headlessProp = appProps.getProperty("headless.run");
            browserProp = appProps.getProperty("browser");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (Browser.valueOf(browserProp).equals(Browser.CHROME)) {
            System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.setHeadless(Boolean.parseBoolean(headlessProp));
            setDriver(new ChromeDriver(options));
        } else if (Browser.valueOf(browserProp).equals(Browser.IE)){
            System.setProperty("webdriver.ie.driver", "drivers/IEDriverServer.exe");
            InternetExplorerOptions options = new InternetExplorerOptions();
            options.ignoreZoomSettings();
            setDriver(new InternetExplorerDriver(options));
        }

        //Установка времени ожидания загрузки страницы
        getDriver().manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    }

    public static void shutDown() {
        getDriver().quit();
    }
}
