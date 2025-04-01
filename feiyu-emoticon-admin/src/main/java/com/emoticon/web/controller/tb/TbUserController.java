package com.emoticon.web.controller.tb;

import com.emoticon.common.annotation.Log;
import com.emoticon.common.core.controller.BaseController;
import com.emoticon.common.core.domain.AjaxResult;
import com.emoticon.common.core.page.TableDataInfo;
import com.emoticon.common.enums.BusinessType;
import com.emoticon.common.utils.SecurityUtils;
import com.emoticon.tb.domain.TbUser;
import com.emoticon.tb.service.ITbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * emoticon-framework用户管理 控制器
 *
 * @author emoticon
 */
@RestController
@RequestMapping("/tb/user")
public class TbUserController extends BaseController
{
    @Autowired
    private ITbUserService flowerUserService;

    /**
     * 获取emoticon-framework用户列表
     */
    @PreAuthorize("@ss.hasPermi('tb:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(TbUser user)
    {
        startPage();
        List<TbUser> list = flowerUserService.selectFlowerUserList(user);
        return getDataTable(list);
    }

    /**
     * 根据用户编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('tb:user:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable Long userId)
    {
        return success(flowerUserService.selectUserById(userId));
    }

    /**
     * 新增用户
     */
    @PreAuthorize("@ss.hasPermi('tb:user:add')")
    @Log(title = "emoticon-framework用户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody TbUser user)
    {
//        if (!flowerUserService.checkUserNameUnique(user))
//        {
//            return error("新增用户'" + user.getUserName() + "'失败，登录账号已存在");
//        }
//        else if (!flowerUserService.checkPhoneUnique(user))
//        {
//            return error("新增用户'" + user.getUserName() + "'失败，手机号码已存在");
//        }
//        else if (!flowerUserService.checkEmailUnique(user))
//        {
//            return error("新增用户'" + user.getUserName() + "'失败，邮箱账号已存在");
//        }
        user.setCreateBy(getUsername());
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        return toAjax(flowerUserService.insertFlowerUser(user));
    }

    /**
     * 修改用户
     */
    @PreAuthorize("@ss.hasPermi('tb:user:edit')")
    @Log(title = "emoticon-framework用户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody TbUser user)
    {
//        if (!flowerUserService.checkUserNameUnique(user))
//        {
//            return error("修改用户'" + user.getUserName() + "'失败，登录账号已存在");
//        }
//        else if (!flowerUserService.checkPhoneUnique(user))
//        {
//            return error("修改用户'" + user.getUserName() + "'失败，手机号码已存在");
//        }
//        else if (!flowerUserService.checkEmailUnique(user))
//        {
//            return error("修改用户'" + user.getUserName() + "'失败，邮箱账号已存在");
//        }
        user.setUpdateBy(getUsername());
        return toAjax(flowerUserService.updateFlowerUser(user));
    }

    /**
     * 删除用户
     */
    @PreAuthorize("@ss.hasPermi('tb:user:remove')")
    @Log(title = "emoticon-framework用户管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(flowerUserService.deleteFlowerUserByIds(userIds));
    }

    /**
     * 重置密码
     */
    @PreAuthorize("@ss.hasPermi('tb:user:resetPwd')")
    @Log(title = "emoticon-framework用户管理", businessType = BusinessType.UPDATE)
    @PutMapping("/resetPwd")
    public AjaxResult resetPwd(@RequestBody TbUser user)
    {
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        user.setUpdateBy(getUsername());
        return toAjax(flowerUserService.resetPwd(user));
    }

}
