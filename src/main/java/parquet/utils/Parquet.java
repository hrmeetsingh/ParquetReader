package parquet.utils;

import org.apache.parquet.example.data.simple.SimpleGroup;
import org.apache.parquet.schema.Type;

import java.util.List;
import java.util.Map;

public class Parquet {
    private List<SimpleGroup> data;
    private List<Type> schema;
    private Map<String, String> keyValueMetadata;

    public Parquet(List<SimpleGroup> data, List<Type> schema, Map<String, String> keyValueMetadata) {
        this.data = data;
        this.schema = schema;
        this.keyValueMetadata = keyValueMetadata;
    }

    public List<SimpleGroup> getData() {
        return data;
    }

    public List<Type> getSchema() {
        return schema;
    }

    public Map<String, String> getKeyValueMetadata() {
        return keyValueMetadata;
    }
}