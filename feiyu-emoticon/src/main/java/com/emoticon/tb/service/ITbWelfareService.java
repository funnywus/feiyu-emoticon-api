package com.emoticon.tb.service;

import com.emoticon.tb.domain.TbWelfare;

import java.util.List;

/**
 * 福利列表Service接口
 *
 * @author feiyu
 * @date 2025-03-22
 */
public interface ITbWelfareService
{
    /**
     * 查询福利列表
     *
     * @param id 福利列表主键
     * @return 福利列表
     */
    public TbWelfare selectTbWelfareById(Long id);

    /**
     * 查询福利列表列表
     *
     * @param tbWelfare 福利列表
     * @return 福利列表集合
     */
    public List<TbWelfare> selectTbWelfareList(TbWelfare tbWelfare);

    /**
     * 新增福利列表
     *
     * @param tbWelfare 福利列表
     * @return 结果
     */
    public int insertTbWelfare(TbWelfare tbWelfare);

    /**
     * 修改福利列表
     *
     * @param tbWelfare 福利列表
     * @return 结果
     */
    public int updateTbWelfare(TbWelfare tbWelfare);

    /**
     * 批量删除福利列表
     *
     * @param ids 需要删除的福利列表主键集合
     * @return 结果
     */
    public int deleteTbWelfareByIds(Long[] ids);

    /**
     * 删除福利列表信息
     *
     * @param id 福利列表主键
     * @return 结果
     */
    public int deleteTbWelfareById(Long id);
}
