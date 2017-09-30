package com.base.noob.ray.noob.binlog.meta;

import lombok.Data;


@Data
public class ColumnMeta {
    String columnName;
    boolean isPk;
    String type;
}
