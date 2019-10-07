package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {

    @FindBy(xpath = "//span[contains(@class, 'item_type_region')]")
    WebElement regionButton;

    String CategoryItemLink = "//span[contains(text(), '%s')]/../parent::div";

    @FindBy(xpath = "//div[contains(@class, 'region-select-form')]//input[@class='input__control']")
    WebElement regionInput;

    @FindBy(xpath = "//button[contains(@class, 'region-select-form')]")
    WebElement regionConfirmButton;

    String regionItem = "//span[contains(text(), '%s')]/ancestor::div[contains(@class, 'region-suggest__list-item')]";

    public MainPage(){
        waitForLoad(allCategoryButtonLocator);
    }

    public void selectNewRegionByFirstLetters(String firstLetters, String region) {
        regionButton.click();
        regionInput.clear();
        regionInput.sendKeys(firstLetters);
        waitForLoad(By.xpath(String.format(regionItem, region)));
        waitToBeClickable(driver.findElement(By.xpath(String.format(regionItem, region))));
        driver.findElement(By.xpath(String.format(regionItem, region))).click();
        regionConfirmButton.click();
    }

    public void chooseCategory(String category) {
        waitForLoad(popupCloserLocator);
        waitForVisibility(popupCloser);
        popupCloser.click();

        waitForVisibility(driver.findElement(By.xpath(String.format(CategoryItemLink, category))));
        driver.findElement(By.xpath(String.format(CategoryItemLink, category))).click();
    }

}
