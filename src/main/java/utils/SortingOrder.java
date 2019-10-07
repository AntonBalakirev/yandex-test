package utils;

public enum SortingOrder {
    ASC("asc"),
    DESC("desc");

    public final String order;

    SortingOrder(String order) {
        this.order = order;
    }
}
