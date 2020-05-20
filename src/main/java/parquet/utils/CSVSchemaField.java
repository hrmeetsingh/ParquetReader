package parquet.utils;

import org.apache.parquet.schema.PrimitiveType;
import org.apache.parquet.schema.Type;

public class CSVSchemaField {
    String fieldName;
    String fieldPrimitiveType;

    public CSVSchemaField(String fieldName, String fieldPrimitiveType){
        this.fieldName = fieldName;
        this.fieldPrimitiveType = fieldPrimitiveType;
    }

    public PrimitiveType getPrimitiveType(){
        return new PrimitiveType(Type.Repetition.REQUIRED, PrimitiveType.PrimitiveTypeName.valueOf(fieldPrimitiveType), this.fieldName);
    }

}

