package com.liao.framework.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.liao.common.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * <p>
 * MyBatis_Plus 自动注入
 * </p>
 *
 * @author LiAo
 * @since 2020/11/11
 */
@Slf4j
@Component
public class DataObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime", DateUtils.getNowDate(), metaObject);
        this.setFieldValByName("updateTime", DateUtils.getNowDate(), metaObject);


    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", DateUtils.getNowDate(), metaObject);
    }
}
