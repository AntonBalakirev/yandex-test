package pojo;

import pojo.global.Global;
import pojo.manufacturers.Manufacturers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Parameters")
@XmlAccessorType(XmlAccessType.FIELD)
public class Parameters {
    @XmlElement(name = "Global")
    private Global global;
    @XmlElement(name = "Manufacturers")
    private Manufacturers manufacturers;

    public Parameters(){}
    public Parameters(Global global, Manufacturers manufacturers){
        this.global = global;
        this.manufacturers = manufacturers;
    }

    public Global getGlobal() {
        return global;
    }

    public void setGlobal(Global global) {
        this.global = global;
    }

    public Manufacturers getManufacturers() {
        return manufacturers;
    }

    public void setManufacturers(Manufacturers manufacturers) {
        this.manufacturers = manufacturers;
    }

    @Override
    public String toString() {
        return "Parameters{" +
                "\n Global=" + global + "\n" +
                ",\n Manufacturers=" + manufacturers + "\n" +
                '}';
    }
}
