package enums;

public enum GoodsCategories {
    COMPUTERS("Компьютерная техника"),
    NOTEBOOKS("Ноутбуки");

    private String textValue;

    GoodsCategories(String category){
        this.textValue = category;
    }

    public String getTextValue() {
        return textValue;
    }
}
