package com.emoticon.tb.service.impl;

import com.emoticon.common.utils.DateUtils;
import com.emoticon.tb.domain.TbFeedback;
import com.emoticon.tb.mapper.TbFeedbackMapper;
import com.emoticon.tb.service.ITbFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 意见反馈Service业务层处理
 *
 * @author emoticon
 * @date 2025-03-11
 */
@Service
public class TbFeedbackServiceImpl implements ITbFeedbackService
{
    @Autowired
    private TbFeedbackMapper tbFeedbackMapper;

    /**
     * 查询意见反馈
     *
     * @param id 意见反馈主键
     * @return 意见反馈
     */
    @Override
    public TbFeedback selectTbFeedbackById(Long id)
    {
        return tbFeedbackMapper.selectTbFeedbackById(id);
    }

    /**
     * 查询意见反馈列表
     *
     * @param tbFeedback 意见反馈
     * @return 意见反馈
     */
    @Override
    public List<TbFeedback> selectTbFeedbackList(TbFeedback tbFeedback)
    {
        return tbFeedbackMapper.selectTbFeedbackList(tbFeedback);
    }

    /**
     * 新增意见反馈
     *
     * @param tbFeedback 意见反馈
     * @return 结果
     */
    @Override
    public int insertTbFeedback(TbFeedback tbFeedback)
    {
        tbFeedback.setCreateTime(DateUtils.getNowDate());
        return tbFeedbackMapper.insertTbFeedback(tbFeedback);
    }

    /**
     * 修改意见反馈
     *
     * @param tbFeedback 意见反馈
     * @return 结果
     */
    @Override
    public int updateTbFeedback(TbFeedback tbFeedback)
    {
        tbFeedback.setUpdateTime(DateUtils.getNowDate());
        return tbFeedbackMapper.updateTbFeedback(tbFeedback);
    }

    /**
     * 批量删除意见反馈
     *
     * @param ids 需要删除的意见反馈主键
     * @return 结果
     */
    @Override
    public int deleteTbFeedbackByIds(Long[] ids)
    {
        return tbFeedbackMapper.deleteTbFeedbackByIds(ids);
    }

    /**
     * 删除意见反馈信息
     *
     * @param id 意见反馈主键
     * @return 结果
     */
    @Override
    public int deleteTbFeedbackById(Long id)
    {
        return tbFeedbackMapper.deleteTbFeedbackById(id);
    }
}
