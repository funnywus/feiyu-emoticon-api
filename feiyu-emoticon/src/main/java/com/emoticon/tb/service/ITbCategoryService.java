package com.emoticon.tb.service;

import com.emoticon.tb.domain.TbCategory;

import java.util.List;

/**
 * 表情包分类Service接口
 *
 * @author feiyu
 * @date 2025-03-22
 */
public interface ITbCategoryService
{
    /**
     * 查询表情包分类
     *
     * @param id 表情包分类主键
     * @return 表情包分类
     */
    public TbCategory selectTbCategoryById(String id);

    /**
     * 查询表情包分类列表
     *
     * @param tbCategory 表情包分类
     * @return 表情包分类集合
     */
    public List<TbCategory> selectTbCategoryList(TbCategory tbCategory);

    /**
     * 新增表情包分类
     *
     * @param tbCategory 表情包分类
     * @return 结果
     */
    public int insertTbCategory(TbCategory tbCategory);

    /**
     * 修改表情包分类
     *
     * @param tbCategory 表情包分类
     * @return 结果
     */
    public int updateTbCategory(TbCategory tbCategory);

    /**
     * 批量删除表情包分类
     *
     * @param ids 需要删除的表情包分类主键集合
     * @return 结果
     */
    public int deleteTbCategoryByIds(String[] ids);

    /**
     * 删除表情包分类信息
     *
     * @param id 表情包分类主键
     * @return 结果
     */
    public int deleteTbCategoryById(String id);
}
