import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pojo.manufacturers.Manufacturer;
import steps.*;
import enums.SortingOrder;

import javax.xml.bind.JAXBException;
import java.io.IOException;

import static steps.XMLSteps.parameters;

public class YandexTest {

    private XMLSteps xmlSteps = new XMLSteps();
    private BaseSteps baseSteps = new BaseSteps();
    private MainPageSteps mainPageSteps = new MainPageSteps();
    private ComputersPageSteps computersPageSteps = new ComputersPageSteps();
    private NotebooksPageSteps notebooksPageSteps = new NotebooksPageSteps();
    private ProductPageSteps productPageSteps = new ProductPageSteps();
    private TechnicalSteps techSteps = new TechnicalSteps();

    @Before
    public void setUp() {
        BaseSteps.startUp();
    }

    @Test
    @DisplayName("Yandex Market тест")
    public void yandexTest() throws JAXBException, InterruptedException, IOException {
        xmlSteps.initializeParametersfromXMLwithRate("notebooks.xml", 10);
        techSteps.openSite("https://market.yandex.ru");
        mainPageSteps.selectNewRegionByFirstLetters("Сан", "Санкт-Петербург")
                .selectGoodsCategory("Компьютерная техника");
        computersPageSteps.selectGoodsCategory("Ноутбуки");
        for (Manufacturer manufacturer : parameters.getManufacturers().getManufacturersList()) {
            if (manufacturer.getProducts().contains("notebook")) {
                notebooksPageSteps.selectManufacturer(manufacturer.getName())
                        .inputMinAndMaxPrice(
                                manufacturer.getPriceLimit().getMin(),
                                manufacturer.getPriceLimit().getMax())
                        .selectItemsAmount(12)
                        .setShopsRatingFrom(parameters.getGlobal().getExcludedVendors().getRating())
//                        .markAllShopsExcept(parameters.getGlobal().getExcludedVendors().getVendors())
                        .sortItemsByPrice(SortingOrder.DESC)
                        .selectProductByOrder(3);
                productPageSteps.checkManufacturer(manufacturer.getName())
                        .selectProductTab("Характеристики");
                techSteps.performScreenShot();
                productPageSteps.saveParameter(manufacturer.getName(), "Вес")
                        .saveParameter(manufacturer.getName(), "Диагональ")
                        .returnByBreadCrumbsOnStepAmount(1);
            }
        }
        techSteps.summarizeResult();
    }

    @After
    public void tearDown() {
        BaseSteps.shutDown();
    }
}
