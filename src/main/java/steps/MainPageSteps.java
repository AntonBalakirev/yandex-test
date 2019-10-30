package steps;

import enums.GoodsCategories;
import io.qameta.allure.Step;
import pages.MainPage;

public class MainPageSteps extends BaseSteps {

    @Step("Пользователь выбирает регион по первым трем символам - \"{0}\"")
    public MainPageSteps selectNewRegionByFirstLetters(String firstLetters, String region) throws InterruptedException {
        new MainPage().selectNewRegionByFirstLetters(firstLetters, region);
        return this;
    }

    @Step("Пользователь выбирает категорию товаров - \"{0}\"")
    public MainPageSteps selectGoodsCategory(GoodsCategories goodsCategory) throws InterruptedException {
        new MainPage().chooseCategory(goodsCategory);
        return this;
    }
}
