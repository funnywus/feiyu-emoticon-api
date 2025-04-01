package com.emoticon.tb.mapper;

import com.emoticon.tb.domain.TbCategory;

import java.util.List;

/**
 * 表情包分类Mapper接口
 *
 * @author feiyu
 * @date 2025-03-22
 */
public interface TbCategoryMapper
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
     * 删除表情包分类
     *
     * @param id 表情包分类主键
     * @return 结果
     */
    public int deleteTbCategoryById(String id);

    /**
     * 批量删除表情包分类
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbCategoryByIds(String[] ids);
}
