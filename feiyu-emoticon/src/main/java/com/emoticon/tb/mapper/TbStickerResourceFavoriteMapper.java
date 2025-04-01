package com.emoticon.tb.mapper;

import com.emoticon.tb.domain.TbStickerResource;
import com.emoticon.tb.domain.TbStickerResourceFavorite;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 表情包资源收藏Mapper接口
 *
 * @author feiyu
 * @date 2025-03-27
 */
public interface TbStickerResourceFavoriteMapper
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
     * 删除表情包资源收藏
     *
     * @param id 表情包资源收藏主键
     * @return 结果
     */
    public int deleteTbStickerResourceFavoriteById(String id);

    /**
     * 批量删除表情包资源收藏
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbStickerResourceFavoriteByIds(String[] ids);

    /**
     * 根据用户ID和资源ID查询收藏记录
     */
    TbStickerResourceFavorite selectByUserIdAndResourceId(@Param("userId") Long userId, @Param("stickerResourceId") String stickerResourceId);

    /**
     * 获取资源收藏列表
     * @param stickerResourceFavorite
     * @return
     */
    List<TbStickerResource> selectFavoriteResourceList(TbStickerResourceFavorite stickerResourceFavorite);
}
