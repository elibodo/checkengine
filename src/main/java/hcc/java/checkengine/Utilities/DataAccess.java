package hcc.java.checkengine.Utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import hcc.java.checkengine.Index;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//TODO Needs logic for the JSON file path.
public class DataAccess {

    public static void writeIndex(Index index) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(index);

        try {
            FileWriter fileWriter = new FileWriter("");
            fileWriter.write(json);
            fileWriter.close();
        } catch (Exception ex) { }
    }

    public static Index loadIndex() throws IOException {

        Index index = new Index();
        File jsonFile = new File("");

        if (jsonFile.exists()) {
            JsonReader jsonReader = new JsonReader(new FileReader(jsonFile));
            Gson gson = new Gson();
            index = gson.fromJson(jsonReader, Index.class);
        }
        return index;
    }
}
