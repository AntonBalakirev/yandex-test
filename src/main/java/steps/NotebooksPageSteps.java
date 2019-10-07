package steps;

import io.qameta.allure.Step;
import pages.NotebooksPage;
import utils.SortingOrder;

public class NotebooksPageSteps extends BaseSteps {

    @Step("Пользователь выбирает производителя - \"{0}\"")
    public NotebooksPageSteps selectManufacturer(String manufacturer) {
        new NotebooksPage().selectManufacturer(manufacturer);
        return this;
    }

    @Step("Пользователь указывает диапазон цен - от \"{0}\" до \"{1}\"")
    public NotebooksPageSteps inputMinAndMaxPrice(String min, String max) {
        new NotebooksPage()
                .inputMinPrice(min)
                .inputMaxPrice(max);
        return this;
    }

    @Step("Пользователь выбирает количество отображаемых товаров - \"{0}\"")
    public NotebooksPageSteps selectItemsAmount(Integer amount) {
        new NotebooksPage().selectItemsAmount(amount);
        return this;
    }

    @Step("Пользователь сортирует по цене в порядке - \"{0}\"")
    public NotebooksPageSteps sortItemsByPrice(SortingOrder order) {
        new NotebooksPage().sortItemsByPrice(order);
        return this;
    }

    @Step("Пользователь выбирает товар из списка под номером - \"{0}\"")
    public NotebooksPageSteps selectProductByOrder(int orderNumber) {
        new NotebooksPage().selectProductByOrder(orderNumber);
        return this;
    }
}
