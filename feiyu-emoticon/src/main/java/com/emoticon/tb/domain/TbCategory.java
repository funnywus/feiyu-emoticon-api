package com.emoticon.tb.domain;

import com.emoticon.common.annotation.Excel;
import com.emoticon.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 表情包分类对象 tb_category
 *
 * @author feiyu
 * @date 2025-03-22
 */
public class TbCategory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 分类ID */
    private String id;

    /** 分类名称 */
    @Excel(name = "分类名称")
    private String name;

    /** 分类描述，可选 */
    @Excel(name = "分类描述，可选")
    private String description;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 排序字段，数值越小越靠前 */
    @Excel(name = "排序字段，数值越小越靠前")
    private Long sortOrder;

    /** 子合集排序字段 */
    @Excel(name = "子合集排序字段")
    private String subSortOrder;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDescription()
    {
        return description;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }
    public void setSortOrder(Long sortOrder)
    {
        this.sortOrder = sortOrder;
    }

    public Long getSortOrder()
    {
        return sortOrder;
    }

    public void setSubSortOrder(String subSortOrder)
    {
        this.subSortOrder = subSortOrder;
    }

    public String getSubSortOrder()
    {
        return subSortOrder;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("description", getDescription())
            .append("status", getStatus())
            .append("sortOrder", getSortOrder())
            .append("subSortOrder", getSubSortOrder())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
