import enums.SortingOrder;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pojo.manufacturers.Manufacturer;
import steps.*;

import javax.xml.bind.JAXBException;
import java.io.IOException;

import static enums.Characteristics.DIAGONAL;
import static enums.Characteristics.WEIGHT;
import static enums.GoodsCategories.COMPUTERS;
import static enums.GoodsCategories.NOTEBOOKS;
import static enums.ProductTabs.CHARACHTERISTICS;
import static steps.XMLSteps.parameters;

public class YandexTest {

    private XMLSteps xmlSteps = new XMLSteps();
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
    public void yandexTest() throws JAXBException, IOException, InterruptedException {
        xmlSteps.initializeParametersfromXMLwithRate("parameters_1.xml", 10);
        techSteps.openSite("https://market.yandex.ru");
        mainPageSteps.selectNewRegionByFirstLetters("Сан", "Санкт-Петербург")
                .selectGoodsCategory(COMPUTERS);
        computersPageSteps.selectGoodsCategory(NOTEBOOKS);
        for (Manufacturer manufacturer : parameters.getManufacturers().getManufacturersList()) {
            if (manufacturer.getProducts().contains("notebook")) {
                notebooksPageSteps.selectManufacturer(manufacturer.getName())
                        .inputMinAndMaxPrice(
                                manufacturer.getPriceLimit().getMin(),
                                manufacturer.getPriceLimit().getMax())
                        .selectItemsAmount(12)
                        .setShopsRatingFrom(parameters.getGlobal().getExcludedVendors().getRating())
                        .markAllShopsExcept(parameters.getGlobal().getExcludedVendors().getVendors())
                        .sortItemsByPrice(SortingOrder.DESC)
                        .selectProductByOrder(3);
                productPageSteps.checkManufacturer(manufacturer.getName())
                        .selectProductTab(CHARACHTERISTICS);
                techSteps.performScreenShot();
                productPageSteps.saveParameter(manufacturer.getName(), WEIGHT)
                        .saveParameter(manufacturer.getName(), DIAGONAL)
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
