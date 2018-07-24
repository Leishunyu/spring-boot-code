package com.huaifeng.code.dao;

import com.huaifeng.code.pojo.Column;
import com.huaifeng.code.pojo.Table;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * BaseDao
 *
 * @author huaifeng
 * @since 2018-07-23
 */
@Component
public class BaseDAO {
    
    @Resource
    private DataSource dataSource;
    
    private final String SELECT_TABLE="SELECT upper(ts.TABLE_CATALOG) catalog,upper(ts.TABLE_SCHEMA) \"schema\",upper(ts.TABLE_NAME) name,ts.TABLE_COMMENT comment FROM information_schema.TABLES ts WHERE ts.TABLE_SCHEMA = ? AND ts.TABLE_NAME like ?";
    
    private final String SELECT_COLUMN ="SELECT upper(cs.COLUMN_NAME) \"name\", cs.ORDINAL_POSITION ordinal,upper(cs.COLUMN_TYPE) ype,upper(cs.DATA_TYPE) dataType,cs.COLUMN_DEFAULT defValue,cs.IS_NULLABLE nullable,cs.CHARACTER_MAXIMUM_LENGTH length,cs.NUMERIC_PRECISION percision, cs.NUMERIC_SCALE scale, upper(cs.COLUMN_KEY) \"key\",cs.EXTRA extra,cs.COLUMN_COMMENT \"comment\" FROM information_schema.COLUMNS cs WHERE cs.TABLE_SCHEMA = ? AND cs.TABLE_NAME =? ORDER BY cs.ORDINAL_POSITION";
    
    public List<Table> getTable(String schema,String tableName){
        List<Table> tables=new ArrayList<>();
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            conn=dataSource.getConnection();
            statement = conn.prepareStatement(SELECT_TABLE);
            statement.setString(1, schema);
            statement.setString(2, tableName);
            result = statement.executeQuery();
            while (result.next()) {
                tables.add(new Table(result.getString(1),result.getString(2),result.getString(3),null,null));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                result.close();
            } catch (Throwable e) {
            }
            try {
                statement.close();
            } catch (Throwable e) {
            }
            try {
                conn.close();
            } catch (Throwable e) {
            }
        }
        return tables;
    }
    
    public List<Column> getColumn(String schema, String tableName){
        List<Column> columns=new ArrayList<>();
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            conn=dataSource.getConnection();
            statement = conn.prepareStatement(SELECT_COLUMN);
            statement.setString(1, schema);
            statement.setString(2, tableName);
            result = statement.executeQuery();
            while (result.next()) {
                columns.add(new Column(result.getString(1),result.getLong(2),result.getString(3),result.getString(4),result.getString(5),result.getString(6),result.getLong(7),result.getLong(8),result.getLong(9),result.getString(10),result.getString(11),result.getString(12)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                result.close();
            } catch (Throwable e) {
            }
            try {
                statement.close();
            } catch (Throwable e) {
            }
            try {
                conn.close();
            } catch (Throwable e) {
            }
        }
        return columns;
    }
    
    
    
}
