package com.liao.common.core.text;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * <p>
 * 全局时间转化处理
 * </p>
 *
 * @author LiAo
 * @since 2021/4/26
 */
@Component
public class StringToUtilDateConverter implements Converter<String, Date> {

    static final String YYYY_MM_DD = "yyyy-MM-dd";
    static final String REG_YYYY_MM_DD = "[0-9]{4}[-]{1}[0-9]{2}[-]{1}[0-9]{2}";
    static final String REG_NUMBER = "[0-9]{1,}";


    @Override
    public Date convert(String timeStr) {
        if (StringUtils.isBlank(timeStr)) {
            return null;
        }
        timeStr = timeStr.trim();

        try {
            DateTimeFormatter formatter;
            if (Pattern.matches(StringToUtilDateConverter.REG_YYYY_MM_DD, timeStr)) {
                formatter = DateTimeFormatter.ofPattern(YYYY_MM_DD);
            } else if (Pattern.matches(StringToUtilDateConverter.REG_NUMBER, timeStr)) {
                Long lDate = new Long(timeStr);
                return new Timestamp(lDate);
            } else {
                throw new RuntimeException(String.format("parser %s to Date fail", timeStr));
            }
            LocalDate localDate = LocalDate.parse(timeStr, formatter);
            Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
            return Date.from(instant);
        } catch (Exception e) {
            throw new RuntimeException(String.format("parser %s to Date fail", timeStr));
        }
    }
}
