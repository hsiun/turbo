package com.gaospot.turbo.entity;

import java.util.List;

/**
 * 数据库表的抽象类（数据库表->Java类）
 *
 * @author gaoxiong@asiainfo.com
 * @date 2020-04-10 11:16
 */

public class Table {
    /** 数据库表名 */
    private String tableName;
    /** 表名对应的实体类名 */
    private String className;
    /** 表注释 */
    private String comment;
    /** 主键列 */
    private String keys;
    /** 列集合 */
    private List<Column> columns;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        return "Table{" +
                "tableName='" + tableName + '\'' +
                ", className='" + className + '\'' +
                ", comment='" + comment + '\'' +
                ", keys='" + keys + '\'' +
                ", columns=" + columns +
                '}';
    }
}
