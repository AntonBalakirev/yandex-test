package steps;


import enums.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseSteps {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    private static String headlessProp = "";
    private static String implicityWait = "";
    public static String browserProp = "";

    private static void initDriver(WebDriver driver) {
        BaseSteps.driver = driver;
    }

    public static void startUp() {
        try {
            Properties appProps = new Properties();
            appProps.load(new FileInputStream("src/test/resources/application.properties"));
            headlessProp = appProps.getProperty("headless.run");
            browserProp = appProps.getProperty("browser");
            implicityWait = appProps.getProperty("implicitly.page.load.wait");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (Browser.valueOf(browserProp).equals(Browser.CHROME)) {
            System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.setHeadless(Boolean.parseBoolean(headlessProp));
            initDriver(new ChromeDriver(options));
        } else if (Browser.valueOf(browserProp).equals(Browser.IE)){
            System.setProperty("webdriver.ie.driver", "drivers/IEDriverServer.exe");
            InternetExplorerOptions options = new InternetExplorerOptions();
            options.ignoreZoomSettings();
            options.setCapability("requireWindowFocus", false);
            initDriver(new InternetExplorerDriver(options));
        }
        //Установка времени ожидания загрузки страницы
        getDriver().manage().timeouts().pageLoadTimeout(Integer.parseInt(implicityWait), TimeUnit.SECONDS);
    }

    public static void shutDown() {
        if(browserProp.equals("IE")){
            try {
                Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe").waitFor();
            } catch (IOException | InterruptedException e) {
                throw new Error("There is a problem of killing IE", e);
            }
        } else {
            getDriver().quit();
        }
    }
}
