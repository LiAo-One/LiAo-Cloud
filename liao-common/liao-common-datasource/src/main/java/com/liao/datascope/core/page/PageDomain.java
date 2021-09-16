package com.liao.datascope.core.page;


import com.liao.common.utils.StringUtils;

/**
 * <p>
 * 分页数据
 * </p>
 *
 * @author LiAo
 * @since 2020/12/14
 */
public class PageDomain {

    // 页码
    private Integer pageNum;

    // 分页长度
    private Integer pageSize;

    // 排序列
    private String orderByColumn;

    // 排序方向
    private String isAsc;


    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderByColumn() {
        if (StringUtils.isEmpty(orderByColumn)) {
            return "";
        }

        return StringUtils.toUnderScoreCase(orderByColumn);
        /*return StringUtils.toUnderScoreCase(orderByColumn) + " " + isAsc;*/
    }

    public void setOrderByColumn(String orderByColumn) {
        this.orderByColumn = orderByColumn;
    }

    public String getIsAsc() {
        return isAsc;
    }

    public void setIsAsc(String isAsc) {
        this.isAsc = isAsc;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PageDomain{");
        sb.append("pageNum=").append(pageNum);
        sb.append(", pageSize=").append(pageSize);
        sb.append(", orderByColumn='").append(orderByColumn).append('\'');
        sb.append(", isAsc='").append(isAsc).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
