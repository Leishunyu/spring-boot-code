package com.huaifeng.code.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Table
 *
 * @author huaifeng
 * @since 2018-07-23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Table {
    //表目录
    private String catalog;
    //数据库名
    private String schema;
    //表名
    private String name;
    //表描述
    private String comment;
    //表字段
    private final Map<String, Column> columns = new LinkedHashMap<String, Column>();
    //主键
    private Column primaryKey;
    
    public void addColumn(Column column) {
        this.columns.put(column.getName().toLowerCase(), column);
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }
}
