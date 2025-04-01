package com.emoticon.tb.service.impl;

import com.emoticon.common.utils.DateUtils;
import com.emoticon.tb.domain.TbWelfare;
import com.emoticon.tb.mapper.TbWelfareMapper;
import com.emoticon.tb.service.ITbWelfareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 福利列表Service业务层处理
 *
 * @author feiyu
 * @date 2025-03-22
 */
@Service
public class TbWelfareServiceImpl implements ITbWelfareService
{
    @Autowired
    private TbWelfareMapper tbWelfareMapper;

    /**
     * 查询福利列表
     *
     * @param id 福利列表主键
     * @return 福利列表
     */
    @Override
    public TbWelfare selectTbWelfareById(Long id)
    {
        return tbWelfareMapper.selectTbWelfareById(id);
    }

    /**
     * 查询福利列表列表
     *
     * @param tbWelfare 福利列表
     * @return 福利列表
     */
    @Override
    public List<TbWelfare> selectTbWelfareList(TbWelfare tbWelfare)
    {
        return tbWelfareMapper.selectTbWelfareList(tbWelfare);
    }

    /**
     * 新增福利列表
     *
     * @param tbWelfare 福利列表
     * @return 结果
     */
    @Override
    public int insertTbWelfare(TbWelfare tbWelfare)
    {
        tbWelfare.setCreateTime(DateUtils.getNowDate());
        return tbWelfareMapper.insertTbWelfare(tbWelfare);
    }

    /**
     * 修改福利列表
     *
     * @param tbWelfare 福利列表
     * @return 结果
     */
    @Override
    public int updateTbWelfare(TbWelfare tbWelfare)
    {
        tbWelfare.setUpdateTime(DateUtils.getNowDate());
        return tbWelfareMapper.updateTbWelfare(tbWelfare);
    }

    /**
     * 批量删除福利列表
     *
     * @param ids 需要删除的福利列表主键
     * @return 结果
     */
    @Override
    public int deleteTbWelfareByIds(Long[] ids)
    {
        return tbWelfareMapper.deleteTbWelfareByIds(ids);
    }

    /**
     * 删除福利列表信息
     *
     * @param id 福利列表主键
     * @return 结果
     */
    @Override
    public int deleteTbWelfareById(Long id)
    {
        return tbWelfareMapper.deleteTbWelfareById(id);
    }
}
