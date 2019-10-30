package steps;

import io.qameta.allure.Step;
import pages.NotebooksPage;
import enums.SortingOrder;

import java.util.List;

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
    public NotebooksPageSteps selectProductByOrder(int orderNumber) throws InterruptedException {
        new NotebooksPage().selectProductByOrder(orderNumber);
        return this;
    }

    @Step("Пользователь устанавливает рейтинг магазина от - \"{0}\"")
    public NotebooksPageSteps setShopsRatingFrom(String rating) throws InterruptedException {
        new NotebooksPage().setShopsRatingFrom(rating);
        return this;
    }

    @Step("Пользователь отмечает все магазины кроме указанных- \"{0}\"")
    public NotebooksPageSteps markAllShopsExcept(List<String> vendors) {
        new NotebooksPage().markAllShopsExcept(vendors);
        return this;
    }
}
