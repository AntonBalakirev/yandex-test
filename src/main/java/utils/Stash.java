package utils;

import java.util.HashMap;
import java.util.Map;

public class Stash {

    private static Map<String, HashMap<String, Object>> stash = new HashMap();

    public static Map<String, HashMap<String, Object>> getStash() {
        return stash;
    }

    public static void putStash(String manufacturerName, String key, Object value) {
        HashMap<String, Object> map = new HashMap<>();
        map.put(key, value);
        stash.put(manufacturerName, map);
    }
}
