package com.emoticon.tb.service.impl;

import com.emoticon.common.utils.DateUtils;
import com.emoticon.tb.domain.TbAddress;
import com.emoticon.tb.domain.TbDemandOrder;
import com.emoticon.tb.mapper.TbDemandOrderMapper;
import com.emoticon.tb.service.ITbAddressService;
import com.emoticon.tb.service.ITbDemandOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 需求订单Service业务层处理
 *
 * @author emoticon
 * @date 2025-03-05
 */
@Service
public class TbDemandOrderServiceImpl implements ITbDemandOrderService
{
    @Autowired
    private TbDemandOrderMapper tbDemandOrderMapper;
    @Autowired

    private ITbAddressService iTbAddressService;

    /**
     * 查询需求订单
     *
     * @param id 需求订单主键
     * @return 需求订单
     */
    @Override
    public TbDemandOrder selectTbDemandOrderById(String id)
    {
        return tbDemandOrderMapper.selectTbDemandOrderById(id);
    }

    /**
     * 查询需求订单列表
     *
     * @param tbDemandOrder 需求订单
     * @return 需求订单
     */
    @Override
    public List<TbDemandOrder> selectTbDemandOrderList(TbDemandOrder tbDemandOrder)
    {
        return tbDemandOrderMapper.selectTbDemandOrderList(tbDemandOrder);
    }

    /**
     * 新增需求订单
     *
     * @param tbDemandOrder 需求订单
     * @return 结果
     */
    @Override
    public int insertTbDemandOrder(TbDemandOrder tbDemandOrder)
    {
        tbDemandOrder.setCreateTime(DateUtils.getNowDate());
        return tbDemandOrderMapper.insertTbDemandOrder(tbDemandOrder);
    }

    /**
     * 修改需求订单
     *
     * @param tbDemandOrder 需求订单
     * @return 结果
     */
    @Override
    public int updateTbDemandOrder(TbDemandOrder tbDemandOrder)
    {
        tbDemandOrder.setUpdateTime(DateUtils.getNowDate());
        return tbDemandOrderMapper.updateTbDemandOrder(tbDemandOrder);
    }

    /**
     * 批量删除需求订单
     *
     * @param ids 需要删除的需求订单主键
     * @return 结果
     */
    @Override
    public int deleteTbDemandOrderByIds(String[] ids)
    {
        return tbDemandOrderMapper.deleteTbDemandOrderByIds(ids);
    }

    /**
     * 删除需求订单信息
     *
     * @param id 需求订单主键
     * @return 结果
     */
    @Override
    public int deleteTbDemandOrderById(String id)
    {
        return tbDemandOrderMapper.deleteTbDemandOrderById(id);
    }

    @Override
    public Object addTbDemandOrder(TbDemandOrder order) {
        Long addressId = order.getAddressId();
        TbAddress tbAddress = iTbAddressService.selectFlowerAddressById(addressId);
        if (tbAddress != null) {
            order.setReceiverName(tbAddress.getReceiverName());
            order.setReceiverPhone(tbAddress.getReceiverPhone());
            order.setReceiverAddress(tbAddress.getDetailAddress());
        }
        // 设置初始状态为待支付
        order.setStatus("0");
        // 设置订单编号
        order.setId(generateOrderNo());
        order.setCreateTime(DateUtils.getNowDate());
        tbDemandOrderMapper.insertTbDemandOrder(order);
        return order.getId();
    }

    /**
     * 生成订单编号
     * 格式: 年月日时分秒 + 4位随机数
     */
    private String generateOrderNo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timeStr = sdf.format(new Date());
        int random = (int) ((Math.random() * 9 + 1) * 1000);
        return timeStr + random;
    }
}
