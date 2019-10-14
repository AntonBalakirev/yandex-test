package enums;

public enum Browser {
    CHROME("chrome"),
    IE("ie");

    public final String type;

    Browser(String type){
        this.type = type;
    }
}
