package com.zyc.utils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 单例工厂（使用饿汉模式）
 * 
 * @author 杨卫兵
 * @version [V1.00, 2020年9月10日]
 * @since V1.00
 */
public class SingletonObjectFactory2
{
    private static Map<String, Object> objects;
    
    static
    {
        Properties props = new Properties();
        objects = new HashMap<>();
        InputStream stream = null;
        try
        {
            stream = SingletonObjectFactory2.class.getClassLoader().getResourceAsStream("objects.properties");
            props.load(stream);
            for(Object k:props.keySet()) {
                String key=(String)k;
                String className = props.getProperty(key);
                try
                {
                    Class<?> clz = Class.forName(className);
                    Object obj = clz.getConstructor().newInstance();
                    objects.put(key, obj);
                }
                catch (Exception ex)
                {
                }
            }
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
        
    }
    
    /**
     * 根据名称获取单例对象
     * 
     * @param name
     * @return
     */
    public static Object getObject(String name)
    {
        return objects.get(name);
    }
    
}
