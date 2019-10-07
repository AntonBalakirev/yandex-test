package steps;

import io.qameta.allure.Step;
import pojo.Parameters;
import pojo.manufacturers.Manufacturer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class XMLSteps extends BaseSteps {

    public static Parameters parameters;

    @Step("Пользователь берет значения из XML \"{0}\" и увеличивает на порядок \"{1}\"")
    public XMLSteps initializeParametersfromXMLwithRate(String path, Integer rate) throws JAXBException {
        if (parameters == null) {
            unmarshallStaticParameters(path);
            increaseParamsByRate(parameters, rate);
        }
        return this;
    }

    public void marshallParameters(Parameters parameters, String path) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Parameters.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        marshaller.marshal(parameters, new File(path));
    }

    public Parameters unmarshallParameters(String path) throws JAXBException {
        File file = new File(path);
        JAXBContext inputContext = JAXBContext.newInstance(Parameters.class);
        Unmarshaller unmarshaller = inputContext.createUnmarshaller();
        return (Parameters) unmarshaller.unmarshal(file);
    }

    private void unmarshallStaticParameters(String path) throws JAXBException {
        XMLSteps.parameters = unmarshallParameters(path);
    }

    public void increaseParamsByRate(Parameters parameters, int rate) {
        int max = Integer.parseInt(parameters.getGlobal().getPrice().getMax());
        parameters.getGlobal().getPrice().setMax(String.valueOf(max * rate));

        List<Manufacturer> manufacturersList = parameters.getManufacturers().getManufacturersList();
        for (Manufacturer manufacturer : manufacturersList) {
            int maxPrice = Integer.parseInt(manufacturer.getPriceLimit().getMax());
            manufacturer.getPriceLimit().setMax(String.valueOf(maxPrice * rate));

            int minPrice = Integer.parseInt(manufacturer.getPriceLimit().getMin());
            manufacturer.getPriceLimit().setMin(String.valueOf(minPrice * rate));
        }
    }
}
