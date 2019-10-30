package pages;

import enums.SortingOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import steps.XMLSteps;
import utils.Stash;

import java.util.List;
import java.util.Set;
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

    //    String itemsList = "//div[@class='n-snippet-card2__title']/a";
    String itemsList = "//div[contains(@class, 'n-snippet-list')]//div[contains(@class, 'title')]/a";

    String ratingShopCheckbox = "//label[@for='qrfrom_%s']";

    @FindBy(xpath = "//legend[text()='Магазины']/following::button[text()='Показать всё']")
    WebElement showAllShopsButton;

    @FindBy(xpath = "//legend[contains(text(), 'Производитель')]/following-sibling::footer/button[contains(text(), 'Показать всё')]")
    WebElement showAllManufacturersButton;

    public void selectManufacturer(String brand) throws NoSuchFieldException, InterruptedException {
        click(showAllManufacturersButton);
        if (!Stash.getManufacturer().isEmpty()) {
            clickManyfacturerCheckBox(Stash.getManufacturer());
        }
        Thread.sleep(3000);
        if(driver.findElements(By.xpath(String.format(this.brand, brand))).size() > 0) {
            clickManyfacturerCheckBox(brand);
        } else {
            throw new NoSuchFieldException("There is no manufacturer");
        }
        Stash.setManufacturer(brand);
    }

    private void clickManyfacturerCheckBox(String brand) {
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

    public void selectItemsAmount(Integer amount) {
        scrollToElement(amountButton);
        amountButton.click();
        driver.findElement(By.xpath(String.format(amountList, amount))).click();
    }

    public void sortItemsByPrice(SortingOrder order) {
        scrollToElement(priceSortingButton);
        Actions actions = new Actions(driver);
        do {
            actions.moveToElement(priceSortingButton).click().build().perform();
        } while (priceSortingButton.getAttribute("class").contains(order.order));
    }

    public void selectProductByOrder(int orderNumber) throws InterruptedException {
        mainHandle = driver.getWindowHandle();
        waitForLoad(By.xpath(itemsList));
        waitForElementEnabled(driver.findElements(By.xpath(itemsList)).get(orderNumber - 1));
        Thread.sleep(5000);
        scrollToElement(driver.findElements(By.xpath(itemsList)).get(orderNumber - 1));
        Thread.sleep(5000);
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElements(By.xpath(itemsList)).get(orderNumber - 1)).click().build().perform();
        Set<String> winHandles = driver.getWindowHandles();
        Thread.sleep(5000);
        if (winHandles.size() > 1) {
            for (String winHandle : winHandles) {
                if (!winHandle.equals(mainHandle)) {
                    driver.switchTo().window(winHandle);
                }
            }
        }
    }

    public void setShopsRatingFrom(String rating) throws InterruptedException {
        WebElement ratingShopCheckboxElement = driver.findElement(By.xpath(String.format(ratingShopCheckbox, rating)));
        Thread.sleep(2000);
        scrollToElement(ratingShopCheckboxElement);
        Thread.sleep(2000);
        click(ratingShopCheckboxElement);
    }

    public void markAllShopsExcept(List<String> vendors) {
        scrollToElement(showAllShopsButton);
        waitForVisibility(showAllShopsButton);
        click(showAllShopsButton);
        //mark all shops except excluded vendors
//        HashMap<String, Boolean> clicked = new HashMap<>();
//        while (true) {
//            AtomicBoolean finished = new AtomicBoolean(true);
//            List<WebElement> elements = driver.findElements(By.cssSelector("[data-zone-name=\"search-filter\"] [data-autotest-id=\"fesh\"] label"));
//            try {
//                elements.forEach(el -> {
//                    try {
//                        WebElement input = null;
//                        String labelFor = el.getAttribute("for");
//                        if (labelFor.equals("fesh-suggester")) {
//                            return;
//                        }
//                        try {
//                            input = el.findElement(By.cssSelector("input[type=checkbox]"));
//                        } catch (Exception e) {
//                            return;
//                        }
//                        if (input == null) {
//                            return;
//                        }
//                        String name = input.getAttribute("name");
//                        if (clicked.containsKey(name)) {
//                            return;
//                        }
//                        clicked.put(name, true);
//                        for (String shop : except) {
//                            if (name.toLowerCase().contains(shop.toLowerCase())) {
//                                return;
//                            }
//                        }
//                        finished.set(false);
//                        el.findElement(By.cssSelector("div")).click();
//                        ((JavascriptExecutor) driver).executeScript("document.querySelector('[for=" + labelFor + "]').scrollIntoView({block: 'center'})");
//                    } catch (Exception ignored) {
//                    }
//                });
//            } catch (Exception ignored) {
//
//            }
//            if (finished.get()) {
//                break;
//            }
//        }
    }
}
