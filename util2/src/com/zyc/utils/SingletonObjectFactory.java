package com.zyc.utils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 单例工厂（使用懒汉模式）
 * 
 * @author 杨卫兵
 * @version [V1.00, 2020年9月10日]
 * @since V1.00
 */
public class SingletonObjectFactory
{
    private static Map<String, Object> objects;
    
    private static Properties props;// <String,String>
    
    static
    {
        props = new Properties();
        InputStream stream = null;
        try
        {
            stream = SingletonObjectFactory.class.getClassLoader().getResourceAsStream("objects.properties");
            props.load(stream);
        }
        catch (Exception ex)
        {
            
        }
        finally
        {
            try
            {
                stream.close();
            }
            catch (Exception e)
            {
            }
        }
        objects = new HashMap<>();
    }
    
    /**
     * 根据名称获取单例对象
     * 
     * @param name
     * @return
     */
    public static Object getObject(String name)
    {
        Object obj = objects.get(name);
        if (obj == null)
        {
            String className = props.getProperty(name);
            try
            {
                Class<?> clz = Class.forName(className);
                obj = clz.getConstructor().newInstance();
                objects.put(name, obj);
            }
            catch (Exception ex)
            {
            }
        }
        return obj;
    }
    
}
