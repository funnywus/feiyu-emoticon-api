package com.emoticon.tb.mapper;

import com.emoticon.tb.domain.TbConfig;

import java.util.List;

/**
 * 全局配置Mapper接口
 *
 * @author feiyu
 * @date 2025-03-22
 */
public interface TbConfigMapper
{
    /**
     * 查询全局配置
     *
     * @param id 全局配置主键
     * @return 全局配置
     */
    public TbConfig selectTbConfigById(Long id);

    /**
     * 查询全局配置列表
     *
     * @param tbConfig 全局配置
     * @return 全局配置集合
     */
    public List<TbConfig> selectTbConfigList(TbConfig tbConfig);

    /**
     * 新增全局配置
     *
     * @param tbConfig 全局配置
     * @return 结果
     */
    public int insertTbConfig(TbConfig tbConfig);

    /**
     * 修改全局配置
     *
     * @param tbConfig 全局配置
     * @return 结果
     */
    public int updateTbConfig(TbConfig tbConfig);

    /**
     * 删除全局配置
     *
     * @param id 全局配置主键
     * @return 结果
     */
    public int deleteTbConfigById(Long id);

    /**
     * 批量删除全局配置
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbConfigByIds(Long[] ids);
}
