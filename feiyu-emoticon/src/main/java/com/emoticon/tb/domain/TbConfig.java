package com.emoticon.tb.domain;

import com.emoticon.common.annotation.Excel;
import com.emoticon.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 全局配置对象 tb_config
 *
 * @author feiyu
 * @date 2025-03-22
 */
public class TbConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 配置ID，自增 */
    private Long id;

    /** 小程序名称 */
    @Excel(name = "小程序名称")
    private String appName;

    /** 分享标题 */
    @Excel(name = "分享标题")
    private String shareTitle;

    /** 分享图片URL */
    @Excel(name = "分享图片URL")
    private String shareImage;

    /** 分享描述 */
    @Excel(name = "分享描述")
    private String shareDescription;

    /** 分享状态：1=开启，0=关闭 */
    @Excel(name = "分享状态：1=开启，0=关闭")
    private Integer shareStatus;

    /** 关于我们类型：web=网页，richtext=富文本 */
    @Excel(name = "关于我们类型：web=网页，richtext=富文本")
    private String aboutType;

    /** 关于我们的内容（如果是网页，则存储URL；如果是富文本，则存HTML） */
    @Excel(name = "关于我们的内容", readConverterExp = "如=果是网页，则存储URL；如果是富文本，则存HTML")
    private String aboutContent;

    /** 隐藏数据（JSON字符串） */
    @Excel(name = "隐藏数据")
    private String hiddenData;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setAppName(String appName)
    {
        this.appName = appName;
    }

    public String getAppName()
    {
        return appName;
    }
    public void setShareTitle(String shareTitle)
    {
        this.shareTitle = shareTitle;
    }

    public String getShareTitle()
    {
        return shareTitle;
    }
    public void setShareImage(String shareImage)
    {
        this.shareImage = shareImage;
    }

    public String getShareImage()
    {
        return shareImage;
    }
    public void setShareDescription(String shareDescription)
    {
        this.shareDescription = shareDescription;
    }

    public String getShareDescription()
    {
        return shareDescription;
    }
    public void setShareStatus(Integer shareStatus)
    {
        this.shareStatus = shareStatus;
    }

    public Integer getShareStatus()
    {
        return shareStatus;
    }
    public void setAboutType(String aboutType)
    {
        this.aboutType = aboutType;
    }

    public String getAboutType()
    {
        return aboutType;
    }
    public void setAboutContent(String aboutContent)
    {
        this.aboutContent = aboutContent;
    }

    public String getAboutContent()
    {
        return aboutContent;
    }

    public void setHiddenData(String hiddenData)
    {
        this.hiddenData = hiddenData;
    }

    public String getHiddenData()
    {
        return hiddenData;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("appName", getAppName())
            .append("shareTitle", getShareTitle())
            .append("shareImage", getShareImage())
            .append("shareDescription", getShareDescription())
            .append("shareStatus", getShareStatus())
            .append("aboutType", getAboutType())
            .append("aboutContent", getAboutContent())
            .append("hiddenData", getHiddenData())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
