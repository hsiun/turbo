package com.gaospot.turbo.entity;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 系统配置
 *
 * @author gaoxiong@asiainfo.com
 * @date 2020-04-10 11:01
 */

public class Settings {
    private String project = "example";
    private String projectPackage = "com.example.demo";
    private String projectComment;
    private String author;
    private String path1 = "com";
    private String path2 = "example";
    private String path3 = "demo";
    private String packagePath;

    public Settings(String project, String projectPackage, String projectComment, String author) {
        this.project = project;
        this.projectPackage = projectPackage;
        this.projectComment = projectComment;
        this.author = author;
        String[] paths = projectPackage.split("\\.");
        path1 = paths[0];
        path2 = paths.length > 1 ? paths[1] : path2;
        path3 = paths.length > 2 ? paths[2] : path3;
        packagePath = projectPackage.replaceAll("\\.", "/");
    }

    public Map<String, Object> getSettingMap() throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>(8);
        Field[] declaredFields = Settings.class.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(this));

        }
        return map;
    }


    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getProjectPackage() {
        return projectPackage;
    }

    public void setProjectPackage(String projectPackage) {
        this.projectPackage = projectPackage;
    }

    public String getProjectComment() {
        return projectComment;
    }

    public void setProjectComment(String projectComment) {
        this.projectComment = projectComment;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPath1() {
        return path1;
    }

    public void setPath1(String path1) {
        this.path1 = path1;
    }

    public String getPath2() {
        return path2;
    }

    public void setPath2(String path2) {
        this.path2 = path2;
    }

    public String getPath3() {
        return path3;
    }

    public void setPath3(String path3) {
        this.path3 = path3;
    }

    public String getPackagePath() {
        return packagePath;
    }

    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
    }

}
