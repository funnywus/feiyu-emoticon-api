package com.emoticon.tb.service.impl;

import com.emoticon.common.utils.DateUtils;
import com.emoticon.tb.domain.TbAddress;
import com.emoticon.tb.mapper.TbAddressMapper;
import com.emoticon.tb.service.ITbAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户收货地址Service业务层处理
 */
@Service
public class TbAddressServiceImpl implements ITbAddressService
{
    @Autowired
    private TbAddressMapper tbAddressMapper;

    /**
     * 查询用户收货地址
     *
     * @param id 用户收货地址主键
     * @return 用户收货地址
     */
    @Override
    public TbAddress selectFlowerAddressById(Long id)
    {
        return tbAddressMapper.selectFlowerAddressById(id);
    }

    /**
     * 查询用户收货地址列表
     *
     * @param tbAddress 用户收货地址
     * @return 用户收货地址
     */
    @Override
    public List<TbAddress> selectFlowerAddressList(TbAddress tbAddress)
    {
        return tbAddressMapper.selectFlowerAddressList(tbAddress);
    }

    /**
     * 查询用户的默认收货地址
     *
     * @param userId 用户ID
     * @return 默认收货地址
     */
    @Override
    public TbAddress selectDefaultAddressByUserId(Long userId)
    {
        return tbAddressMapper.selectDefaultAddressByUserId(userId);
    }

    /**
     * 新增用户收货地址
     *
     * @param tbAddress 用户收货地址
     * @return 结果
     */
    @Override
    @Transactional
    public int insertFlowerAddress(TbAddress tbAddress)
    {
        // 如果设置为默认地址，先取消其他默认地址
        if ("1".equals(tbAddress.getIsDefault()))
        {
            tbAddressMapper.cancelDefaultAddress(tbAddress.getUserId());
        }
        return tbAddressMapper.insertFlowerAddress(tbAddress);
    }

    /**
     * 新增收货地址
     *
     * @param address 收货地址信息
     * @return 结果
     */
    @Override
    public int addFlowerAddress(TbAddress address)
    {
        address.setCreateTime(DateUtils.getNowDate());
        // 如果是第一个地址，设为默认地址
        List<TbAddress> list = tbAddressMapper.selectFlowerAddressList(new TbAddress(address.getUserId()));
        if (list == null || list.isEmpty()) {
            address.setIsDefault("1");
        } else {
            address.setIsDefault("0");
        }
        return tbAddressMapper.insertFlowerAddress(address);
    }

    /**
     * 修改用户收货地址
     *
     * @param tbAddress 用户收货地址
     * @return 结果
     */
    @Override
    @Transactional
    public int updateFlowerAddress(TbAddress tbAddress)
    {
        // 如果设置为默认地址，先取消其他默认地址
        if ("1".equals(tbAddress.getIsDefault()))
        {
            tbAddressMapper.cancelDefaultAddress(tbAddress.getUserId());
        }
        return tbAddressMapper.updateFlowerAddress(tbAddress);
    }

    /**
     * 批量删除用户收货地址
     *
     * @param ids 需要删除的用户收货地址主键
     * @return 结果
     */
    @Override
    public int deleteFlowerAddressByIds(Long[] ids)
    {
        return tbAddressMapper.deleteFlowerAddressByIds(ids);
    }

    /**
     * 删除用户收货地址信息
     *
     * @param id 用户收货地址主键
     * @return 结果
     */
    @Override
    public int deleteFlowerAddressById(Long id)
    {
        return tbAddressMapper.deleteFlowerAddressById(id);
    }

    /**
     * 设置默认收货地址
     *
     * @param id 地址ID
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    @Transactional
    public int setDefaultAddress(Long id, Long userId)
    {
        // 验证地址是否属于当前用户
        TbAddress address = tbAddressMapper.selectFlowerAddressById(id);
        if (address == null || !address.getUserId().equals(userId)) {
            throw new RuntimeException("地址不存在或无权限设置");
        }
        // 先将所有地址设为非默认
        TbAddress updateAddress = new TbAddress();
        updateAddress.setUserId(userId);
        updateAddress.setIsDefault("0");
        tbAddressMapper.updateFlowerAddressDefault(updateAddress);
        // 设置新的默认地址
        updateAddress.setId(id);
        updateAddress.setIsDefault("1");
        return tbAddressMapper.updateFlowerAddress(updateAddress);
    }
}
