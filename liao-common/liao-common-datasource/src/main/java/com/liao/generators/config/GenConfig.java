package com.liao.generators.config;

import com.liao.generators.entity.GenTableColumns;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 代码生成配置类
 * </p>
 *
 * @author LiAo
 * @since 2020/12/30
 */
public class GenConfig {

    private static final long serialVersionUID = 1L;

    /**
     * 生成包路径
     */
    public String packageName;

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 表描述
     */
    private String tableComment;

    /**
     * 实体类名称(首字母大写)
     */
    private String className;

    /**
     * 生成文件名
     */
    private String finleName;

    /**
     * 生成路径（不填默认项目路径）
     */
    private String genPath;

    /**
     * 表列信息
     */
    private List<GenTableColumns> columns = new ArrayList<GenTableColumns>();


    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getFinleName() {
        return finleName;
    }

    public void setFinleName(String finleName) {
        this.finleName = finleName;
    }

    public String getGenPath() {
        return genPath;
    }

    public void setGenPath(String genPath) {
        this.genPath = genPath;
    }

    public List<GenTableColumns> getColumns() {
        return columns;
    }

    public void setColumns(List<GenTableColumns> columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("GenConfig{");
        sb.append("packageName='").append(packageName).append('\'');
        sb.append(", tableName='").append(tableName).append('\'');
        sb.append(", tableComment='").append(tableComment).append('\'');
        sb.append(", className='").append(className).append('\'');
        sb.append(", finleName='").append(finleName).append('\'');
        sb.append(", genPath='").append(genPath).append('\'');
        sb.append(", columns=").append(columns);
        sb.append('}');
        return sb.toString();
    }
}
