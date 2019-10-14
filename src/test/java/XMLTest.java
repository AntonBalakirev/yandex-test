
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pojo.Parameters;
import steps.XMLSteps;
import utils.XMLUtils;

import javax.xml.bind.JAXBException;

public class XMLTest {

    private XMLUtils user = new XMLUtils();
    private int byRate = 10;

    @Test()
    @DisplayName("Check XML updating")
    public void XMLmethodsCheck() throws JAXBException {
        Parameters parameters = user.unmarshallParameters("notebooks.xml");
        user.increaseParamsByRate(parameters, byRate);
        user.marshallParameters(parameters, "notebooks_updated.xml");
    }
}
