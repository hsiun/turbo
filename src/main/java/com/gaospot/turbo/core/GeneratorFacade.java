package com.gaospot.turbo.core;

import com.gaospot.turbo.entity.Database;
import com.gaospot.turbo.entity.Settings;
import com.gaospot.turbo.entity.Table;
import com.gaospot.turbo.utils.DatabaseUtil;
import com.gaospot.turbo.utils.PropertiesUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gaoxiong@asiainfo.com
 * @date 2020-04-10 19:23
 */

public class GeneratorFacade {
    private String templatePath;
    private Settings settings;
    private String outPath;
    private Database database;
    private Generator generator;

    public GeneratorFacade(String templatePath, Settings settings, String outPath, Database database) throws IOException {
        this.templatePath = templatePath;
        this.settings = settings;
        this.outPath = outPath;
        this.database = database;
        generator = new Generator(this.templatePath, this.outPath);
    }

    public void generatorByDatabase() throws Exception {
        List<Table> tables = DatabaseUtil.getDbInfo(database);
        for (Table table: tables) {
            Map<String, Object> dataModel = getDataModel(table);
            generator.scanAndGenerator(dataModel);
//            for (Map.Entry<String, Object> entry: dataModel.entrySet()) {
//                System.out.println(entry.getKey() + "=" + entry.getValue());
//            }
        }

    }

    private Map<String, Object> getDataModel(Table table) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        map.putAll(PropertiesUtil.customMap);
        map.put("table", table);
        map.putAll(this.settings.getSettingMap());
        map.put("ClassName", table.getClassName());
        return map;
    }
}
