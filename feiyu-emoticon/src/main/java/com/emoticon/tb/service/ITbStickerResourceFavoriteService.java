package com.emoticon.tb.service;

import com.emoticon.tb.domain.TbStickerResource;
import com.emoticon.tb.domain.TbStickerResourceFavorite;

import java.util.List;

/**
 * 表情包资源收藏Service接口
 *
 * @author feiyu
 * @date 2025-03-27
 */
public interface ITbStickerResourceFavoriteService
{
    /**
     * 查询表情包资源收藏
     *
     * @param id 表情包资源收藏主键
     * @return 表情包资源收藏
     */
    public TbStickerResourceFavorite selectTbStickerResourceFavoriteById(String id);

    /**
     * 查询表情包资源收藏列表
     *
     * @param tbStickerResourceFavorite 表情包资源收藏
     * @return 表情包资源收藏集合
     */
    public List<TbStickerResourceFavorite> selectTbStickerResourceFavoriteList(TbStickerResourceFavorite tbStickerResourceFavorite);

    /**
     * 新增表情包资源收藏
     *
     * @param tbStickerResourceFavorite 表情包资源收藏
     * @return 结果
     */
    public int insertTbStickerResourceFavorite(TbStickerResourceFavorite tbStickerResourceFavorite);

    /**
     * 修改表情包资源收藏
     *
     * @param tbStickerResourceFavorite 表情包资源收藏
     * @return 结果
     */
    public int updateTbStickerResourceFavorite(TbStickerResourceFavorite tbStickerResourceFavorite);

    /**
     * 批量删除表情包资源收藏
     *
     * @param ids 需要删除的表情包资源收藏主键集合
     * @return 结果
     */
    public int deleteTbStickerResourceFavoriteByIds(String[] ids);

    /**
     * 删除表情包资源收藏信息
     *
     * @param id 表情包资源收藏主键
     * @return 结果
     */
    public int deleteTbStickerResourceFavoriteById(String id);

    /**
     * 切换表情包收藏状态
     *
     * @param resourceId 表情包资源ID
     * @return 操作结果
     */
    public int toggleFavoriteStatus(String resourceId);

    /**
     * 获取资源收藏列表
     * @param stickerResourceFavorite
     * @return
     */
    List<TbStickerResource> selectFavoriteResourceList(TbStickerResourceFavorite stickerResourceFavorite);
}
