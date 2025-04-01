package com.emoticon.tb.mapper;

import java.util.List;
import com.emoticon.tb.domain.TbAddress;

/**
 * 用户收货地址Mapper接口
 */
public interface TbAddressMapper
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
     * 删除用户收货地址
     *
     * @param id 用户收货地址主键
     * @return 结果
     */
    public int deleteFlowerAddressById(Long id);

    /**
     * 批量删除用户收货地址
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFlowerAddressByIds(Long[] ids);

    /**
     * 取消用户的所有默认地址
     *
     * @param userId 用户ID
     * @return 结果
     */
    public int cancelDefaultAddress(Long userId);

    /**
     * 更新用户所有收货地址的默认状态
     *
     * @param address 收货地址信息
     * @return 结果
     */
    public int updateFlowerAddressDefault(TbAddress address);
}
