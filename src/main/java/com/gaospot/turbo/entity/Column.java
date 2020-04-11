package com.gaospot.turbo.entity;

/**
 * 数据库表中列的抽象类（数据库列->类变量）
 *
 * @author gaoxiong@asiainfo.com
 * @date 2020-04-10 11:16
 */

public class Column {
    /** 列名称 */
    private String columnName;
    /** 变量名称 */
    private String variableName;
    /** 列类型 */
    private String columnType;
    /** 变量类型 */
    private String variableType;
    /** 列备注 */
    private String remark;
    /** 是否是主键 */
    private String variableIsKey;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getVariableType() {
        return variableType;
    }

    public void setVariableType(String variableType) {
        this.variableType = variableType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getVariableIsKey() {
        return variableIsKey;
    }

    public void setVariableIsKey(String variableIsKey) {
        this.variableIsKey = variableIsKey;
    }

    @Override
    public String toString() {
        return "Column{" +
                "columnName='" + columnName + '\'' +
                ", variableName='" + variableName + '\'' +
                ", columnType='" + columnType + '\'' +
                ", variableType='" + variableType + '\'' +
                ", remark='" + remark + '\'' +
                ", variableIsKey='" + variableIsKey + '\'' +
                '}';
    }
}
