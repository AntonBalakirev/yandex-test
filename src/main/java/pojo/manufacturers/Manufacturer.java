package pojo.manufacturers;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Manufacturer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Manufacturer {
    @XmlAttribute(name = "products")
    private String products;
    @XmlElement(name = "Name")
    private String name;
    @XmlElement(name = "PriceLimit")
    private PriceLimit priceLimit;

    public Manufacturer() {
    }

    public Manufacturer(String products, String name, PriceLimit priceLimit) {
        this.products = products;
        this.name = name;
        this.priceLimit = priceLimit;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PriceLimit getPriceLimit() {
        return priceLimit;
    }

    public void setPriceLimit(PriceLimit priceLimit) {
        this.priceLimit = priceLimit;
    }

    @Override
    public String toString() {
        return "Manufacturer{" +
                "\n Products=" + products + "\n" +
                ",\n Name=" + name + "\n" +
                ",\n PriceLimit=" + priceLimit + "\n" +
                '}';
    }
}
