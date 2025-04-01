package com.emoticon.tb.service.impl;

import com.emoticon.common.constant.UserConstants;
import com.emoticon.common.utils.StringUtils;
import com.emoticon.tb.domain.TbUser;
import com.emoticon.tb.mapper.TbUserMapper;
import com.emoticon.tb.service.ITbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * emoticon-framework用户 业务层处理
 *
 * @author emoticon
 */
@Service
public class TbUserServiceImpl implements ITbUserService
{
    @Autowired
    private TbUserMapper userMapper;

    /**
     * 根据条件分页查询用户列表
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    @Override
    public List<TbUser> selectFlowerUserList(TbUser user)
    {
        return userMapper.selectFlowerUserList(user);
    }

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    @Override
    public TbUser selectFlowerUserByUserName(String userName)
    {
        return userMapper.selectFlowerUserByUserName(userName);
    }

    /**
     * 通过用户ID查询用户
     *
     * @param userId 用户ID
     * @return 用户对象信息
     */
    @Override
    public TbUser selectUserById(Long userId)
    {
        return userMapper.selectFlowerUserById(userId);
    }

    /**
     * 新增用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertFlowerUser(TbUser user)
    {
        // 新增用户信息
        int rows = userMapper.insertFlowerUser(user);
        return rows;
    }

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateFlowerUser(TbUser user)
    {
        return userMapper.updateFlowerUser(user);
    }

    /**
     * 通过用户ID删除用户
     *
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteFlowerUserById(Long userId)
    {
        return userMapper.deleteFlowerUserById(userId);
    }

    /**
     * 批量删除用户信息
     *
     * @param userIds 需要删除的用户ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteFlowerUserByIds(Long[] userIds)
    {
        return userMapper.deleteFlowerUserByIds(userIds);
    }

    /**
     * 校验用户名称是否唯一
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public boolean checkUserNameUnique(TbUser user)
    {
        Long userId = StringUtils.isNull(user.getUserId()) ? -1L : user.getUserId();
        TbUser info = userMapper.checkUserNameUnique(user.getUserName());
        if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验手机号码是否唯一
     *
     * @param user 用户信息
     * @return
     */
    @Override
    public boolean checkPhoneUnique(TbUser user)
    {
        Long userId = StringUtils.isNull(user.getUserId()) ? -1L : user.getUserId();
        TbUser info = userMapper.checkPhoneUnique(user.getPhonenumber());
        if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验email是否唯一
     *
     * @param user 用户信息
     * @return
     */
    @Override
    public boolean checkEmailUnique(TbUser user)
    {
        Long userId = StringUtils.isNull(user.getUserId()) ? -1L : user.getUserId();
        TbUser info = userMapper.checkEmailUnique(user.getEmail());
        if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 重置用户密码
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int resetPwd(TbUser user)
    {
        return userMapper.updateFlowerUser(user);
    }

    @Override
    public TbUser selectFlowerUserByOpenid(String openid) {
        return userMapper.selectFlowerUserByOpenid(openid);
    }
}
