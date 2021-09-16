package com.liao.common.utils;

/**
 * <p>
 * 处理日志文件
 * </p>
 *
 * @author LiAo
 * @since 2020/12/30
 */
public class LogUtils {
    public static String getBlock(Object msg) {
        if (msg == null) {
            msg = "";
        }
        return "[" + msg.toString() + "]";
    }
}
