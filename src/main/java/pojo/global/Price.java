package pojo.global;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Price")
@XmlAccessorType(XmlAccessType.FIELD)
public class Price {
    @XmlElement(name = "Max")
    private String max;

    public Price() {
    }

    public Price(String max) {
        this.max = max;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return "Price{" +
                "\n Max=" + max + "\n" +
                '}';
    }
}
