package pages;

import enums.GoodsCategories;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Properties;
import java.util.Set;

import static steps.BaseSteps.baseurl;
import static steps.BaseSteps.browserProp;

public class MainPage extends BasePage {

    @FindBy(xpath = "//span[contains(@class, 'item_type_region')]")
    WebElement regionButton;

    String CategoryItemLink = "//span[contains(text(), '%s')]/../parent::div";

    @FindBy(xpath = "//div[contains(@class, 'region-select-form')]//input[@class='input__control']")
    WebElement regionInput;

    @FindBy(xpath = "//button[contains(@class, 'region-select-form')]")
    WebElement regionConfirmButton;

    String regionItem = "//span[contains(text(), '%s')]/ancestor::div[contains(@class, 'region-suggest__list-item')]";

    public MainPage() {
        waitForLoad(allCategoryButtonLocator);
    }

    public void selectNewRegionByFirstLetters(String firstLetters, String region) throws InterruptedException {
        regionButton.click();
        regionInput.clear();
        regionInput.sendKeys(firstLetters);
        waitForLoad(By.xpath(String.format(regionItem, region)));
        waitToBeClickable(driver.findElement(By.xpath(String.format(regionItem, region))));
        driver.findElement(By.xpath(String.format(regionItem, region))).getSize();
        driver.findElement(By.xpath(String.format(regionItem, region))).click();
        Thread.sleep(1000);
        regionConfirmButton.getSize();
        regionConfirmButton.click();

        if(browserProp.equals("IE")){
            Thread.sleep(5000);
            driver.get(baseurl);
        }
    }

    public void chooseCategory(GoodsCategories category) throws InterruptedException {
        Thread.sleep(2000);
        if(popupCloser.isDisplayed()) {
            popupCloser.click();
        }
        Thread.sleep(1000);
        waitForVisibility(driver.findElement(By.xpath(String.format(CategoryItemLink, category.getTextValue()))));
        driver.findElement(By.xpath(String.format(CategoryItemLink, category.getTextValue()))).click();
    }

}
