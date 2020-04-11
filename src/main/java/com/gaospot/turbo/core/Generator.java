package com.gaospot.turbo.core;

import com.gaospot.turbo.utils.FileUtil;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gaoxiong@asiainfo.com
 * @date 2020-04-10 19:23
 */

public class Generator {
    private String templatePath;
    private String outPath;
    private Configuration configuration;

    Generator(String templatePath, String outPath) throws IOException {
        this.templatePath = templatePath;
        this.outPath = outPath;
        configuration = new Configuration();
        FileTemplateLoader loader = new FileTemplateLoader(new File(templatePath));
        configuration.setTemplateLoader(loader);
    }

    /**
     * 扫描并生成代码
     * @param dataModel
     * @throws IOException
     */
    public void scanAndGenerator(Map<String, Object> dataModel) throws Exception {
        List<File> fileList = FileUtil.searchAllFile(new File(templatePath));
        for (File file : fileList) {
            executeGenerator(dataModel, file);
        }

    }

    /**
     * 生成代码
     * @param dataModel
     * @param file
     */
    private void executeGenerator(Map<String, Object> dataModel, File file) throws Exception {
        //获取包路径模版
        String templateFilename = file.getAbsolutePath().replace(templatePath, "");
        //获取真实包路径
        String outFilePath = processTemplateString(templateFilename, dataModel);
        //获取模版文件
        Template template = configuration.getTemplate(templateFilename);
        template.setOutputEncoding("utf-8");
        //创建文件夹
        File outFile = FileUtil.mkdir(outPath, outFilePath);
        FileWriter out = new FileWriter(outFile);
        //生成代码文件
        template.process(dataModel, out);
        out.close();
    }

    /**
     * 生成真实包路径
     * @param templateFilename
     * @param dataModel
     * @return
     * @throws Exception
     */
    private String processTemplateString(String templateFilename, Map<String, Object> dataModel) throws Exception {
        StringWriter writer = new StringWriter();
        Template template = new Template("ts", new StringReader(templateFilename), configuration);
        template.process(dataModel, writer);
        return writer.toString();

    }

    public static void main(String[] args) throws Exception {
        String template = "/Users/gao/my_code_trunk/turbo/properties/template";
        String outpath = "/Users/gao/my_code_trunk/turbo/properties/fileout";
        Generator generator = new Generator(template, outpath);
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("username", "gaoxiong");
        generator.scanAndGenerator(dataModel);
    }
}
