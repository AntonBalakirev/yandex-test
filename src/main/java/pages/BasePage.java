package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static steps.BaseSteps.getDriver;

abstract class BasePage {

    WebDriver driver;
    String mainHandle = "";

    BasePage() {
        driver = getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[contains(@class, 'control-hamburger')]")
    WebElement allCategoryButton;

    By allCategoryButtonLocator = By.xpath("//div[contains(@class, 'control-hamburger')]");

    @FindBy(xpath = "//div[@class='popup2__closer']")
    WebElement popupCloser;

    By popupCloserLocator = By.xpath("//div[@class='popup2__closer']");


    /**
     * Scroll до элемента
     * @param element элемент
     */
    void scrollToElement(WebElement element) {
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) driver).executeScript(
                "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));", element);
    }

    /**
     * Клик по элементу
     * @param element элемент
     */
    void click(WebElement element) {
        element.click();
    }

    /**
     * Ожидание момента, когда по элементу возможно зделать клик
     * @param element элемент
     */
    void waitToBeClickable(WebElement element) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Ожидание момента, когда элемент становится видимым
     * @param element элемент
     */
    void waitForVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.pollingEvery(Duration.ofMillis(300))
                .until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Ожидание загрузки элемента
     * @param locator локатор элемента
     */
    void waitForLoad(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.pollingEvery(Duration.ofMillis(300))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    void waitForElementEnabled(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 20);
            wait.pollingEvery(Duration.ofMillis(300))
                    .until((ExpectedCondition<Boolean>) driver -> element.isEnabled());
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}


