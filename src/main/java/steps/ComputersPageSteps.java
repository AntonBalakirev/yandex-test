package steps;

import io.qameta.allure.Step;
import pages.ComputersPage;

public class ComputersPageSteps extends BaseSteps {

    @Step("Пользователь выбирает категорию товаров - \"{0}\"")
    public ComputersPageSteps selectGoodsCategory(String goodsCategory) {
        new ComputersPage().chooseCategory(goodsCategory);
        return this;
    }

}
