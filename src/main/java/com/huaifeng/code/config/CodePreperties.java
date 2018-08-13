package com.huaifeng.code.config;

import com.huaifeng.code.converter.TypeConverter;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * CodePreperties
 *
 * @author huaifeng
 * @since 2018-07-23
 */
@Data
@ConfigurationProperties( prefix = "code" )
public class CodePreperties {
    
    /**
     * 实体包名
     */
    private String pojoPkg;
    
    /**
     * mapper包名
     */
    private String mapperPkg;
    
    /**
     * 类型转换
     */
    private Map<String,String> typesMap;
    /**
     *  数据库schema
     */
    private String schema;
    
    /**
     * 数据表名称，多个以逗号隔开，支持Like查询
     */
    private String tableName ;
    /**
     *     代码写入磁盘路径
     */
    private String rootPath = "outCode";
    
    /**
     * 模板路径
     */
    private String path;
    
    /**
     * pojpName
     */
    private String pojoName;
    
    /**
     * mamperName
     */
    private String mapperName;
    
    /**
     * templateEntity
     */
    private String templateEntity="Entity.ftl";
    
    /**
     * templateMapperXml
     */
    private String templateMapperXml="EntityMapperXml.ftl";
    
    /**
     * templateMapper
     */
    private String templateMapper="EntityMapper.ftl";
    
    /**
     * templateDAO
     */
    private String templateDAO="EntityDAO.ftl";
}
