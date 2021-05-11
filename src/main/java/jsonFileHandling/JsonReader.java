package jsonFileHandling;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.tinylog.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class JsonReader {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private ArrayList<MatchResultData> results;

    public JsonReader(File file) {
        if (file==null) {Logger.error("No file choosen!");}
        Logger.debug(file);
        try{
            loadData(file);
        } catch(IOException e) {
            Logger.error(e);
        }
    }

    private void loadData(File file) throws IOException {
        results = OBJECT_MAPPER.readValue(file, new TypeReference<>() {
        });
    }

    public ArrayList<MatchResultData> getAll() {
        if (results != null) {
            return results;
        }
        else {return new ArrayList<>();}
    }

}
