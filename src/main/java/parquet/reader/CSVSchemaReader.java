package parquet.reader;

import org.apache.parquet.schema.MessageType;
import parquet.utils.CSVSchemaReaderUtils;
import java.io.IOException;

public class CSVSchemaReader {
    public static void main(String args[]) throws IOException {
        String currentDirectory = System.getProperty("user.dir");
        System.out.println("User's current working directory is: " + currentDirectory);

        MessageType csvSchema = CSVSchemaReaderUtils.csvSchema(currentDirectory + "/src/main/resources/testschema.csv");
        System.out.println(csvSchema.toString());
    }
}
