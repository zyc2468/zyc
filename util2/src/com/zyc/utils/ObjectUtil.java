package com.zyc.utils;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import com.sun.javafx.fxml.PropertyNotFoundException;

/**
 * 
 * 产生对象：通过配置文件，格式如下
 * class=xxx.xxx.xxx.Xxx
 * 属性名1=属性值1
 * 属性名2=属性值2
 * 属性名3=属性值3
 * ....
 * 
 * @author 张宇辰
 * @version [V1.00, 2020年9月8日]
 * @see [相关类/方法]
 * @since V1.00
 */
public class ObjectUtil
{
    /**
     * 创建对象
     * 1.加载文件
     * 2.创建对象
     * 3.设置属性
     *      A.根据属性名查找相应的setter方法
     *      B.根据参数类型将字符串转为对应类型数据
     *      C.执行setter方法
     * @param propFileName  配置文件名
     * @return
     */
    public static Object createObject(String propFileName) {
        InputStream is=null;
        Properties props=new Properties();
        try {
            is=ObjectUtil.class.getClassLoader().getResourceAsStream(propFileName);
            props.load(is);
            String className=props.getProperty("class");
            Class<?> clz=Class.forName(className);
            Object obj=clz.getConstructor().newInstance();
            for(Object k:props.keySet()) {
                String key=(String)k;
                if("class".equals(key)) {
                    continue;
                }
                Method mth=getSetterByPropertyName(clz, key);
                Object value=convertToObject(props.getProperty(key),mth.getParameterTypes()[0]);
                mth.invoke(obj, value);
            }
            return obj;
        }
        catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
        finally {
            try
            {
                is.close();
            }
            catch (Exception e)
            {}
        }
    }
    
    /**
     * 根据参数类型将字符串转为对应类型数据
     * @param src
     * @param target
     * @return
     */
    public static Object convertToObject(String src,Class<?> target) {
        try {
            if(target==Character.class) {
                return Character.valueOf(src.charAt(0));
            }
            if(target==String.class) {
                return src;
            }
            if(target==Date.class) {
                SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd");
                return fmt.parse(src);
            }
            Method valueOf=target.getMethod("valueOf",String.class);
            return valueOf.invoke(null, src);
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 根据属性名获取相应的setter
     * @param clz           类型
     * @param propName      属性名
     * @return
     */
    public static Method getSetterByPropertyName(Class<?> clz,String propName) {
        String methodName="set"+propName.substring(0,1).toUpperCase()+propName.substring(1);
        Field fld=getField(clz, propName);
        if(fld==null) {
            return null;
        }
        Method mth=null;
        try {
            mth=clz.getMethod(methodName,fld.getType());
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return mth;
    }
    
    /**
     * 根据属性名查找Field对象
     * @param clz
     * @param fieldName
     * @return
     */
    public static Field getField(Class<?> clz,String fieldName){
        if(clz==null || clz==Object.class) {
            return null;
        }
        try {
            return clz.getDeclaredField(fieldName);
        }
        catch(Exception ex) {
            return getField(clz.getSuperclass(),fieldName);
        }
    }
    
    /**
     * 给对象（第一个参数）设置属性
     * 第二个参数，其中key为属性名，value为属性值
     * @param object
     */
    public static void setProperties(Object object,
        Map<String,Object> values) {
        Class<?> clz=object.getClass();
        for(Map.Entry<String, Object> entry:values.entrySet()) {
            String setter=entry.getKey();
            setter="set"+setter.substring(0,1).toUpperCase()+setter.substring(1);
            Method mth=null;
            try {
                mth=clz.getMethod(setter, entry.getValue().getClass());
                mth.invoke(object, entry.getValue());
            }catch(Exception ex) {
                
            }
        }
    }
    
    /**
     * 获取指定对象（第一个参数）指定属性的值（第二个参数），
     * 如果无此属性，则抛出自定义异常PropertyNotFoundException
     * @param object
     * @param prop
     * @return
     * @throws PropertyNotFoundException
     */
    public static Object getPropertyValue(Object object,String prop) throws PropertyNotFoundException{
        String getter="get"+prop.substring(0,1).toUpperCase()+prop.substring(1);
        Class<?> clz=object.getClass();
        Method mth=null;
        try {
            mth=clz.getMethod(getter);
        }
        catch(Exception ex) {
            getter="is"+prop.substring(0,1).toUpperCase()+prop.substring(1);
            try {
                mth=clz.getMethod(getter);
            }
            catch(Exception e) {}
        }
        if(mth==null) {
            throw new PropertyNotFoundException(prop);
        }
        try {
            return mth.invoke(object);
        }
        catch(Exception ex) {
            throw new PropertyNotFoundException(prop);
        }
        
    }
}
