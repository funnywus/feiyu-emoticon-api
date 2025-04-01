package com.emoticon.tb.domain;

import com.emoticon.common.annotation.Excel;
import com.emoticon.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.List;

/**
 * 表情包对象 tb_sticker
 *
 * @author feiyu
 * @date 2025-03-22
 */
@Data
public class TbSticker extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 表情包ID */
    private String id;

    /** 上传表情包的用户ID，关联用户表 */
    @Excel(name = "上传表情包的用户ID，关联用户表")
    private Long userId;

    /** 所属分类ID，关联 tb_category */
    @Excel(name = "所属分类ID，关联 tb_category")
    private String categoryId;

    /** 表情包名称 */
    @Excel(name = "表情包名称")
    private String name;

    /** 表情包描述，可选 */
    @Excel(name = "表情包描述，可选")
    private String description;

    /** 表情包的图片URL，逗号分隔 */
    @Excel(name = "表情包的图片URL，逗号分隔")
    private String images;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 表情包标签，逗号分隔 */
    @Excel(name = "表情包标签，逗号分隔")
    private String tags;

    /** 点赞数，默认0 */
    @Excel(name = "点赞数，默认0")
    private Long likes;

    /** 下载次数，默认0 */
    @Excel(name = "下载次数，默认0")
    private Long downloads;

    /** 浏览次数，默认0 */
    @Excel(name = "浏览次数，默认0")
    private Long views;

    /** 排序字段，数值越小越靠前 */
    @Excel(name = "排序字段，数值越小越靠前")
    private Long sortOrder;

    /** 分享次数，默认0 */
    @Excel(name = "分享次数，默认0")
    private Long shareCount;

    /** 子合集排序字段 不传就按照热门展示 1: 最新，2：热门 */
    @Excel(name = "子合集排序字段")
    private Long subSortOrder;

    /**
     * 表情包资源列表
     */
    List<TbStickerResource> stickerResourceList;
}
