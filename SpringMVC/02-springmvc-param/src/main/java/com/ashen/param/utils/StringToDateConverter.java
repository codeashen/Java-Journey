package com.ashen.param.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义类型转换器，字符串转日期
 */
public class StringToDateConverter implements Converter<String, Date> {
    /**
     * 转换方法，返回目标类型
     * @param source 转换前对象
     * @return 转换后对象
     */
    @Override
    public Date convert(String source) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
