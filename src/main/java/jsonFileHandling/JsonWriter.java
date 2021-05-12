package jsonFileHandling;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.tinylog.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class used to write output to json.
 */
public class JsonWriter {
    /**
     * Mapper object used for writing.
     */
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * Creates a {@link JsonWriter} object.
     */
    public JsonWriter() {
    }

    /**
     * Writes the given output to the given file.
     *
     * @param file       The given file.
     * @param newResults The given Input.
     */
    public void writeToFile(File file, ArrayList<MatchResultData> newResults) {
        try {
            OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValue(file, newResults);
        } catch (IOException e) {
            Logger.error("Json writer failed!");
        }
    }
}