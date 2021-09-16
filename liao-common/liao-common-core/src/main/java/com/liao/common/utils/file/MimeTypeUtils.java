package com.liao.common.utils.file;

/**
 * <p>
 * 媒体常量
 * </p>
 *
 * @author LiAo
 * @since 2021/4/29
 */
public class MimeTypeUtils {

    public static final String[] DEFAULT_ALLOWED_EXTENSION = {
            // 图片
            "bmp", "gif", "jpg", "jpeg", "png",
            // word excel powerpoint
            "doc", "docx", "xls", "xlsx", "ppt", "pptx", "html", "htm", "txt",
            // 压缩文件
            "rar", "zip", "gz", "bz2",
            // pdf
            "pdf" };
}
