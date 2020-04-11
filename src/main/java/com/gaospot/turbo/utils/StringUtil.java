package com.gaospot.turbo.utils;

/**
 * @author gaoxiong@asiainfo.com
 * @date 2020-04-10 11:28
 */

public class StringUtil {

    private StringUtil() {
    }

    /**
     * 删除前缀
     * @param temp
     * @param prefix
     * @param flag
     * @return
     */
    public static String  removePrefix(String temp, String prefix, boolean flag){
        return getTableName(temp);
    }

    /**
     * 去掉表名前缀
     * @param tableName
     * @return
     */
    public static String getTableName(String tableName){
        //全部转换为小写
        tableName=tableName.toLowerCase();
        //下划线的位置
        int index = tableName.indexOf('_');
        if(index==-1){
            return tableName;
        }
        //从下划线开始截取
        String name= tableName.substring(index+1);
        //去掉下划线
        return toJavaVariableName(name);
    }

    /**
     * 首字母大写
     * @param str
     * @return
     */
    public static String makeFirstLetterUpper(String str) {
        String s0=str.substring(0, 1).toUpperCase();
        return s0+str.substring(1);
    }

    /**
     * 字符串数据组中是否包含指定字符串
     * @param string
     * @param keywords
     * @return
     */
    public static boolean contains(String string, String... keywords) {
        if (string == null){ return false;}
        if (keywords == null){ return false;}
        for (String keyword: keywords) {
            if (string.contains(keyword.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 把列名转化为变量名
     * @param columnName
     * @return
     */
    public static String toJavaVariableName(String columnName) {
        while(true){
            //取下划线
            int i=columnName.indexOf('_');
            if(i==-1){
                break;//跳出
            }
            //取出需要转换的字母进行转换
            String n= columnName.substring(i+1,i+2).toUpperCase();

            columnName=columnName.substring(0,i)+n+ columnName.substring(i+2 ) ;

        }
        return columnName;
    }
}
