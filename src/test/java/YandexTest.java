import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pojo.manufacturers.Manufacturer;
import steps.*;
import utils.SortingOrder;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class YandexTest {

    private XMLSteps xmlSteps = new XMLSteps();
    private BaseSteps baseSteps = new BaseSteps();
    private MainPageSteps mainPageSteps = new MainPageSteps();
    private ComputersPageSteps computersPageSteps = new ComputersPageSteps();
    private NotebooksPageSteps notebooksPageSteps = new NotebooksPageSteps();
    private ProductPageSteps productPageSteps = new ProductPageSteps();

    @Before
    public void setUp() {
        BaseSteps.startUp();
    }

    @Test
    @DisplayName("Yandex Market тест")
    public void yandexTest() throws JAXBException, InterruptedException, IOException {
        //1.1
        xmlSteps.initializeParametersfromXMLwithRate("notebooks.xml", 10);
        //1.2
        baseSteps.openSite("https://market.yandex.ru");
        //1.3
        mainPageSteps.selectNewRegionByFirstLetters("Сан", "Санкт-Петербург");
        //1.4
        mainPageSteps.selectGoodsCategory("Компьютерная техника");
        //1.5
        computersPageSteps.selectGoodsCategory("Ноутбуки");
        for (Manufacturer manufacturer : XMLSteps.parameters.getManufacturers().getManufacturersList()) {
            if (manufacturer.getProducts().contains("notebook")) {
                //1.6
                notebooksPageSteps.selectManufacturer(manufacturer.getName());
                //1.7 + 1.8
                notebooksPageSteps.inputMinAndMaxPrice(
                        manufacturer.getPriceLimit().getMin(),
                        manufacturer.getPriceLimit().getMax());
                //1.9
                notebooksPageSteps.selectItemsAmount(12);
                //1.10

                //1.11
                notebooksPageSteps.sortItemsByPrice(SortingOrder.DESC);
                //1.12
                notebooksPageSteps.selectProductByOrder(3);
                //1.13
                productPageSteps.checkManufacturer(manufacturer.getName());
                //1.14
                productPageSteps.selectProductTab("Характеристики");
                //1.15
                baseSteps.performScreenShot();
                //1.16
                productPageSteps.saveParameter(manufacturer.getName(), "Вес");
                productPageSteps.saveParameter(manufacturer.getName(), "Диагональ");

                productPageSteps.returnByBreadCrumbsOnStepAmount(1);
            }
        }
        baseSteps.attachResult();
    }

    @After
    public void tearDown() {
        BaseSteps.shutDown();
    }
}
