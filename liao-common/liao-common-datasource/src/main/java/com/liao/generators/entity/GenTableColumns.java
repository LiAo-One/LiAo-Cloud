package com.liao.generators.entity;

/**
 * 代码生成业务字段表 gen_table_column
 *
 * @author ruoyi
 */
public class GenTableColumns
{
    private static final long serialVersionUID = 1L;

    /** 列描述 */
    private String columnComment;

    /** JAVA字段名 */
    private String javaField;

    public void setColumnComment(String columnComment)
    {
        this.columnComment = columnComment;
    }

    public String getColumnComment()
    {
        return columnComment;
    }

    public void setJavaField(String javaField)
    {
        this.javaField = javaField;
    }

    public String getJavaField()
    {
        return javaField;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("GenTableColumn{");
        sb.append(", columnComment='").append(columnComment).append('\'');
        sb.append(", javaField='").append(javaField).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
