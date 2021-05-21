package com.keenon.mybatisplus.utils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * @author edz
 */
public class CodeGeneratorUtil {
    /**
     * <p>
     * MyBatisPlus 代码生成工具类
     * </p>
     */
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 选择 freemarker 引擎，默认 Veloctiy
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        String projectPath = System.getProperty("user.dir");
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setAuthor("zhy");
        gc.setOutputDir(projectPath + "/src/main/java");
        // 是否覆盖同名文件，默认是false
        gc.setFileOverride(true);
        gc.setSwagger2(true);

        /* 自定义文件命名，注意 %s 会自动填充表实体属性！ */
        gc.setEntityName("%sEntity");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setUrl("jdbc:mysql://47.100.105.14:3306/zhy?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&zeroDateTimeBehavior=convertToNull");
        //dsc.setSchemaName("peanut-hotel");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.keenon.mybatisplus");
        pc.setEntity("entity");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setMapper("mapper");
        pc.setXml("mapper");
        mpg.setPackageInfo(pc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setEntityTableFieldAnnotationEnable(false);
        strategy.setRestControllerStyle(true);
        strategy.setEntityBooleanColumnRemoveIsPrefix(true);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setLogicDeleteFieldName("logicDelete");
        mpg.setStrategy(strategy);
        // 需要生成的表

        strategy.setInclude("user");
        mpg.execute();
    }
}
