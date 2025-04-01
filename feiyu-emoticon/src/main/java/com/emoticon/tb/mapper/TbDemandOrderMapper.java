package com.emoticon.tb.mapper;

import java.util.List;
import com.emoticon.tb.domain.TbDemandOrder;

/**
 * 需求订单Mapper接口
 *
 * @author emoticon
 * @date 2025-03-05
 */
public interface TbDemandOrderMapper
{
    /**
     * 查询需求订单
     *
     * @param id 需求订单主键
     * @return 需求订单
     */
    public TbDemandOrder selectTbDemandOrderById(String id);

    /**
     * 查询需求订单列表
     *
     * @param tbDemandOrder 需求订单
     * @return 需求订单集合
     */
    public List<TbDemandOrder> selectTbDemandOrderList(TbDemandOrder tbDemandOrder);

    /**
     * 新增需求订单
     *
     * @param tbDemandOrder 需求订单
     * @return 结果
     */
    public int insertTbDemandOrder(TbDemandOrder tbDemandOrder);

    /**
     * 修改需求订单
     *
     * @param tbDemandOrder 需求订单
     * @return 结果
     */
    public int updateTbDemandOrder(TbDemandOrder tbDemandOrder);

    /**
     * 删除需求订单
     *
     * @param id 需求订单主键
     * @return 结果
     */
    public int deleteTbDemandOrderById(String id);

    /**
     * 批量删除需求订单
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbDemandOrderByIds(String[] ids);
}
