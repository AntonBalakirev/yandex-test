
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pojo.Parameters;
import steps.XMLSteps;

import javax.xml.bind.JAXBException;

public class XMLTest {

    private XMLSteps user = new XMLSteps();
    private int byRate = 10;

    @Test()
    @DisplayName("Check XML updating")
    public void XMLmethodsCheck() throws JAXBException {
        Parameters parameters =
                user.unmarshallParameters("notebooks.xml");
        user.increaseParamsByRate(parameters, byRate);
        user.marshallParameters(parameters, "notebooks_updated.xml");
    }

}
