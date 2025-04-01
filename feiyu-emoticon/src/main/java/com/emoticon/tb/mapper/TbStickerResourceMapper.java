package com.emoticon.tb.mapper;

import com.emoticon.tb.domain.TbStickerResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 表情包资源Mapper接口
 *
 * @author feiyu
 * @date 2025-03-23
 */
public interface TbStickerResourceMapper
{
    /**
     * 查询表情包资源
     *
     * @param id 表情包资源主键
     * @return 表情包资源
     */
    public TbStickerResource selectTbStickerResourceById(String id);

    /**
     * 查询表情包资源列表
     *
     * @param tbStickerResource 表情包资源
     * @return 表情包资源集合
     */
    public List<TbStickerResource> selectTbStickerResourceList(TbStickerResource tbStickerResource);    /**

    /**
     * 查询app表情包资源列表 搜索
     *
     * @param tbStickerResource 表情包资源
     * @return 表情包资源集合
     */
    public List<TbStickerResource> selectAppTbStickerResourceList(TbStickerResource tbStickerResource);

    /**
     * 新增表情包资源
     *
     * @param tbStickerResource 表情包资源
     * @return 结果
     */
    public int insertTbStickerResource(TbStickerResource tbStickerResource);

    /**
     * 修改表情包资源
     *
     * @param tbStickerResource 表情包资源
     * @return 结果
     */
    public int updateTbStickerResource(TbStickerResource tbStickerResource);

    /**
     * 删除表情包资源
     *
     * @param id 表情包资源主键
     * @return 结果
     */
    public int deleteTbStickerResourceById(String id);

    /**
     * 批量删除表情包资源
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbStickerResourceByIds(String[] ids);

    /**
     * 根据stickerIdd查询表情包资源
     * @param stickerId
     * @return
     */
    List<TbStickerResource> selectTbStickerResourceListByStickerId(@Param("stickerId") String stickerId);

    List<TbStickerResource> selectBatchByStickerIds(@Param("list") List<String> stickerIds);

    List<TbStickerResource> selectBatchByStickerIdsWithLimit(@Param("list") List<String> stickerIds, @Param("limit") Integer limit);

    /**
     * 根据贴纸ID查询指定数量的资源
     * @param stickerId 贴纸ID
     * @param limit 返回的最大数量
     * @return 表情包资源列表
     */
    List<TbStickerResource> selectByStickerIdWithLimit(@Param("stickerId") String stickerId, @Param("limit") Integer limit);

    /**
     * 增加分享次数
     *
     * @param id 表情包资源ID
     * @return 结果
     */
    public int increaseShareCount(String id);

    /**
     * 增加下载次数
     *
     * @param id 表情包资源ID
     * @return 结果
     */
    public int increaseDownloadCount(String id);

    /**
     * 批量插入表情包资源
     *
     * @param resources 表情包资源列表
     * @return 插入成功的记录数
     */
    int batchInsert(@Param("list") List<TbStickerResource> resources);

    /**
     * 根据ID数组批量查询表情包资源
     *
     * @param ids 表情包资源ID数组
     * @return 表情包资源列表
     */
    List<TbStickerResource> selectTbStickerResourceByIds(String[] ids);
}
