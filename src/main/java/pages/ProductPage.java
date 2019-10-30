package pages;

import enums.Characteristics;
import enums.ProductTabs;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Attach;
import utils.Stash;

import java.util.List;

public class ProductPage extends BasePage {

    @FindBy(xpath = "//h1[contains(@class, 'title')]")
    private WebElement productTitle;

    String productTab = "//a[contains(text(), '%s')]/parent::li";
    By allCharacteristicsLocator = By.xpath("//a[contains(text(), 'Характеристики')]/parent::li");

    String parameterValue = "//span[contains(text(), '%s')]/parent::dt/following-sibling::dd/span";

    String breadCrumbs = "//li[contains(@class, 'n-breadcrumbs')]/a";

    public ProductPage() {
        waitForLoad(allCharacteristicsLocator);
    }

    public void checkManufacturer(String manufacturerName) {
        waitForVisibility(productTitle);
        Assert.assertTrue(
                "Производитель отображается неверно" +
                        "\nactual: " + productTitle.getText() +
                        "\nshould contain: " + manufacturerName,
                productTitle.getText().contains(manufacturerName)
        );
    }

    public void selectProductTab(ProductTabs tab) {
        driver.findElement(By.xpath(String.format(productTab, tab.getTextValue()))).click();
    }

    public void saveParameter(String manufacturerName, Characteristics parameter) throws InterruptedException {
        if (driver.findElements(By.xpath(String.format(parameterValue, parameter.getTextValue()))).size() > 0) {
            saveParamToStash(manufacturerName, parameter.getTextValue());
        } else {
            JUnitSoftAssertions softly = new JUnitSoftAssertions();
                Allure.step("Soft asserts", Status.BROKEN);
            softly.assertThat(
                    driver.findElements(By.xpath(String.format(parameterValue, parameter.getTextValue()))).size() > 0
            ).isTrue();
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].style.border='2px solid red'", driver.findElement(By.xpath(String.format(parameterValue, parameter.getTextValue()))));
            Thread.sleep(2000);
            Attach.makeScreenshot();
        }
    }

    private void saveParamToStash(String manufacturerName, String parameter) {
        WebElement parameterValueElement = driver.findElement(By.xpath(String.format(parameterValue, parameter)));
        waitForElementEnabled(parameterValueElement);
        scrollToElement(parameterValueElement);
        String stringParam = parameterValueElement.getText().trim();
        Stash.putStash(manufacturerName, parameter, stringParam.substring(0, stringParam.indexOf(" ")));
    }

    public void returnByBreadCrumbsOnStepAmount(int steps) {
        List<WebElement> breadCrumbsList = driver.findElements(By.xpath(breadCrumbs));
        scrollToElement(breadCrumbsList.get(breadCrumbsList.size() - 1 - steps));
        breadCrumbsList.get(breadCrumbsList.size() - 1 - steps).click();
    }
}
