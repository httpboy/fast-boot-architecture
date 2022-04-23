package com.fast.mybatisplus.architechturespringmybatisplus.config;


import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.TreeSet;

public class ShardingUtils {


    @SneakyThrows
    public static TreeSet<String> getSuffixListForRange(String lowerSuffix, String upperSuffix) {
        TreeSet<String> suffixList = new TreeSet<>();
        if (lowerSuffix.equals(upperSuffix)) { //上下界在同一张表
            suffixList.add(lowerSuffix);
        } else {  //上下界不在同一张表  计算间隔的所有表
            String tempSuffix = lowerSuffix;
            while (!tempSuffix.equals(upperSuffix)) {
                suffixList.add(tempSuffix);
//                String[] ym = tempSuffix.split("_");
                Date tempDate = DateUtils.parseDate(tempSuffix, "yyyyMM");
                Calendar cal = Calendar.getInstance();
                cal.setTime(tempDate);
                cal.add(Calendar.MONTH, 1);
                tempSuffix = ShardingUtils.getSuffixByYearMonth(cal.getTime());
            }
            suffixList.add(tempSuffix);
        }
        return suffixList;
    }

    public static String getSuffixByYearMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String monthValue = calendar.get(Calendar.MONTH) + 1 < 10 ? "0" + (calendar.get(Calendar.MONTH) + 1) : calendar.get(Calendar.MONTH) + 1 + "";

        return calendar.get(Calendar.YEAR) + monthValue;
    }

    public static String getPrevSuffix(String suffix) {
        if (StringUtils.isEmpty(suffix)) {
            return getSuffixByYearMonth(new Date());
        }
        String[] arr = suffix.split("_");
        if ("1".equals(arr[1])) {
            return (Integer.valueOf(arr[0]) - 1) + "_12";
        } else {
            return arr[0] + "_" + (Integer.valueOf(arr[1]) - 1);
        }
    }

}