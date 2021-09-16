package com.liao.common.enums;

/**
 * <p>
 * 步骤标题枚举
 * </p>
 *
 * @author LiAo
 * @since 2021/5/8
 */
public enum StepsType {

    CREATE("创建"),

    SUBMIT_NAME("提交名称"),

    REGISTERED("注册"),

    PULL("拉取");

    public final String value;

    StepsType(String value) {
        this.value = value;
    }
}
