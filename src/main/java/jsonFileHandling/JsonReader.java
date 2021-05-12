   package jsonFileHandling;
   
   import com.fasterxml.jackson.core.type.TypeReference;
   import com.fasterxml.jackson.databind.ObjectMapper;
   import org.tinylog.Logger;
   
   import java.io.File;
   import java.io.IOException;
   import java.util.ArrayList;
  
  /**
   * Class used for reading input from json.
   */
  public class JsonReader {
      /**
       * Mapper object used for reading.
       */
      private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
      /**
       * Stores data read from json.
       */
      private ArrayList<MatchResultData> results;
  
      /**
      * Creates a {@link JsonReader} object.
      *
      * @param file The json file to read from.
      */
     public JsonReader(File file) {
         if (file==null) {Logger.error("No file choosen!");}
          Logger.debug(file);
          try{
              loadData(file);
          } catch(IOException e) {
              Logger.error(e);
          }
      }
  
      /**
       * Reads the content of the given file.
       *
       * @param file The given file.
       * @throws IOException An exception thrown when the program fails to access the file.
       */
      private void loadData(File file) throws IOException {
          results = OBJECT_MAPPER.readValue(file, new TypeReference<>() {
          });
      }
  
      /**
       * Returns read data.
       *
       * @return Read data.
       */
      public ArrayList<MatchResultData> getAll() {
          if (results != null) {
              return results;
          }
          else {return new ArrayList<>();}
      }
  
  }