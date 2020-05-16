package parquet.reader;

import parquet.utils.ParquetReaderUtils;
import parquet.utils.Parquet;
import org.apache.parquet.example.data.simple.SimpleGroup;
import java.io.IOException;
import org.apache.parquet.schema.Type;
import java.util.List;


public class ParquetReader{
    public static void main(String args[]) throws IOException{
        String currentDirectory = System.getProperty("user.dir");
        System.out.println("User's current working directory is: "+currentDirectory);

        Parquet parquet = ParquetReaderUtils.getParquetData(currentDirectory+"/src/main/resources/part-00000.parquet");
        List<Type> schemalist = parquet.getSchema();
        List<SimpleGroup> datagroup = parquet.getData();

        System.out.println("Data ---->");
        for (SimpleGroup data : datagroup){
            System.out.println(data.toString());
        }

        System.out.println("Schema ---->");
        for (Type schema: schemalist){
            System.out.println("Name: "+schema.getName());
            System.out.println("Original Type: "+schema.getOriginalType());
            System.out.println("Primitive Type: "+schema.asPrimitiveType());
        }
    }
}
