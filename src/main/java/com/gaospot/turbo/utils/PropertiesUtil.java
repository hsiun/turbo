package com.gaospot.turbo.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author gaoxiong@asiainfo.com
 * @date 2020-04-10 11:28
 */

public class PropertiesUtil {
    public static final Map<String, String> customMap = new HashMap<>();

    private static final String propertiesPath = "src/main/resources/MATE-INF/";

    static {
        //相对目录
        File dir = new File(propertiesPath);
        System.out.println("dir = " + dir.getAbsolutePath());

        try {
            List<File> fileList = FileUtil.searchAllFile(new File(dir.getAbsolutePath()));
            for (File file : fileList) {
                if (file.getName().endsWith(".properties")) {
                    Properties properties = new Properties();
                    properties.load(new FileReader(file));
                    customMap.putAll((Map)properties);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PropertiesUtil.customMap.forEach((k, v) -> {
            System.out.println( k + " = " + v);
        });
    }
}
