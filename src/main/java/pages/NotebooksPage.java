package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import steps.XMLSteps;
import enums.SortingOrder;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class NotebooksPage extends BasePage {

    @FindBy(xpath = "//input[@name='Цена от']")
    WebElement minPriceInput;

    @FindBy(xpath = "//input[@name='Цена до']")
    WebElement maxPriceInput;

    String brand = "//span[text()='%s']";

    @FindBy(xpath = "//button[contains(@class, 'button_arrow_down button_size_s')]")
    WebElement amountButton;

    String amountList = "//span[contains(@class, 'select__text') and contains(text(), 'Показывать по %s')]/parent::div";

    @FindBy(xpath = "//a[contains(text(), 'по цене')]/parent::div")
    WebElement priceSortingButton;

    String itemsList = "//div[contains(@class, 'n-snippet-list')]//div[contains(@class, 'title')]/a";

    String ratingShopCheckbox = "//input[@name='Рейтинг магазина' and @id='qrfrom_%s']";

    @FindBy(xpath = "//legend[text()='Магазины']/following::button[text()='Показать всё']")
    WebElement showAllShopsButton;

    public void selectManufacturer(String brand) {
        waitForLoad(By.xpath(String.format(this.brand, brand)));
        scrollToElement(driver.findElement(By.xpath(String.format(this.brand, brand))));
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
        Actions actions = new Actions(driver);
        do {
            actions.moveToElement(priceSortingButton).click().build().perform();
        } while(priceSortingButton.getAttribute("class").contains(order.order));
    }

    public void selectProductByOrder(int orderNumber) throws InterruptedException {
        List<WebElement> productsList = driver.findElements(By.xpath(itemsList));
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        waitForLoad(By.xpath(itemsList));
        waitForElementEnabled(driver.findElements(By.xpath(itemsList)).get(orderNumber - 1));
        Thread.sleep(2000);
        scrollToElement(driver.findElements(By.xpath(itemsList)).get(orderNumber - 1));
        Thread.sleep(2000);
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElements(By.xpath(itemsList)).get(orderNumber - 1)).click().build().perform();
    }

    public void setShopsRatingFrom(String rating) {
        WebElement ratingShopCheckboxElement = driver.findElement(By.xpath(String.format(ratingShopCheckbox, rating)));
        scrollToElement(ratingShopCheckboxElement);
        click(ratingShopCheckboxElement);
    }

    public void markAllShopsExcept(List<String> vendors) {
        scrollToElement(showAllShopsButton);
        waitForVisibility(showAllShopsButton);
        click(showAllShopsButton);
        //mark all shops except excluded vendors
    }
}
