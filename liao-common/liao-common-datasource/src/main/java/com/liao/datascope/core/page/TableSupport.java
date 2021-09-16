package com.liao.datascope.core.page;

import com.liao.common.constant.Constants;
import com.liao.common.utils.ServletUtils;

/**
 * <p>
 * 分页数据处理
 * </p>
 *
 * @author LiAo
 * @since 2020/12/14
 */
public class TableSupport {

    public static PageDomain getPageDomain(){
        PageDomain pageDomain =new PageDomain();
        // 页码
        pageDomain.setPageNum(ServletUtils.getParameterToInt(Constants.PAGE_NUM));
        // 长度
        pageDomain.setPageSize(ServletUtils.getParameterToInt(Constants.PAGE_SIZE));
        // 排序列
        pageDomain.setOrderByColumn(ServletUtils.getParameter(Constants.ORDER_BY_COLUMN));
        // 排序方向
        pageDomain.setIsAsc(ServletUtils.getParameter(Constants.IS_ASC));

        return pageDomain;
    }

    public static PageDomain buildPageRequest(){
        return getPageDomain();
    }
}
