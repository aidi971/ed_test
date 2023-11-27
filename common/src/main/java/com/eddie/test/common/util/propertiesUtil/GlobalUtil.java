package com.eddie.test.common.util.propertiesUtil;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author eddie
 * @date 2023/11/27 14:15
 * @description 用于静态工具类读取配置文件
 **/
public class GlobalUtil {
    private GlobalUtil() {}

    /**
     * 当前对象实例
     */
    private static GlobalUtil global = null;

    /**
     * 静态工厂方法 获取当前对象实例 多线程安全单例模式(使用双重同步锁)
     */

    public static synchronized GlobalUtil getInstance() {

        if (global == null) {
            synchronized (GlobalUtil.class) {
                if (global == null) {
                    global = new GlobalUtil();
                }
            }
        }
        return global;
    }

    /**
     * 保存全局属性值
     */
    private static Map<String, String> map = new HashMap<>();

    /**
     * 属性文件加载对象
     */
    private static PropertiesLoader loader = new PropertiesLoader("application-datadriver.properties",
            "application.properties","log4j.properties");

    /**
     * 获取配置内容
     * 例如: getConfig("LOG_LEVEL") =“Error”
     *
     */
    public static String getConfig(String key) {
        String value = map.get(key);
        if (value == null) {
            value = loader.getProperty(key);
            map.put(key, value != null ? value : StringUtils.EMPTY);
        }
        return value;
    }
}
