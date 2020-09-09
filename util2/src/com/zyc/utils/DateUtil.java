package com.zyc.utils;

import java.util.Date;

/**
 * 日期工具类
 * <一句话功能简述>
 * 
 * @author 张宇辰
 * @version [V1.00, 2020年9月1日]
 * @see [相关类/方法]
 * @since V1.00
 */
public class DateUtil
{
    private static final long MILLIS_OF_DAY = 1000 * 3600 * 24;
    
    private static final long MILLIS_OF_TIMEZONE = 1000 * 3600 * 8;
    
    /**
     * 返回两个日期之间相差的天数（不考虑时间）
     * 
     * @param from
     * @param to
     * @return
     */
    public static int getDaysBetweenTwoDate(Date from, Date to)
    {
        long t1 = (from.getTime() + MILLIS_OF_TIMEZONE) / MILLIS_OF_DAY * MILLIS_OF_DAY;
        long t2 = (to.getTime() + MILLIS_OF_TIMEZONE) / MILLIS_OF_DAY * MILLIS_OF_DAY;
        int diff = (int)((t2 - t1) / MILLIS_OF_DAY);
        return Math.abs(diff);
    }
}
