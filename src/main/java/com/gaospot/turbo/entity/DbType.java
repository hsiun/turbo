package com.gaospot.turbo.entity;

/**
 * @author gaoxiong@asiainfo.com
 * @date 2020-04-10 10:22
 */
public enum DbType {
    MYSQL(1, "MYSQL"),
    MYSQL8(8, "MYSQL8"),
    ORACLE(10, "ORACLE");

    /** 编码 */
    private int code;
    /** 数据库类型 */
    private String type;

    DbType(int code, String type) {
        this.code = code;
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public String getType() {
        return type;
    }
}
