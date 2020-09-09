package com.zyc.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
/**
 * 
 * <一句话功能简述>
 *  
 * @author  张宇辰
 * @version  [V1.00, 2020年9月8日]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class SerializtionUtil
{
    public static void save(Object object, String filename)
        throws Exception
    {
        
        OutputStream os = null;
        ObjectOutputStream oos = null;
        try
        {
            os = new FileOutputStream(filename);
            oos = new ObjectOutputStream(os);
            oos.writeObject(object);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            try
            {
                oos.close();
            }
            catch (Exception e)
            {
            }
            try
            {
                os.close();
            }
            catch (Exception e)
            {
            }
        }
        
    }
    
    public static Object load(String filename)
        throws Exception
    {
        
        InputStream is = null;
        ObjectInputStream ois = null;
        try
        {
            is = new FileInputStream(filename);
            ois = new ObjectInputStream(is);
            return ois.readObject();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            try
            {
                ois.close();
            }
            catch (Exception e)
            {
            }
            try
            {
                is.close();
            }
            catch (Exception e)
            {
            }
        }
        return null;
        
    }
}
