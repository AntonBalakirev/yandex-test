package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileUtils {

    public static void writeResultsToFile(Map.Entry<String, HashMap<String, Object>> maxEntry, String file) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        try {
            writer.write("Best Choice - " + maxEntry.toString() + "\n");
        } catch (NullPointerException e) {
            writer.write("There was error in writing result file\n");
            e.printStackTrace();
        } finally {
            writer.write("\n" + Stash.getStash().toString());
            writer.close();
        }
    }
}
