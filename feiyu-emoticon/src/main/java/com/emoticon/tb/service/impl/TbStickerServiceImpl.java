package com.emoticon.tb.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.emoticon.common.utils.DateUtils;
import com.emoticon.common.utils.IdUtil;
import com.emoticon.tb.domain.TbSticker;
import com.emoticon.tb.domain.TbStickerResource;
import com.emoticon.tb.domain.TbStickerResourceFavorite;
import com.emoticon.tb.mapper.TbStickerMapper;
import com.emoticon.tb.service.ITbCategoryService;
import com.emoticon.tb.service.ITbStickerResourceFavoriteService;
import com.emoticon.tb.service.ITbStickerResourceService;
import com.emoticon.tb.service.ITbStickerService;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 表情包Service业务层处理
 *
 * @author feiyu
 * @date 2025-03-22
 */
@Service
public class TbStickerServiceImpl implements ITbStickerService {
    @Autowired
    private TbStickerMapper tbStickerMapper;

    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private ITbStickerResourceService tbStickerResourceService;
    @Autowired
    private ITbCategoryService categoryService;

    @Autowired
    private AsyncStickerServiceImpl asyncStickerService;
    @Autowired
    private ITbStickerResourceFavoriteService stickerResourceFavoriteService;

    private static final String STICKER_VIEW_KEY = "sticker:view:";
    private static final int BATCH_UPDATE_THRESHOLD = 100; // 累积100次更新一次数据库

    /**
     * 查询表情包
     *
     * @param id 表情包主键
     * @return 表情包
     */
    @Override
    public TbSticker selectTbStickerById(String id) {
        return tbStickerMapper.selectTbStickerById(id);
    }

    /**
     * 查询表情包列表
     *
     * @param tbSticker 表情包
     * @return 表情包
     */
    @Override
    public List<TbSticker> selectTbStickerList(TbSticker tbSticker) {
        return tbStickerMapper.selectTbStickerList(tbSticker);
    }

    /**
     * 新增表情包
     *
     * @param tbSticker 表情包
     * @return 结果
     */
    @Override
    public int insertTbSticker(TbSticker tbSticker) {
        tbSticker.setId(IdUtil.generateFormattedId());
        tbSticker.setCreateTime(DateUtils.getNowDate());
        return tbStickerMapper.insertTbSticker(tbSticker);
    }

    /**
     * 修改表情包
     *
     * @param tbSticker 表情包
     * @return 结果
     */
    @Override
    public int updateTbSticker(TbSticker tbSticker) {
        tbSticker.setUpdateTime(DateUtils.getNowDate());
        return tbStickerMapper.updateTbSticker(tbSticker);
    }

    /**
     * 批量删除表情包
     *
     * @param ids 需要删除的表情包主键
     * @return 结果
     */
    @Override
    public int deleteTbStickerByIds(String[] ids) {
        return tbStickerMapper.deleteTbStickerByIds(ids);
    }

    /**
     * 删除表情包信息
     *
     * @param id 表情包主键
     * @return 结果
     */
    @Override
    public int deleteTbStickerById(String id) {
        return tbStickerMapper.deleteTbStickerById(id);
    }

    @Override
    public List<TbSticker> selectAppTbStickerList(TbSticker tbSticker) {
        List<TbSticker> stickerList = tbStickerMapper.selectAppTbStickerList(tbSticker);
        if (stickerList != null && !stickerList.isEmpty()) {
            List<String> stickerIdList = stickerList.parallelStream()
                    .map(TbSticker::getId)
                    .collect(Collectors.toList());

            // 获取所有表情包资源并转换为Map，每个表情包最多获取4个资源
            Map<String, List<TbStickerResource>> resourceMap = tbStickerResourceService
                    .selectBatchByStickerIdsWithLimit(stickerIdList, 4)
                    .stream()
                    .collect(Collectors.groupingBy(TbStickerResource::getStickerId));

            // 为每个表情包设置对应的资源列表
            stickerList.forEach(sticker -> {
                List<TbStickerResource> resources = resourceMap.getOrDefault(sticker.getId(), new ArrayList<>());
                sticker.setStickerResourceList(resources);
                // 从Redis获取最新浏览量
                sticker.setViews(getViewCountFromRedis(sticker.getId()));
            });
        }
        return stickerList;
    }

    /**
     * 查询 app 表情包，并增加浏览量
     *
     * @param id 表情包ID
     * @return 表情包信息
     */
    @Override
    public TbSticker selectAppTbSticker(String id) {
        TbSticker sticker = tbStickerMapper.selectTbStickerById(id);
        // 获取资源列表
        List<TbStickerResource> stickerResourceList = tbStickerResourceService.selectTbStickerResourceListById(id);
        long userId = StpUtil.getLoginIdAsLong();
        TbStickerResourceFavorite stickerResourceFavorite = new TbStickerResourceFavorite();
        stickerResourceFavorite.setUserId(userId);
        List<TbStickerResourceFavorite> stickerResourceFavoriteList = stickerResourceFavoriteService.selectTbStickerResourceFavoriteList(stickerResourceFavorite);
        List<String> stickerResourceFavoriteIdList = new ArrayList<>();
        if (!ObjectUtils.isEmpty(stickerResourceFavoriteList)) {
            stickerResourceFavoriteIdList = stickerResourceFavoriteList.stream()
                    .map(TbStickerResourceFavorite::getStickerResourceId).collect(Collectors.toList());
        }
        if (!ObjectUtils.isEmpty(stickerResourceList) && !ObjectUtils.isEmpty(stickerResourceFavoriteIdList)) {
            List<String> finalStickerResourceFavoriteIdList = stickerResourceFavoriteIdList;
            stickerResourceList.forEach(item -> {
                Boolean isCollected = finalStickerResourceFavoriteIdList.contains(item.getId());
                item.setIsCollected(isCollected);
            });
        }
        sticker.setStickerResourceList(stickerResourceList);
        // 使用异步服务增加浏览量
        asyncStickerService.incrementViewCount(id);
        // 从Redis获取最新浏览量
        sticker.setViews(getViewCountFromRedis(id));
        return sticker;
    }

    /**
     * 从Redis获取表情包浏览量
     *
     * @param id 表情包ID
     * @return 浏览量
     */
    public Long getViewCountFromRedis(String id) {
        String viewKey = STICKER_VIEW_KEY + id;
        RAtomicLong atomicLong = redissonClient.getAtomicLong(viewKey);
        return atomicLong.get();
    }
}
