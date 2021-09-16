package com.liao.datascope.core.page;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liao.common.utils.StringUtils;
import com.liao.common.utils.sql.SqlUtil;

/**
 * <p>
 * 分页工具类 MyBatis-Plus
 * </p>
 *
 * @author LiAo
 * @since 2020/12/14
 */
public class PageUtils {

    /**
     * 获取分页参数
     *
     * @return 分页数据
     */
    public static <T> IPage<T> startPage() {
        PageDomain pageDomain = TableSupport.buildPageRequest();

        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isEmpty(pageNum)) {
            pageNum = 1;
        }

        if (StringUtils.isEmpty(pageSize)) {
            pageSize = 5;
        }

        return new Page<>(pageNum, pageSize);
    }

    /**
     * 获取分页参数
     *
     * @param current 页码
     * @param size    长度
     * @return 分页数据
     */
    public static IPage<?> startPage(Integer current, Integer size) {

        return new Page<>(current, size);
    }

    /**
     * 获取分页参数
     *
     * @return 分页数据
     */
    public static <T> IPage<T> startDefPage() {

        return new Page<>(1, 1);
    }


    /**
     * 获取排序
     *
     * @return 条件构造
     */
    public static <T> QueryWrapper<T> startOrderBy(QueryWrapper queryWrapper) {
        PageDomain pageDomain = TableSupport.buildPageRequest();

        // 校验SQL
        String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderByColumn());

        if (StringUtils.isNotEmpty(pageDomain.getIsAsc())) {
            queryWrapper.orderBy(
                    StringUtils.isNotEmpty(orderBy),
                    SqlUtil.getOrderBy(pageDomain.getIsAsc()),
                    pageDomain.getOrderByColumn()
            );
        }

        return queryWrapper;
    }
}
