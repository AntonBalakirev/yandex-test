package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import steps.XMLSteps;
import utils.SortingOrder;

import java.util.List;

public class NotebooksPage extends BasePage {

    @FindBy(xpath = "//input[@name='Цена от']")
    WebElement minPriceInput;

    @FindBy(xpath = "//input[@name='Цена до']")
    WebElement maxPriceInput;

//    String brand = "//input[contains(@name, '%s')]";
    String brand = "//span[text()='%s']";

    @FindBy(xpath = "//button[contains(@class, 'button_arrow_down button_size_s')]")
    WebElement amountButton;

    String amountList = "//span[contains(@class, 'select__text') and contains(text(), 'Показывать по %s')]/parent::div";

    @FindBy(xpath = "//a[contains(text(), 'по цене')]/parent::div")
    WebElement priceSortingButton;

    String itemsList = "//div[contains(@class, 'n-snippet-list')]//div[contains(@class, 'title')]/a";

    public void selectManufacturer(String brand) {
        waitForLoad(By.xpath(String.format(this.brand, brand)));
        scrollToElement(driver.findElement(By.xpath(String.format(this.brand, brand))));
//        waitToBeClickable(driver.findElement(By.xpath(String.format(this.brand, brand))), 20);
        driver.findElement(By.xpath(String.format(this.brand, brand))).click();
    }

    public NotebooksPage inputMinPrice(String minPrice) {
        minPriceInput.sendKeys(minPrice);
        return this;
    }

    public NotebooksPage inputMaxPrice(String maxPrice) {
        String keys =
                Integer.parseInt(XMLSteps.parameters.getGlobal().getPrice().getMax()) > Integer.parseInt(maxPrice)
                        ? maxPrice
                        : XMLSteps.parameters.getGlobal().getPrice().getMax();
        maxPriceInput.sendKeys(keys);
        return this;
    }

    public void selectItemsAmount(Integer amount){
        scrollToElement(amountButton);
        amountButton.click();
        driver.findElement(By.xpath(String.format(amountList, amount))).click();
    }

    public void sortItemsByPrice(SortingOrder order){
        scrollToElement(priceSortingButton);
        do {
            priceSortingButton.click();
        } while(priceSortingButton.getAttribute("class").contains(order.order));
    }

    public void selectProductByOrder(int orderNumber) {
        List<WebElement> productsList = driver.findElements(By.xpath(itemsList));
        waitForLoad(By.xpath(itemsList));
        waitForVisibility(productsList.get(orderNumber - 1));
        scrollToElement(productsList.get(orderNumber - 1));
        waitToBeClickable(productsList.get(orderNumber - 1));
        driver.findElements(By.xpath(itemsList)).get(orderNumber - 1).click();
    }
}