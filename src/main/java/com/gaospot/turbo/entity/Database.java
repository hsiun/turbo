package com.gaospot.turbo.entity;

/**
 * 数据库实体，用于建立数据库连接
 *
 * @author gaoxiong@asiainfo.com
 * @date 2020-04-10 10:26
 */

public class Database {
    private static final String MYSQL_URL = "jdbc:mysql://[ip]:[port]/[db]?characterEncoding=UTF8&serverTimezone=UTC";
    private static final String ORACLE_URL = "jdbc:oracle:thin:@[ip]:[port]:[db]";
    /** 数据库类型 */
    private DbType dbType;
    /** 驱动类 */
    private String driver;
    /** 连接串 */
    private String url;
    private String username;
    private String password;
    /** 数据库名称 */
    private String dbName;

    public Database() {
        this(DbType.MYSQL8);
    }

    public Database(DbType dbType) {
        this(dbType, "127.0.0.1", "3306", "");
    }

    public Database(DbType dbType, String db) {
        this(dbType, "127.0.0.1", "3306", db);
    }

    public Database(DbType dbType, String ip, String port, String db) {
        this.dbName = db;
        this.dbType = dbType;
        this.url = MYSQL_URL.replace("[ip]", ip).replace("[port]", port).replace("[db]", db);
        this.driver = "com.mysql.jdbc.Driver";
        if (DbType.MYSQL == dbType) {
            this.driver = "com.mysql.jdbc.Driver";
        } else if (DbType.MYSQL8 == dbType) {
            this.driver = "com.mysql.cj.jdbc.Driver";
        } else if (DbType.ORACLE == dbType){
            this.driver = "oracle.jdbc.driver.OracleDriver";
            this.url = ORACLE_URL.replace("[ip]", ip).replace("[port]", port).replace("[db]", db);
        }
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public DbType getDbType() {
        return dbType;
    }

    public void setDbType(DbType dbType) {
        this.dbType = dbType;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
