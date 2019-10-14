package steps;

import io.qameta.allure.Step;
import pojo.Parameters;
import pojo.manufacturers.Manufacturer;
import utils.XMLUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class XMLSteps extends BaseSteps {

    public static Parameters parameters;
    private XMLUtils xmlUtils = new XMLUtils();

    @Step("Пользователь берет значения из XML \"{0}\" и увеличивает на порядок \"{1}\"")
    public XMLSteps initializeParametersfromXMLwithRate(String path, Integer rate) throws JAXBException {
        if (parameters == null) {
            xmlUtils.unmarshallStaticParameters(path);
            xmlUtils.increaseParamsByRate(parameters, rate);
        }
        return this;
    }
}
