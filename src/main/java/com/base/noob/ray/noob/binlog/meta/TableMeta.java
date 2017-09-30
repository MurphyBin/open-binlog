package com.base.noob.ray.noob.binlog.meta;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class TableMeta {
    int tableId;
    String tableName; //dbName.tableName
    List<ColumnMeta> columnMetas = new ArrayList<>();
}
