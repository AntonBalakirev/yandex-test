package pages;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.JUnitSoftAssertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Stash;

import java.util.List;

public class ProductPage extends BasePage {

    String manufacturerBreadCrumb = "//ul[@id='n-breadcrumbs']//a[@title='%s']";

    String productTab = "//a[contains(text(), '%s')]/parent::li";

    String parameterValue = "//span[contains(text(), 'Вес')]/parent::dt/following-sibling::dd/span";

    String breadCrumbs = "//li[contains(@class, 'n-breadcrumbs')]/a";

    public ProductPage(){
        waitForLoad(allCategoryButtonLocator);
    }

    public void checkManufacturer(String manufacturerName) {
        boolean manufacturerIsRight = driver.findElements(By.xpath(String.format(manufacturerBreadCrumb, manufacturerName))).size() > 0;
        Assert.assertTrue(
                "Производитель отображается неверно",
                manufacturerIsRight
        );
    }

    public void selectProductTab(String tab) {
        driver.findElement(By.xpath(String.format(productTab, tab))).click();
    }

    public void saveParameter(String manufacturerName, String parameter) {
        if(parameter.equals("Вес")){
            SoftAssertions softly = new SoftAssertions();
            softly.assertThat(
                    driver.findElement(By.xpath(String.format(parameterValue, parameter))).isDisplayed()
            ).isTrue();
        }
        scrollToElement(driver.findElement(By.xpath(String.format(parameterValue, parameter))));
        Stash.putStash(manufacturerName, parameter, driver.findElement(By.xpath(String.format(parameterValue, parameter))).getText());
    }

    public void returnByBreadCrumbsOnStepAmount(int steps) {
        List<WebElement> breadCrumbsList = driver.findElements(By.xpath(breadCrumbs));
        breadCrumbsList.get(breadCrumbsList.size() - 1 - steps).click();
    }
}
