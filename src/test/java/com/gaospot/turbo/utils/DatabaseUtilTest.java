package com.gaospot.turbo.utils;


import com.gaospot.turbo.entity.Database;
import com.gaospot.turbo.entity.DbType;
import org.junit.Before;
import org.junit.Test;

/**
 * @author gaoxiong@asiainfo.com
 * @date 2020-04-10 15:46
 */
public class DatabaseUtilTest {

    @Before
    public void init() {

    }


    @Test
    public void getSchemas() {
        Database database = new Database(DbType.MYSQL8);
        database.setUsername("root");
        database.setPassword("password");
    }
}
