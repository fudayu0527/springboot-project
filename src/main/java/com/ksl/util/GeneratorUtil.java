package com.ksl.util;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.ksl.config.GeneratorConf;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ksl
 * @Description TODO
 * @data 2021/05/12
 */
public class GeneratorUtil {
    private GeneratorUtil() {

    }

    /**
     * 代码生成器
     */
    public static void generate() {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        //String projectPath = GeneratorUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor(GeneratorConf.author);
        gc.setMapperName(GeneratorConf.mapperName);
        gc.setServiceName(GeneratorConf.serviceName);
        gc.setServiceImplName(GeneratorConf.serviceImplName);
        gc.setXmlName(GeneratorConf.xmlName);
        gc.setOpen(false);
        gc.setEnableCache(false);
        //是否覆盖已生成的文件
        gc.setFileOverride(false);
        //实体属性 Swagger2 注解
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(GeneratorConf.url);
        // dsc.setSchemaName("public");
        dsc.setDriverName(GeneratorConf.driverName);
        dsc.setUsername(GeneratorConf.username);
        dsc.setPassword(GeneratorConf.password);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(GeneratorConf.basePackage)
                .setController(GeneratorConf.controllerPackageName)
                .setService(GeneratorConf.servicePackageName)
                .setServiceImpl(GeneratorConf.serviceImplPackageName)
                .setMapper(GeneratorConf.mapperPackageName)
                .setXml(GeneratorConf.xmlPackageName)
                .setEntity(GeneratorConf.entityPackageName);
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                //  TODO  to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        // String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/template/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        //指定自定义文件输出路径，是需要带上.ftl/.vm, 注意和下面的模板配置做区分
        focList.add(new FileOutConfig(GeneratorConf.xmlTemplatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" +
                        "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录，自定义目录用");
                if (fileType == FileType.MAPPER) {
                    // 已经生成 mapper 文件判断存在，不想重新生成返回 false
                    return !new File(filePath).exists();
                }
                // 允许生成模板文件
                return true;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别

        templateConfig
                .setEntity(GeneratorConf.entityTemplatePath)
                .setMapper(GeneratorConf.mapperTemplatePath)
                .setService(GeneratorConf.serviceTemplatePath)
                .setServiceImpl(GeneratorConf.serviceImplTemplatePath)
                .setController(GeneratorConf.controllerTemplatePath)
                .setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        //strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        //// 公共父类
        //strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        //// 写于父类中的公共字段
        //strategy.setSuperEntityColumns("id");
        //strategy.setControllerMappingHyphenStyle(true);
        //strategy.setTablePrefix(pc.getModuleName() + "_");

        strategy.setInclude(GeneratorConf.tableNames);
        mpg.setStrategy(strategy);

        mpg.setTemplateEngine(new VelocityTemplateEngine());
        // mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        //执行
        mpg.execute();
    }

    public static void main(String[] args) {
        GeneratorUtil.generate();
    }
}

