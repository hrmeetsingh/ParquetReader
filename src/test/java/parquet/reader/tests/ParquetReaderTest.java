package parquet.reader.tests;

import org.junit.Assert;
import org.junit.BeforeClass;
import parquet.utils.Parquet;
import parquet.utils.ParquetReaderUtils;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class ParquetReaderTest {

    public static Parquet testParquetFile;

    public static String parquetTestFilePath(){
        String currentDirectory = System.getProperty("user.dir");
        return currentDirectory + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "part-00000.parquet";
    }

    @BeforeClass
    public static void setup(){
        testParquetFile = ParquetReaderUtils.getParquetData(parquetTestFilePath());
    }

    @Test
    public void testParquetFileRead(){
        Assert.assertEquals("Data size does not match",4, testParquetFile.getData().size());
        Assert.assertEquals("Schema does not match", "message spark_schema {\n" +
                "  optional int32 age;\n" +
                "  optional binary name (STRING);\n" +
                "}\n", testParquetFile.getSchema().toString());
        Assert.assertEquals("Schema field list does not match",
                "[optional int32 age, optional binary name (STRING)]",
                testParquetFile.getSchemaFields().toString());
    }
}