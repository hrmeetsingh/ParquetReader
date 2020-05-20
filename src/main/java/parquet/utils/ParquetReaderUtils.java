package parquet.utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.column.page.PageReadStore;
import org.apache.parquet.example.data.simple.SimpleGroup;
import org.apache.parquet.example.data.simple.convert.GroupRecordConverter;
import org.apache.parquet.hadoop.ParquetFileReader;
import org.apache.parquet.hadoop.metadata.FileMetaData;
import org.apache.parquet.hadoop.util.HadoopInputFile;
import org.apache.parquet.io.ColumnIOFactory;
import org.apache.parquet.io.MessageColumnIO;
import org.apache.parquet.io.RecordReader;
import org.apache.parquet.schema.MessageType;
import org.apache.parquet.schema.Type;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParquetReaderUtils {

    private static final ParquetFileReader readFile(String filePath) throws IOException {
        return ParquetFileReader.open(HadoopInputFile.fromPath(new Path(filePath), new Configuration()));
    }

    public static Parquet getParquetData(String filePath) throws IOException {
        List<SimpleGroup> simpleGroups = new ArrayList<>();
        ParquetFileReader reader = readFile(filePath);
        FileMetaData fileMetaData = reader.getFooter().getFileMetaData();

        MessageType schema = fileMetaData.getSchema();
        Map<String, String> keyValueMetadata = fileMetaData.getKeyValueMetaData();

        List<Type> fields = schema.getFields();
        PageReadStore pages;
        while ((pages = reader.readNextRowGroup()) != null) {
            long rows = pages.getRowCount();
            MessageColumnIO columnIO = new ColumnIOFactory().getColumnIO(schema);
            RecordReader recordReader = columnIO.getRecordReader(pages, new GroupRecordConverter(schema));

            for (int i = 0; i < rows; i++) {
                SimpleGroup simpleGroup = (SimpleGroup) recordReader.read();
                simpleGroups.add(simpleGroup);
            }
        }
        reader.close();
        return new Parquet(simpleGroups, fields, keyValueMetadata, schema);
    }
}