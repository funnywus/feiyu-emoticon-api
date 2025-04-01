package com.emoticon.tb.service.impl;

import com.emoticon.common.utils.DateUtils;
import com.emoticon.common.utils.IdUtil;
import com.emoticon.tb.domain.TbSticker;
import com.emoticon.tb.domain.TbStickerResource;
import com.emoticon.tb.mapper.TbStickerResourceMapper;
import com.emoticon.tb.service.ITbStickerResourceFavoriteService;
import com.emoticon.tb.service.ITbStickerResourceService;
import com.emoticon.tb.service.ITbStickerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 表情包资源Service业务层处理
 *
 * @author feiyu
 * @date 2025-03-23
 */
@Service
public class TbStickerResourceServiceImpl implements ITbStickerResourceService
{
    @Autowired
    private TbStickerResourceMapper stickerResourceMapper;
    @Autowired
    private ITbStickerService stickerService;
    @Autowired
    private ITbStickerResourceFavoriteService stickerResourceFavoriteService;


    /**
     * 查询表情包资源
     *
     * @param id 表情包资源主键
     * @return 表情包资源
     */
    @Override
    public TbStickerResource selectTbStickerResourceById(String id)
    {
        return stickerResourceMapper.selectTbStickerResourceById(id);
    }

    /**
     * 查询表情包资源列表
     *
     * @param tbStickerResource 表情包资源
     * @return 表情包资源
     */
    @Override
    public List<TbStickerResource> selectTbStickerResourceList(TbStickerResource tbStickerResource)
    {
        List<TbStickerResource> stickerResourceList = stickerResourceMapper.selectTbStickerResourceList(tbStickerResource);
        return stickerResourceList;
    }

    /**
     * 新增表情包资源
     *
     * @param tbStickerResource 表情包资源
     * @return 结果
     */
    @Override
    public int insertTbStickerResource(TbStickerResource tbStickerResource)
    {
        tbStickerResource.setCreateTime(DateUtils.getNowDate());
        return stickerResourceMapper.insertTbStickerResource(tbStickerResource);
    }

    /**
     * 修改表情包资源
     *
     * @param tbStickerResource 表情包资源
     * @return 结果
     */
    @Override
    public int updateTbStickerResource(TbStickerResource tbStickerResource)
    {
        tbStickerResource.setUpdateTime(DateUtils.getNowDate());
        return stickerResourceMapper.updateTbStickerResource(tbStickerResource);
    }

    /**
     * 批量删除表情包资源
     *
     * @param ids 需要删除的表情包资源主键
     * @return 结果
     */
    @Override
    public int deleteTbStickerResourceByIds(String[] ids)
    {
        return stickerResourceMapper.deleteTbStickerResourceByIds(ids);
    }

    /**
     * 删除表情包资源信息
     *
     * @param id 表情包资源主键
     * @return 结果
     */
    @Override
    public int deleteTbStickerResourceById(String id)
    {
        return stickerResourceMapper.deleteTbStickerResourceById(id);
    }

    public List<TbStickerResource> selectBatchByStickerIds(List<String> stickerIds) {
        if (stickerIds == null || stickerIds.isEmpty()) {
            return new ArrayList<>();
        }
        return stickerResourceMapper.selectBatchByStickerIds(stickerIds);
    }

    /**
     * 根据贴纸ID批量查询并限制每个ID返回数量
     * @param stickerIds 贴纸ID列表
     * @param limit 每个贴纸ID返回的最大数量
     * @return 表情包资源列表
     */
    @Override
    public List<TbStickerResource> selectBatchByStickerIdsWithLimit(List<String> stickerIds, Integer limit) {
        if (stickerIds == null || stickerIds.isEmpty()) {
            return new ArrayList<>();
        }
        return stickerResourceMapper.selectBatchByStickerIdsWithLimit(stickerIds, limit);
    }

    public List<TbStickerResource> selectTbStickerResourceListById(String id) {
        return stickerResourceMapper.selectTbStickerResourceListByStickerId(id);
    }


    /**
     * 增加分享次数
     *
     * @param id 表情包资源ID
     * @return 结果
     */
    @Override
    public int increaseShareCount(String id) {
        return stickerResourceMapper.increaseShareCount(id);
    }

    /**
     * 增加下载次数
     *
     * @param id 表情包资源ID
     * @return 结果
     */
    @Override
    public int increaseDownloadCount(String id) {
        return stickerResourceMapper.increaseDownloadCount(id);
    }

    @Override
    public int batchInsertTbStickerResource(String categoryId, String stickerName, List<TbStickerResource> resources) {
        if (resources == null || resources.isEmpty()) {
            return 0;
        }

        // 收集所有非空的资源ID
        List<String> resourceIds = resources.stream()
            .map(TbStickerResource::getId)
            .collect(Collectors.toList());

        // 查询数据库中已存在的资源
        List<TbStickerResource> existingResources = new ArrayList<>();
        if (!resourceIds.isEmpty()) {
            existingResources = stickerResourceMapper.selectTbStickerResourceByIds(resourceIds.toArray(new String[0]));
        }
        if (existingResources.isEmpty()) {
            return 0;
        }

        TbSticker sticker = new TbSticker();
        sticker.setCategoryId(categoryId);
        sticker.setName(stickerName);
        sticker.setStatus(1);
        // 生成新的合集
        int is = stickerService.insertTbSticker(sticker);
        if (is == 0) {
            throw new RuntimeException("创建合集失败");
        }
        String stickerId = sticker.getId();


        // 为每个新资源设置属性
        existingResources.forEach(resource -> {
            resource.setId(IdUtil.generateFormattedId());
            resource.setStickerId(stickerId);
            resource.setName(stickerName);
            resource.setRemark("");
            resource.setId(IdUtil.generateFormattedId());
            resource.setCreateTime(DateUtils.getNowDate());
            resource.setUpdateTime(DateUtils.getNowDate());

            // 设置默认值
            if (resource.getShareCount() == null) {
                resource.setShareCount(0L);
            }
            if (resource.getDownloads() == null) {
                resource.setDownloads(0L);
            }
            if (resource.getViews() == null) {
                resource.setViews(0L);
            }
            if (resource.getLikes() == null) {
                resource.setLikes(0L);
            }
            if (resource.getStatus() == null) {
                resource.setStatus(1); // 默认状态为1-正常
            }
        });

        return stickerResourceMapper.batchInsert(existingResources);
    }

    @Override
    public List<TbStickerResource> selectAppTbStickerResourceList(TbStickerResource tbStickerResource) {
        List<TbStickerResource> stickerResourceList = stickerResourceMapper.selectAppTbStickerResourceList(tbStickerResource);
        return stickerResourceList;
    }
}
