package enums;

public enum ProductTabs {
    CHARACHTERISTICS("Характеристики");

    private String textValue;

    ProductTabs(String name){
        this.textValue = name;
    }

    public String getTextValue() {
        return textValue;
    }
}
