package utils;

import java.util.HashMap;
import java.util.Map;

public class Stash {

    private static Map<String, HashMap<String, Object>> stash = new HashMap();

    private static String manufacturer = "";

    public static String getManufacturer() {
        return manufacturer;
    }

    public static void setManufacturer(String manufacturer) {
        Stash.manufacturer = manufacturer;
    }

    public static Map<String, HashMap<String, Object>> getStash() {
        return stash;
    }

    public static void putStash(String manufacturerName, String key, Object value) {
        HashMap<String, Object> map = new HashMap<>();
        map.put(key, value);
        if (!stash.containsKey(manufacturerName)) {
            stash.put(manufacturerName, map);
        } else {
            stash.get(manufacturerName).put(key, value);
        }
    }
}
