package jsonFileHandling;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.tinylog.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonWriter {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public JsonWriter() { }

    public void writeToFile(File file, ArrayList<MatchResultData> newResults) {
        try {
            OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValue(file , newResults);
        } catch (IOException e) {
            Logger.error("Json writer failed!");
        }
    }
}
