package hcc.java.checkengine.Utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import hcc.java.checkengine.Index;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DataAccess {

    private static String jsonFilePath = System.getProperty("user.home") + "\\fileIndex.json";

    public static void writeIndex(Index index) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(index);

        try {
            FileWriter fileWriter = new FileWriter(jsonFilePath);
            fileWriter.write(json);
            fileWriter.close();
        } catch (Exception ex) { }
    }

    public static Index loadIndex() throws IOException {

        Index index = new Index();
        File jsonFile = new File(jsonFilePath);

        if (jsonFile.exists()) {
            JsonReader jsonReader = new JsonReader(new FileReader(jsonFile));
            Gson gson = new Gson();
            index = gson.fromJson(jsonReader, Index.class);
        }
        return index;
    }
}
