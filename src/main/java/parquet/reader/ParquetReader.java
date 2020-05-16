package parquet.reader;

import parquet.utils.ParquetReaderUtils;
import parquet.utils.Parquet;
import org.apache.parquet.example.data.simple.SimpleGroup;
import java.io.IOException;


public class ParquetReader{
    public static void main(String args[]) throws IOException{
        String currentDirectory = System.getProperty("user.dir");
        System.out.println("User's current working directory is: "+currentDirectory);

        Parquet parquet = ParquetReaderUtils.getParquetData(currentDirectory+"/src/main/resources/part-00000.parquet");
        SimpleGroup simpleGroup = parquet.getData().get(0);
//        String storedString = simpleGroup.get(0).getString("theFieldIWant", 0);
    }
}
