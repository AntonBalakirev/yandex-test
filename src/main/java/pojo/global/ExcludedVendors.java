package pojo.global;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "Excluded_vendors")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExcludedVendors {
    @XmlAttribute(name = "rating")
    private String rating;
    @XmlElement(name = "Vendor")
    private List<String> vendors;

    public ExcludedVendors(){}
    public ExcludedVendors(String rating){
        this.rating = rating;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public List<String> getVendors() {
        return vendors;
    }

    public void setVendor(List<String> vendors) {
        this.vendors = vendors;
    }
}
