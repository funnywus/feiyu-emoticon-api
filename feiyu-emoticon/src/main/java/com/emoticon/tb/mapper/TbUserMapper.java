package com.emoticon.tb.mapper;

import java.util.List;
import com.emoticon.tb.domain.TbUser;

/**
 * emoticon-framework用户信息 数据层
 *
 * @author emoticon
 */
public interface TbUserMapper
{
    /**
     * 根据条件分页查询用户列表
     *
     * @param tbUser 用户信息
     * @return 用户信息集合
     */
    public List<TbUser> selectFlowerUserList(TbUser tbUser);

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    public TbUser selectFlowerUserByUserName(String userName);

    /**
     * 通过用户ID查询用户
     *
     * @param userId 用户ID
     * @return 用户对象信息
     */
    public TbUser selectFlowerUserById(Long userId);

    /**
     * 新增用户信息
     *
     * @param tbUser 用户信息
     * @return 结果
     */
    public int insertFlowerUser(TbUser tbUser);

    /**
     * 修改用户信息
     *
     * @param tbUser 用户信息
     * @return 结果
     */
    public int updateFlowerUser(TbUser tbUser);

    /**
     * 通过用户ID删除用户
     *
     * @param userId 用户ID
     * @return 结果
     */
    public int deleteFlowerUserById(Long userId);

    /**
     * 批量删除用户信息
     *
     * @param userIds 需要删除的用户ID
     * @return 结果
     */
    public int deleteFlowerUserByIds(Long[] userIds);

    /**
     * 校验用户名称是否唯一
     *
     * @param userName 用户名称
     * @return 结果
     */
    public TbUser checkUserNameUnique(String userName);

    /**
     * 校验手机号码是否唯一
     *
     * @param phonenumber 手机号码
     * @return 结果
     */
    public TbUser checkPhoneUnique(String phonenumber);

    /**
     * 校验email是否唯一
     *
     * @param email 用户邮箱
     * @return 结果
     */
    public TbUser checkEmailUnique(String email);


    /**
     * 通过openid查询用户
     *
     * @param openid openid
     * @return 用户对象信息
     */
    public TbUser selectFlowerUserByOpenid(String openid);
}
