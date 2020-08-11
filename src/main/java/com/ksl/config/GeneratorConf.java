package com.ksl.config;

/**
 * @author ksl
 * @Description TODO
 * @data 2021/05/12
 */
public class GeneratorConf {
    /**
     * 全局配置
     */
    public static String author = "ksl";
    public static String mapperName = "%sMapper";
    public static String serviceName = "%sService";
    public static String serviceImplName = "%sServiceImpl";
    public static String xmlName = "%sMapper";


    /**
     * 数据源配置 mysql
     */
    public static String url = "jdbc:mysql://112.74.101.55:3306/ksl-cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8";
    public static String driverName = "com.mysql.cj.jdbc.Driver";
    public static String username = "root";
    public static String password = "Ksl123456!";

    /**
     * 包配置
     */
    public static String basePackage = "com.ksl.product";
    public static String controllerPackageName = "controller";
    public static String servicePackageName = "service";
    public static String serviceImplPackageName = "service.Impl";
    public static String mapperPackageName = "mapper";
    public static String xmlPackageName = "mapper";
    public static String entityPackageName = "entity";

    /**
     * 配置模板
     */
    public static String controllerTemplatePath = "/template/controller.java";
    public static String serviceTemplatePath = "/template/service.java";
    public static String serviceImplTemplatePath = "/template/serviceImpl.java";
    public static String mapperTemplatePath = "/template/mapper.java";
    public static String xmlTemplatePath = "/template/mapper.xml.vm";
    public static String entityTemplatePath = "/template/entity.java";

    /**
     * 策略配置
     * 要逆向生成的表名
     * 单个表名{"email"}; 多个表名 {"email","leaves","record","user"};
     */
    public static String[] tableNames = {"sku","product_category","product_brand","brand_related_category","brand_related_sku","product_element","comb_sku",
            "comb_related_sku","sku_stock","product_property","product_property_exp","external_sku",
            "unit","unit_conversion","product_dict_data","product_dict_type","sku_code_rule","sku_same_related"};
}
