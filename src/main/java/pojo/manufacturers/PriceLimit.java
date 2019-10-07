package pojo.manufacturers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Pricelimit")
@XmlAccessorType(XmlAccessType.FIELD)
public class PriceLimit {
    @XmlElement(name = "Min")
    private String min;
    @XmlElement(name = "Max")
    private String max;

    public PriceLimit(){}

    public PriceLimit(String min, String max){
        this.min = min;
        this.max = max;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return "PriceLimit{" +
                "\n Min=" + min + "\n" +
                ",\n Max=" + max + "\n" +
                '}';
    }
}
