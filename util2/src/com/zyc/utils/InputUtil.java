package com.zyc.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 控制台输入工具类
 * <一句话功能简述>
 * 
 * @author 张宇辰
 * @version [V1.00, 2020年9月1日]
 * @see [相关类/方法]
 * @since V1.00
 */
public class InputUtil
{
    public static Scanner scanner;
    
    /**
     * 接受输入的一个整数
     * 
     * @return
     */
    public static int acceptInt()
    {
        int n = 0;
        do
        {
            try
            {
                n = scanner.nextInt();
                break;
            }
            catch (InputMismatchException ex)
            {
                scanner.next();
                System.out.println("输入的不是整数，请重新输入！");
            }
        } while (true);
        return n;
    }
    
    /**
     * 
     * 接受输入的一个整数,必须在min-max之间
     * 
     * @param min 最小数
     * @param max 最大数
     * @return
     */
    public static int acceptInt(int min, int max)
    {
        int n;
        do
        {
            n = acceptInt();
            if (n < min || n > max)
            {
                System.out.printf("请输入%d-%d之间的整数。\n", min, max);
                continue;
            }
            break;
        } while (true);
        return n;
    }
    
    public static float acceptFloat()
    {
        float n;
        do
        {
            try
            {
                n = scanner.nextFloat();
                break;
            }
            catch (InputMismatchException ex)
            {
                scanner.next();
                System.out.println("输入的不是float类型，请重新输入！");
            }
        } while (true);
        return n;
    }
    
    public static double acceptDouble()
    {
        double n;
        do
        {
            try
            {
                n = scanner.nextDouble();
                break;
            }
            catch (InputMismatchException ex)
            {
                scanner.next();
                System.out.println("输入的不是double类型数据，请重新输入！");
            }
        } while (true);
        return n;
    }
    
    public static String acceptString()
    {
        String n = scanner.next();
        return n;
    }
    
    public static boolean acceptBoolean()
    {
        boolean n;
        do
        {
            try
            {
                n = scanner.nextBoolean();
                break;
            }
            catch (InputMismatchException ex)
            {
                scanner.next();
                System.out.println("输入的不是boolean类型数据，请重新输入！");
            }
        } while (true);
        return n;
    }
    
    /**
     * 接受日期对象（仅日期）
     * 
     * @return
     */
    public static Date acceptDate()
    {
        Date date = null;
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        fmt.setLenient(false);
        do
        {
            try
            {
                date = fmt.parse(scanner.next());
                break;
            }
            catch (Exception ex)
            {
                System.out.println("输入的日期格式不是yyyy-MM-dd，请重新输入！");
            }
        } while (true);
        return date;
    }
    
    /**
     * 接受日期对象（包括日期、时间）
     * 
     * @return
     */
    public static Date acceptDateTime()
    {
        Date date = null;
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        fmt.setLenient(false);
        do
        {
            try
            {
                date = fmt.parse(scanner.nextLine());
                break;
            }
            catch (Exception ex)
            {
                System.out.println("输入的日期格式不是yyyy-MM-dd HH:mm:ss，请重新输入！");
            }
        } while (true);
        return date;
    }
}
