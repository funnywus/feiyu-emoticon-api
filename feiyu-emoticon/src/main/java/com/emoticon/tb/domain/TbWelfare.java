package com.emoticon.tb.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.emoticon.common.annotation.Excel;
import com.emoticon.common.core.domain.BaseEntity;

/**
 * 福利列表对象 tb_welfare
 *
 * @author feiyu
 * @date 2025-03-22
 */
public class TbWelfare extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 福利ID */
    private Long id;

    /** 福利图片 */
    @Excel(name = "福利图片")
    private String welfareImage;

    /** 跳转类型 */
    @Excel(name = "跳转类型")
    private String jumpType;

    /** 跳转地址 */
    @Excel(name = "跳转地址")
    private String jumpUrl;

    /** AppID */
    @Excel(name = "AppID")
    private String appId;

    /** 排序 */
    @Excel(name = "排序")
    private Long sortOrder;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 按钮文字，如“ */
    @Excel(name = "按钮文字，如“")
    private String buttonText;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setWelfareImage(String welfareImage)
    {
        this.welfareImage = welfareImage;
    }

    public String getWelfareImage()
    {
        return welfareImage;
    }
    public void setJumpType(String jumpType)
    {
        this.jumpType = jumpType;
    }

    public String getJumpType()
    {
        return jumpType;
    }
    public void setJumpUrl(String jumpUrl)
    {
        this.jumpUrl = jumpUrl;
    }

    public String getJumpUrl()
    {
        return jumpUrl;
    }
    public void setAppId(String appId)
    {
        this.appId = appId;
    }

    public String getAppId()
    {
        return appId;
    }
    public void setSortOrder(Long sortOrder)
    {
        this.sortOrder = sortOrder;
    }

    public Long getSortOrder()
    {
        return sortOrder;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }
    public void setButtonText(String buttonText)
    {
        this.buttonText = buttonText;
    }

    public String getButtonText()
    {
        return buttonText;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("welfareImage", getWelfareImage())
            .append("jumpType", getJumpType())
            .append("jumpUrl", getJumpUrl())
            .append("appId", getAppId())
            .append("sortOrder", getSortOrder())
            .append("status", getStatus())
            .append("buttonText", getButtonText())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
