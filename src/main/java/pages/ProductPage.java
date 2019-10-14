package pages;

import org.assertj.core.api.JUnitSoftAssertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Attach;
import utils.Stash;

import java.util.List;

public class ProductPage extends BasePage {

    @FindBy(xpath = "//h1[contains(@class, 'title')]")
    private WebElement productTitle;

    String productTab = "//a[contains(text(), '%s')]/parent::li";

    String parameterValue = "//span[contains(text(), '%s')]/parent::dt/following-sibling::dd/span";

    String breadCrumbs = "//li[contains(@class, 'n-breadcrumbs')]/a";

    public ProductPage(){
        waitForLoad(allCategoryButtonLocator);
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

    public void selectProductTab(String tab) {
        driver.findElement(By.xpath(String.format(productTab, tab))).click();
    }

    public void saveParameter(String manufacturerName, String parameter) {
        if(parameter.equals("Вес")){
            if(driver.findElements(By.xpath(String.format(parameterValue, parameter))).size() > 0){
                saveParamToStash(manufacturerName, parameter);
            } else {
                JUnitSoftAssertions softly = new JUnitSoftAssertions();
                softly.assertThat(
                        driver.findElements(By.xpath(String.format(parameterValue, parameter))).size() > 0
                ).isTrue();
                Attach.makeScreenshot();
            }
        } else {
            saveParamToStash(manufacturerName, parameter);
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
