package com.emoticon.tb.mapper;

import com.emoticon.tb.domain.TbFeedback;

import java.util.List;

/**
 * 意见反馈Mapper接口
 *
 * @author emoticon
 * @date 2025-03-11
 */
public interface TbFeedbackMapper
{
    /**
     * 查询意见反馈
     *
     * @param id 意见反馈主键
     * @return 意见反馈
     */
    public TbFeedback selectTbFeedbackById(Long id);

    /**
     * 查询意见反馈列表
     *
     * @param tbFeedback 意见反馈
     * @return 意见反馈集合
     */
    public List<TbFeedback> selectTbFeedbackList(TbFeedback tbFeedback);

    /**
     * 新增意见反馈
     *
     * @param tbFeedback 意见反馈
     * @return 结果
     */
    public int insertTbFeedback(TbFeedback tbFeedback);

    /**
     * 修改意见反馈
     *
     * @param tbFeedback 意见反馈
     * @return 结果
     */
    public int updateTbFeedback(TbFeedback tbFeedback);

    /**
     * 删除意见反馈
     *
     * @param id 意见反馈主键
     * @return 结果
     */
    public int deleteTbFeedbackById(Long id);

    /**
     * 批量删除意见反馈
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbFeedbackByIds(Long[] ids);
}
