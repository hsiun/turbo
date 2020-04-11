package com.gaospot.turbo.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gaoxiong@asiainfo.com
 * @date 2020-04-10 11:28
 */

public class FileUtil {

    /**
     * 得到相对路径
     *
     * @param baseDir
     * @param file
     * @return
     */
    public static String getRelativePath(File baseDir, File file) {
        if (baseDir.equals(file)) {
            return "";
        }
        if (baseDir.getParentFile() == null) {
            return file.getAbsolutePath().substring(baseDir.getAbsolutePath().length());
        }
        return file.getAbsolutePath().substring(baseDir.getAbsolutePath().length() + 1);
    }

    /**
     * 查询某个目录下所有文件
     *
     * @param dir
     * @return
     * @throws IOException
     */
    public static List<File> searchAllFile(File dir) throws IOException {
        List<File> list = new ArrayList<>();
        searchFiles(dir, list);
        return list;
    }

    /**
     * 递归查询目录下所有文件
     * @param dir
     * @param list
     * @throws IOException
     */
    public static void searchFiles(File dir, List<File> list) throws IOException {
        if (dir.isDirectory()) {
            File[] subFiles = dir.listFiles();
            if (subFiles == null) {
                return;
            }
            for (File file: subFiles) {
                searchFiles(file, list);
            }
        } else {
            list.add(dir);
        }
    }

    /**
     * 创建文件
     *
     * @param dir
     * @param file
     * @return
     */
    public static File mkdir(String dir, String file) {
        if (dir == null) {
            return null;
        }
        File result = new File(dir, file);
        if (result.getParentFile() != null) {
            result.getParentFile().mkdirs();
        }

        return result;
    }
}
