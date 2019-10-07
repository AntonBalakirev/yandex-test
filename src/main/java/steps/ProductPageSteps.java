package steps;

import io.qameta.allure.Step;
import pages.ProductPage;

public class ProductPageSteps extends BaseSteps {

    @Step("Пользователь проверяет производителя на соответствие ожидаемому - \"{0}\"")
    public ProductPageSteps checkManufacturer(String manufacturerName) {
        new ProductPage().checkManufacturer(manufacturerName);
        return this;
    }

    @Step("Пользователь проверяет производителя на соответствие ожидаемому - \"{0}\"")
    public ProductPageSteps selectProductTab(String tab) {
        new ProductPage().selectProductTab(tab);
        return this;
    }

    @Step("Пользователь пользователь сохраняет параметр - \"{0}\"")
    public ProductPageSteps saveParameter(String manufacturerName, String parameter) {
        new ProductPage().saveParameter(manufacturerName, parameter);
        return this;
    }

    @Step("Пользователь возвращается на \"{0}\" назад по навигационной цепочке")
    public void returnByBreadCrumbsOnStepAmount(int steps) {
        new ProductPage().returnByBreadCrumbsOnStepAmount(steps);
    }
}
