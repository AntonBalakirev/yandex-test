package steps;

import io.qameta.allure.Step;
import pojo.Parameters;
import utils.XMLUtils;

import javax.xml.bind.JAXBException;

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
