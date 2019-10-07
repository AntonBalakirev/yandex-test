package pojo.manufacturers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Manufacturers")
@XmlAccessorType(XmlAccessType.FIELD)
public class Manufacturers {
    @XmlElement(name = "Manufacturer")
    private List<Manufacturer> manufacturers;

    public List<Manufacturer> getManufacturersList() {
        return manufacturers;
    }

    public void setManufacturersList(List<Manufacturer> manufacturers) {
        this.manufacturers = manufacturers;
    }

    public void add(Manufacturer manufacturer) {
        if (this.manufacturers == null) {
            this.manufacturers = new ArrayList<Manufacturer>();
        }
        this.manufacturers.add(manufacturer);
    }

    @Override
    public String toString() {
        return "Manufacturers{" +
                "\n" + manufacturers + "\n" +
                '}';
    }
}
