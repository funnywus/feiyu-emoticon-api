package com.emoticon.tb.service;

import com.emoticon.tb.domain.TbStickerResource;

import java.util.List;

/**
 * 表情包资源Service接口
 *
 * @author feiyu
 * @date 2025-03-23
 */
public interface ITbStickerResourceService
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
    public List<TbStickerResource> selectTbStickerResourceList(TbStickerResource tbStickerResource);

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
     * 批量删除表情包资源
     *
     * @param ids 需要删除的表情包资源主键集合
     * @return 结果
     */
    public int deleteTbStickerResourceByIds(String[] ids);

    /**
     * 删除表情包资源信息
     *
     * @param id 表情包资源主键
     * @return 结果
     */
    public int deleteTbStickerResourceById(String id);

    /**
     * 批量查询表情包资源
     *
     * @param stickerIds 表情包ID列表
     * @return 表情包资源列表
     */
    List<TbStickerResource> selectBatchByStickerIds(List<String> stickerIds);

    /**
     * 批量查询表情包资源
     *
     * @param stickerIds 表情包ID列表
     * @param limit 限制数量
     * @return 表情包资源列表
     */
    List<TbStickerResource> selectBatchByStickerIdsWithLimit(List<String> stickerIds, Integer limit);


    List<TbStickerResource> selectTbStickerResourceListById(String id);

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
     * 批量添加资源到合集
     *
     * @param categoryId
     * @param stickerName 合集名
     * @param resources   资源列表
     * @return 结果
     */
    public int batchInsertTbStickerResource(String categoryId, String stickerName, List<TbStickerResource> resources);

    List<TbStickerResource> selectAppTbStickerResourceList(TbStickerResource tbStickerResource);

}
