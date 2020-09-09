package com.zyc.utils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

/**
 * 数据格式验证工具类
 * <一句话功能简述>
 * 
 * @author 张宇辰
 * @version [V1.00, 2020年9月1日]
 * @see [相关类/方法]
 * @since V1.00
 */
public class ValidationUtil
{
    /**
     * 验证是否为整数：包括正负数
     * 
     * @param str
     * @return
     */
    public static boolean checkInteger(String str)
    {
        String regex = "[-+]?\\d+";
        return str != null && str.matches(regex);
    }
    
    /**
     * 验证是否为数字：包括小数 2.5 -3.7 5 -10
     * 
     * @param str
     * @return
     */
    public static boolean checkNumber(String str)
    {
        String regex = "[-+]?\\d+([.]\\d+)?";
        return str != null && str.matches(regex);
    }
    
    /**
     * 验证国内固定电话的格式：区号-号码 或者 (区号)号码
     * 025-88811111 (025)88889999
     * 
     * @param phone
     * @return
     */
    public static boolean checkPhone(String phone)
    {
//        String regex="0[1-9]\\d{1,2}-[1-9]\\d{6,7}";
//        String regex2="\\(0[1-9]\\d{1,2}\\)[1-9]\\d{6,7}";
//        return phone!=null && 
//           (phone.matches(regex) ||
//            phone.matches(regex2));
        String regex = "((0[1-9]\\d{1,2}-)|(\\(0[1-9]\\d{1,2}\\)))[1-9]\\d{6,7}";
        return phone != null && phone.matches(regex);
    }
    
    /**
     * 检查Email格式是否正确: 字母、数字、下划线@ss.cn
     * 
     * @param email Email
     * @return true：正确；false：不正确
     */
    public static boolean checkEmail(String email)
    {
        String regex = "\\w+@\\w+(\\.\\w+){0,2}";
        return email != null && email.matches(regex);
        
    }
    
    private static final List<String> prefix = Arrays.asList("13", "14", "15", "17", "18", "19");
    
    /**
     * 检查手机号格式是否正确
     * 13、14、15、17、18、19且11位
     * 
     * @return
     */
    public static boolean checkMobilePhone(String phone)
    {
        if (phone == null || phone.length() != 11)
        {
            return false;
        }
        String pre = phone.substring(0, 2);
        return prefix.contains(pre);
    }
    
    /**
     * 验证18位身份证号
     * 
     * @param id18
     * @return
     */
    public static boolean checkIdCard(String id18)
    {
        String rx = "[1-9]\\d{16}[0-9X]";
        
        if (id18 == null || !id18.matches(rx))
        {
            return false;
        }
        int[] arr = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
        int sum = 0;
        String id17 = id18.substring(0, 17);
        
        for (int i = 0; i < id17.length(); i++)
        {
            char ch = id17.charAt(i);
            sum += arr[i] * (ch - 48);
        }
        // 生日判断
        String birth = id18.substring(6, 14);
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        fmt.setLenient(false);
        try
        {
            fmt.parse(birth);
        }
        catch (Exception ex)
        {
            return false;
        }
        // 验证码判断
        String tails = "10X98765432";
        int remain = sum % 11;//
        return tails.charAt(remain) == id18.charAt(17);
    }
}
