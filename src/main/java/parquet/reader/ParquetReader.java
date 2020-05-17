package parquet.reader;

import parquet.utils.ParquetReaderUtils;
import parquet.utils.Parquet;
import org.apache.parquet.example.data.simple.SimpleGroup;
import java.io.IOException;
import org.apache.parquet.schema.Type;
import java.util.List;
import java.util.Map;


public class ParquetReader{
    public static void main(String args[]) throws IOException{
        String currentDirectory = System.getProperty("user.dir");
        System.out.println("User's current working directory is: "+currentDirectory);

        Parquet parquet = ParquetReaderUtils.getParquetData(currentDirectory+"/src/main/resources/part-00000.parquet");
        List<Type> schemalist = parquet.getSchema();
        List<SimpleGroup> datagroup = parquet.getData();
        Map<String, String> keyValueFileMetaData = parquet.getKeyValueMetadata();

        System.out.println("\nData ---->");
        for (SimpleGroup data : datagroup){
            System.out.println(data.toString());
        }

        System.out.println("\nSchema ---->");
        for (Type schema: schemalist){
            System.out.println("Name: "+schema.getName());
            System.out.println("Original Type: "+schema.getOriginalType());
            System.out.println("Primitive Type: "+schema.asPrimitiveType());
        }

        System.out.println("\nFile metadata key values ---->");
        for (Map.Entry<String, String> entry : keyValueFileMetaData.entrySet()) {
            System.out.println("Key - "+ entry.getKey());
            System.out.println("Value - " + entry.getValue());
        }
    }
}
