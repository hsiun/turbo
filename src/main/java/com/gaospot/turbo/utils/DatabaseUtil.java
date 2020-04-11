package com.gaospot.turbo.utils;

import com.gaospot.turbo.entity.Column;
import com.gaospot.turbo.entity.Database;
import com.gaospot.turbo.entity.DbType;
import com.gaospot.turbo.entity.Table;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author gaoxiong@asiainfo.com
 * @date 2020-04-10 15:14
 */

public class DatabaseUtil {

    /**
     * 获取数据库连接
     *
     * @param db
     * @return
     * @throws Exception
     */
    public static Connection getConnection(Database db) throws Exception {
        Properties properties = new Properties();
        properties.put("remarksReporting", "true");
        properties.put("user", db.getUsername());
        properties.put("password", db.getPassword());
        Class.forName(db.getDriver());
        return DriverManager.getConnection(db.getUrl(), properties);
    }

    /**
     * 获取数据库列表
     *
     * @param db
     * @return
     * @throws Exception
     */
    public static List<String> getSchemas(Database db) throws Exception {
        Connection connection = getConnection(db);
        DatabaseMetaData metaData = connection.getMetaData();

        ResultSet catalogs = metaData.getCatalogs();
        List<String> list = new ArrayList<>();
        while (catalogs.next()) {
            list.add(catalogs.getString(1));
        }
        catalogs.close();
        connection.close();
        return list;
    }

    /**
     * 获取数据库表的信息
     * @param db
     * @return
     * @throws Exception
     */
    public static List<Table> getDbInfo(Database db) throws Exception {
        Connection connection = getConnection(db);
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet tables = metaData.getTables(db.getDbName(), null, null, new String[]{"TABLE"});

        List<Table> list = new ArrayList<>();
        while (tables.next()) {
            Table table = new Table();
            String tableName = tables.getString("TABLE_NAME");
            table.setTableName(tableName);
            String className = removePrefix(tableName);
            table.setClassName(className);
            String remarks = tables.getString("REMARKS");
            table.setComment(remarks);
            ResultSet primaryKeys = metaData.getPrimaryKeys(null, null, tableName);
            StringBuilder keys = new StringBuilder();
            while (primaryKeys.next()) {
                String keyname = primaryKeys.getString("COLUMN_NAME");
                keys.append(keyname).append(",");
            }
            String primaryKey = keys.toString();
            table.setKeys(primaryKey);
            //字段处理
            List<Column> columnList = getColumns(metaData, tableName, primaryKey);
            table.setColumns(columnList);
            list.add(table);
        }
        tables.close();
        connection.close();

        return list;
    }

    /**
     * 获取列
     * @param metaData
     * @param tableName
     * @param primaryKey
     * @return
     * @throws SQLException
     */
    private static List<Column> getColumns(DatabaseMetaData metaData, String tableName, String primaryKey) throws SQLException {
        ResultSet columns = metaData.getColumns(null, null, tableName, null);
        List<Column> columnList = new ArrayList<>();
        while (columns.next()) {
            Column column = new Column();
            String columnName = columns.getString("COLUMN_NAME");
            column.setColumnName(columnName);
            String variableName = StringUtil.toJavaVariableName(columnName);
            column.setVariableName(variableName);
            String dbType = columns.getString("TYPE_NAME");
            column.setColumnType(dbType);
            String javaType = PropertiesUtil.customMap.get(dbType);
            column.setVariableType(javaType);
            String columnRemark = columns.getString("REMARKS");
            column.setRemark(columnRemark);
            //是否是主键
            String pri = null;
            if (StringUtil.contains(columnName, primaryKey.split(","))) {
                pri = "PRI";
            }
            column.setVariableIsKey(pri);
            columnList.add(column);
        }
        columns.close();

        return columnList;
    }

    /**
     * 删除前缀
     * @param tableName
     * @return
     */
    public static String removePrefix(String tableName) {
        String prefixes = PropertiesUtil.customMap.get("tableRemovePrefixes");
        String temp = tableName;
        for (String prefix : prefixes.split(",")) {
            temp = StringUtil.removePrefix(temp, prefix, true);
        }
        return StringUtil.makeFirstLetterUpper(temp);

    }


    public static void main(String[] args) throws Exception {
        Database database = new Database(DbType.MYSQL8, "eleme_sell");
        database.setUsername("root");
        database.setPassword("password");
        List<Table> dbInfo = DatabaseUtil.getDbInfo(database);
        for (Table table: dbInfo) {
            System.out.println("table = " + table);
        }
    }
}
