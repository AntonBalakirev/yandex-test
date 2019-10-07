package pojo.global;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Global")
@XmlAccessorType(XmlAccessType.FIELD)
public class Global {
    @XmlElement(name = "Price")
    private Price price;
    @XmlElement(name = "Excluded_vendors")
    private ExcludedVendors excludedVendors;

    public Global() {
    }

    public Global(Price price, ExcludedVendors excludedVendors) {
        this.price = price;
        this.excludedVendors = excludedVendors;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public ExcludedVendors getExcludedVendors() {
        return excludedVendors;
    }

    public void setExcludedVendors(ExcludedVendors excludedVendors) {
        this.excludedVendors = excludedVendors;
    }

    @Override
    public String toString() {
        return "Price{" +
                "\n Price=" + price + "\n" +
                ",\n" + excludedVendors + "\n" +
                '}';
    }
}
