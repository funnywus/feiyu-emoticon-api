package com.emoticon.tb.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.emoticon.common.utils.DateUtils;
import com.emoticon.tb.domain.TbStickerResource;
import com.emoticon.tb.domain.TbStickerResourceFavorite;
import com.emoticon.tb.mapper.TbStickerResourceFavoriteMapper;
import com.emoticon.tb.service.ITbStickerResourceFavoriteService;
import com.emoticon.tb.service.ITbStickerResourceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 表情包资源收藏Service业务层处理
 *
 * @author feiyu
 * @date 2025-03-27
 */
@AllArgsConstructor
@Service
public class TbStickerResourceFavoriteServiceImpl implements ITbStickerResourceFavoriteService
{
    @Resource
    private TbStickerResourceFavoriteMapper tbStickerResourceFavoriteMapper;
    private final ITbStickerResourceService stickerResourceService;

    /**
     * 查询表情包资源收藏
     *
     * @param id 表情包资源收藏主键
     * @return 表情包资源收藏
     */
    @Override
    public TbStickerResourceFavorite selectTbStickerResourceFavoriteById(String id)
    {
        return tbStickerResourceFavoriteMapper.selectTbStickerResourceFavoriteById(id);
    }

    /**
     * 查询表情包资源收藏列表
     *
     * @param tbStickerResourceFavorite 表情包资源收藏
     * @return 表情包资源收藏
     */
    @Override
    public List<TbStickerResourceFavorite> selectTbStickerResourceFavoriteList(TbStickerResourceFavorite tbStickerResourceFavorite)
    {
        return tbStickerResourceFavoriteMapper.selectTbStickerResourceFavoriteList(tbStickerResourceFavorite);
    }

    /**
     * 新增表情包资源收藏
     *
     * @param tbStickerResourceFavorite 表情包资源收藏
     * @return 结果
     */
    @Override
    public int insertTbStickerResourceFavorite(TbStickerResourceFavorite tbStickerResourceFavorite)
    {
        tbStickerResourceFavorite.setCreateTime(DateUtils.getNowDate());
        return tbStickerResourceFavoriteMapper.insertTbStickerResourceFavorite(tbStickerResourceFavorite);
    }

    /**
     * 修改表情包资源收藏
     *
     * @param tbStickerResourceFavorite 表情包资源收藏
     * @return 结果
     */
    @Override
    public int updateTbStickerResourceFavorite(TbStickerResourceFavorite tbStickerResourceFavorite)
    {
        tbStickerResourceFavorite.setUpdateTime(DateUtils.getNowDate());
        return tbStickerResourceFavoriteMapper.updateTbStickerResourceFavorite(tbStickerResourceFavorite);
    }

    /**
     * 批量删除表情包资源收藏
     *
     * @param ids 需要删除的表情包资源收藏主键
     * @return 结果
     */
    @Override
    public int deleteTbStickerResourceFavoriteByIds(String[] ids)
    {
        return tbStickerResourceFavoriteMapper.deleteTbStickerResourceFavoriteByIds(ids);
    }

    /**
     * 删除表情包资源收藏信息
     *
     * @param id 表情包资源收藏主键
     * @return 结果
     */
    @Override
    public int deleteTbStickerResourceFavoriteById(String id)
    {
        return tbStickerResourceFavoriteMapper.deleteTbStickerResourceFavoriteById(id);
    }

    @Override
    public int toggleFavoriteStatus(String resourceId) {
        Long userId = StpUtil.getLoginIdAsLong();
        // 查询是否已收藏
        TbStickerResourceFavorite favorite = tbStickerResourceFavoriteMapper.selectByUserIdAndResourceId(userId, resourceId);

        if (favorite != null) {
            // 如果已收藏，则取消收藏
            return tbStickerResourceFavoriteMapper.deleteTbStickerResourceFavoriteById(favorite.getId());
        } else {
            // 如果未收藏，则添加收藏
            TbStickerResourceFavorite newFavorite = new TbStickerResourceFavorite();
            newFavorite.setUserId(userId);
            newFavorite.setStickerResourceId(resourceId);
            newFavorite.setCreateTime(DateUtils.getNowDate());
            return tbStickerResourceFavoriteMapper.insertTbStickerResourceFavorite(newFavorite);
        }
    }

    @Override
    public List<TbStickerResource> selectFavoriteResourceList(TbStickerResourceFavorite stickerResourceFavorite) {
        // 获取当前登录用户ID
        Long userId = StpUtil.getLoginIdAsLong();
        // 设置查询用户ID
        stickerResourceFavorite.setUserId(userId);
        return tbStickerResourceFavoriteMapper.selectFavoriteResourceList(stickerResourceFavorite);
    }
}
