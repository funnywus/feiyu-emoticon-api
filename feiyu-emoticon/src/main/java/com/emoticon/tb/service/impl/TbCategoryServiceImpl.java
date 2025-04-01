package com.emoticon.tb.service.impl;

import com.emoticon.common.utils.DateUtils;
import com.emoticon.tb.domain.TbCategory;
import com.emoticon.tb.mapper.TbCategoryMapper;
import com.emoticon.tb.service.ITbCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 表情包分类Service业务层处理
 *
 * @author feiyu
 * @date 2025-03-22
 */
@Service
public class TbCategoryServiceImpl implements ITbCategoryService
{
    @Autowired
    private TbCategoryMapper tbCategoryMapper;

    /**
     * 查询表情包分类
     *
     * @param id 表情包分类主键
     * @return 表情包分类
     */
    @Override
    public TbCategory selectTbCategoryById(String id)
    {
        return tbCategoryMapper.selectTbCategoryById(id);
    }

    /**
     * 查询表情包分类列表
     *
     * @param tbCategory 表情包分类
     * @return 表情包分类
     */
    @Override
    public List<TbCategory> selectTbCategoryList(TbCategory tbCategory)
    {
        return tbCategoryMapper.selectTbCategoryList(tbCategory);
    }

    /**
     * 新增表情包分类
     *
     * @param tbCategory 表情包分类
     * @return 结果
     */
    @Override
    public int insertTbCategory(TbCategory tbCategory)
    {
        tbCategory.setCreateTime(DateUtils.getNowDate());
        return tbCategoryMapper.insertTbCategory(tbCategory);
    }

    /**
     * 修改表情包分类
     *
     * @param tbCategory 表情包分类
     * @return 结果
     */
    @Override
    public int updateTbCategory(TbCategory tbCategory)
    {
        tbCategory.setUpdateTime(DateUtils.getNowDate());
        return tbCategoryMapper.updateTbCategory(tbCategory);
    }

    /**
     * 批量删除表情包分类
     *
     * @param ids 需要删除的表情包分类主键
     * @return 结果
     */
    @Override
    public int deleteTbCategoryByIds(String[] ids)
    {
        return tbCategoryMapper.deleteTbCategoryByIds(ids);
    }

    /**
     * 删除表情包分类信息
     *
     * @param id 表情包分类主键
     * @return 结果
     */
    @Override
    public int deleteTbCategoryById(String id)
    {
        return tbCategoryMapper.deleteTbCategoryById(id);
    }
}
