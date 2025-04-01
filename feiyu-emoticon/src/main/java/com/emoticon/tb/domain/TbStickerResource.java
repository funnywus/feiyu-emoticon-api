package com.emoticon.tb.domain;

import com.emoticon.common.annotation.Excel;
import com.emoticon.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 表情包资源对象 tb_sticker_resource
 *
 * @author feiyu
 * @date 2025-03-23
 */
@Data
public class TbStickerResource extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 资源ID */
    private String id;

    /** 表情包ID */
    @Excel(name = "表情包ID")
    private String stickerId;

    /** 分类ID */
    @Excel(name = "分类ID")
    private String categoryId;

    /** 资源名称 */
    @Excel(name = "资源名称")
    private String name;

    /** 资源URL */
    @Excel(name = "资源URL")
    private String imageUrl;

    /** 文件类型（image/png, image/gif, video/mp4） */
    @Excel(name = "文件类型", readConverterExp = "i=mage/png,,i=mage/gif,,v=ideo/mp4")
    private String fileType;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 点赞数 */
    @Excel(name = "点赞数")
    private Long likes;

    /** 下载次数 */
    @Excel(name = "下载次数")
    private Long downloads;

    /** 浏览次数 */
    @Excel(name = "浏览次数")
    private Long views;

    /** 分享次数 */
    @Excel(name = "分享次数")
    private Long shareCount;

    /** 排序字段 */
    @Excel(name = "排序字段")
    private Long sortOrder;

    /**
     * 是否收藏
     */
    private Boolean isCollected;

    private Long userId;
}
