package com.base.noob.ray.noob.binlog.meta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TableMetaCache {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    private Map<String, TableMeta> tableMetaMap = new ConcurrentHashMap<>();
    private Map<String, String> tableId2Name = new ConcurrentHashMap<>();

    public TableMeta rebuild(String tableId) {
        tableMetaMap.remove(tableId);
        return getTableMeta(tableId);
    }

    public TableMeta getTableMeta(String tableId) {
        TableMeta tableMeta = tableMetaMap.get(tableId);

        if (null == tableMeta) {
            String tableName = getTableName(tableId);
            tableMeta = fetchTableMeta(tableName);

            synchronized (this) {
                if (!tableMetaMap.containsKey(tableId)) {
                    tableMetaMap.put(tableId, tableMeta);
                }
            }
        }
        return tableMeta;
    }


    public void put(String tableId, String tableName) {
        String preTableName = tableId2Name.get(tableId);

        if (null == preTableName
                || !preTableName.equals(tableName)) {
            tableId2Name.put(tableId, tableName);
        }
    }


    private String getTableName(String tableId) {
        return tableId2Name.get(tableId);
    }


    private  TableMeta fetchTableMeta(String tableName) {
        TableMeta tableMeta = new TableMeta();
        tableMeta.setTableName(tableName);

        jdbcTemplate.queryForList("desc " + tableName).forEach(column -> {
            ColumnMeta columnMeta = new ColumnMeta();

            columnMeta.setColumnName((String) column.get("Field"));
            columnMeta.setPk("PRI".equals(column.get("Key")));
            columnMeta.setType((String) column.get("Type"));

            tableMeta.getColumnMetas().add(columnMeta);
        });

        return tableMeta;
    }
}
