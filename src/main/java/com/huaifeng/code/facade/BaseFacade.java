package com.huaifeng.code.facade;

import com.huaifeng.code.converter.TypeConverter;
import com.huaifeng.code.dao.BaseDAO;
import com.huaifeng.code.pojo.Column;
import com.huaifeng.code.pojo.Table;
import com.huaifeng.code.wapper.TableWapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * BaseFacade
 *
 * @author huaifeng
 * @since 2018-07-23
 */
@Component
public class BaseFacade {
    
    @Resource
    private BaseDAO baseDAO;
    
    @Resource
    private TypeConverter typeConverter;
    
    private final Map<String, TableWapper> tableMap = new HashMap<String, TableWapper>();
    
    public Map<String,TableWapper> getTables(String tableName,String schema){
        String[] names = tableName.split(",");
        for (String name : names) {
            List<Table> tables = baseDAO.getTable(schema, name);
            for (Table table : tables) {
                List<Column> columns = baseDAO.getColumn(schema, name);
                for (Column column : columns) {
                    table.addColumn(column);
                }
                tableMap.put(table.getName().toUpperCase(Locale.ENGLISH), new TableWapper(table, typeConverter));
            }
        }
        return tableMap;
    }
}
