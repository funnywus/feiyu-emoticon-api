package com.emoticon.tb.domain;

import com.emoticon.common.annotation.Excel;
import com.emoticon.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 表情包资源收藏对象 tb_sticker_resource_favorite
 *
 * @author feiyu
 * @date 2025-03-27
 */
public class TbStickerResourceFavorite extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 收藏ID */
    private String id;

    /** 用户ID，表示哪个用户收藏了资源 */
    @Excel(name = "用户ID，表示哪个用户收藏了资源")
    private Long userId;

    /** 表情包资源ID，关联 tb_sticker_resource */
    @Excel(name = "表情包资源ID，关联 tb_sticker_resource")
    private String stickerResourceId;

    /** 表情包ID，关联 tb_sticker */
    @Excel(name = "表情包ID，关联 tb_sticker")
    private String stickerId;

    /** 分类ID，关联 tb_category */
    @Excel(name = "分类ID，关联 tb_category")
    private String categoryId;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setStickerResourceId(String stickerResourceId)
    {
        this.stickerResourceId = stickerResourceId;
    }

    public String getStickerResourceId()
    {
        return stickerResourceId;
    }
    public void setStickerId(String stickerId)
    {
        this.stickerId = stickerId;
    }

    public String getStickerId()
    {
        return stickerId;
    }
    public void setCategoryId(String categoryId)
    {
        this.categoryId = categoryId;
    }

    public String getCategoryId()
    {
        return categoryId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("stickerResourceId", getStickerResourceId())
            .append("stickerId", getStickerId())
            .append("categoryId", getCategoryId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
