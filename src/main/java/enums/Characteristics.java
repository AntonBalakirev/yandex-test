package enums;

public enum Characteristics {
    WEIGHT("Вес"),
    DIAGONAL("Экран");

    private String textValue;

    Characteristics(String textValue){
        this.textValue = textValue;
    }

    public String getTextValue() {
        return textValue;
    }
}
