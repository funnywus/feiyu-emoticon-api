package com.emoticon.tb.service;

import java.util.List;
import com.emoticon.tb.domain.TbAddress;

/**
 * 用户收货地址Service接口
 */
public interface ITbAddressService
{
    /**
     * 查询用户收货地址
     *
     * @param id 用户收货地址主键
     * @return 用户收货地址
     */
    public TbAddress selectFlowerAddressById(Long id);

    /**
     * 查询用户收货地址列表
     *
     * @param tbAddress 用户收货地址
     * @return 用户收货地址集合
     */
    public List<TbAddress> selectFlowerAddressList(TbAddress tbAddress);

    /**
     * 查询用户的默认收货地址
     *
     * @param userId 用户ID
     * @return 默认收货地址
     */
    public TbAddress selectDefaultAddressByUserId(Long userId);

    /**
     * 新增用户收货地址
     *
     * @param tbAddress 用户收货地址
     * @return 结果
     */
    public int insertFlowerAddress(TbAddress tbAddress);

    /**
     * 修改用户收货地址
     *
     * @param tbAddress 用户收货地址
     * @return 结果
     */
    public int updateFlowerAddress(TbAddress tbAddress);

    /**
     * 批量删除用户收货地址
     *
     * @param ids 需要删除的用户收货地址主键集合
     * @return 结果
     */
    public int deleteFlowerAddressByIds(Long[] ids);

    /**
     * 删除用户收货地址信息
     *
     * @param id 用户收货地址主键
     * @return 结果
     */
    public int deleteFlowerAddressById(Long id);

    /**
     * 新增收货地址
     *
     * @param address 收货地址信息
     * @return 结果
     */
    public int addFlowerAddress(TbAddress address);

    /**
     * 设置默认收货地址
     *
     * @param id 地址ID
     * @param userId 用户ID
     * @return 结果
     */
    public int setDefaultAddress(Long id, Long userId);
}
