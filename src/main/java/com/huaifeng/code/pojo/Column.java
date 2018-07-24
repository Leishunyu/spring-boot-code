package com.huaifeng.code.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Column
 *
 * @author huaifeng
 * @since 2018-07-23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Column {
    // 字段名称
    private String name;
    // 字段序号
    private Long ordinal;
    // 字段类型
    private String type;
    // 字段类型(全)
    private String dataType;
    // 默认值
    private String defValue;
    // 是否可为空
    private String nullable;
    // 字段长度(字符串)
    private Long length;
    // 字段长度(数值)
    private Long precision;
    // 小数长度(数值)
    private Long scale;
    // PRI：主键
    private String key;
    // auto_increment标识为自增长
    private String extra;
    // 字段注释
    private String comment;
}
