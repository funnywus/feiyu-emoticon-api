package com.emoticon.web.controller.app;

import cn.dev33.satoken.stp.StpUtil;
import com.emoticon.common.config.EmoticonConfig;
import com.emoticon.common.core.controller.BaseController;
import com.emoticon.common.core.domain.AjaxResult;
import com.emoticon.common.core.domain.entity.SysUser;
import com.emoticon.common.core.domain.model.LoginBody;
import com.emoticon.common.core.domain.model.LoginUser;
import com.emoticon.common.utils.SecurityUtils;
import com.emoticon.common.utils.file.FileUploadUtils;
import com.emoticon.common.utils.file.MimeTypeUtils;
import com.emoticon.tb.domain.TbUser;
import com.emoticon.tb.service.ITbLoginService;
import com.emoticon.tb.service.ITbUserService;
import me.zhyd.oauth.model.AuthCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户信息 前台控制器
 *
 * @author emoticon
 */
@RestController
@RequestMapping("/app/tb/user")
public class AppTbUserController extends BaseController {

    @Autowired
    private ITbUserService tbUserService;
    @Autowired
    private ITbLoginService tbLoginService;
    /**
     * 修改用户信息
     */
    @PutMapping("/updateInfo")
    public AjaxResult updateInfo(@RequestBody TbUser user) {
        user.setUserId(StpUtil.getLoginIdAsLong());
        if (tbUserService.updateFlowerUser(user) > 0) {
            return success();
        }
        return error("修改用户信息异常，请联系管理员");
    }

    /**
     * 修改密码
     */
    @PutMapping("/password")
    public AjaxResult updatePassword(String oldPassword, String newPassword) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        SysUser user = loginUser.getUser();
        if (!SecurityUtils.matchesPassword(oldPassword, user.getPassword())) {
            return error("修改密码失败，旧密码错误");
        }
        TbUser updateUser = new TbUser();
        updateUser.setUserId(user.getUserId());
        updateUser.setPassword(SecurityUtils.encryptPassword(newPassword));
        if (tbUserService.updateFlowerUser(updateUser) > 0) {
            return success();
        }
        return error("修改密码异常，请联系管理员");
    }


    /**
     * 用户登录-微信一键登录
     *
     * @param callback 登录信息
     * @return 登录结果，成功返回token
     */
    @PostMapping("/wxMiniProgramLogin")
    public AjaxResult wxMiniProgramLogin(@RequestBody AuthCallback callback) {
        return tbLoginService.wxMiniProgramLogin(callback);
    }


    /**
     * 用户登录
     *
     * @param loginBody 登录信息（用户名、密码）
     * @return 登录结果，成功返回token
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody) {
        return tbLoginService.login(loginBody);
    }

    /**
     * 用户注册
     *
     * @param loginBody 注册信息（用户名、密码）
     * @return 注册结果
     */
    @PostMapping("/register")
    public AjaxResult register(@RequestBody LoginBody loginBody) {
        return tbLoginService.register(loginBody);
    }

    /**
     * 获取当前登录用户信息
     *
     * @return 用户信息（不含密码等敏感信息）
     */
    @GetMapping("/info")
    public AjaxResult getInfo() {
        return tbLoginService.getInfo();
    }

    /**
     * 退出登录
     *
     * @return 退出结果
     */
    @PostMapping("/logout")
    public AjaxResult logout() {
        return tbLoginService.logout();
    }

    /**
     * 头像上传
     */
    @PostMapping("/avatar")
    public AjaxResult avatar(@RequestParam("avatarfile") MultipartFile file) throws Exception
    {
        if (!file.isEmpty())
        {
            long loginIdAsLong = StpUtil.getLoginIdAsLong();
            TbUser tbUser = tbUserService.selectUserById(loginIdAsLong);
            String avatar = FileUploadUtils.upload(EmoticonConfig.getAvatarPath(), file, MimeTypeUtils.IMAGE_EXTENSION);
            tbUser.setAvatar(avatar);
            if (tbUserService.updateFlowerUser(tbUser) > 0)
            {
                AjaxResult ajax = AjaxResult.success();
                ajax.put("imgUrl", avatar);
                return ajax;
            }
        }
        return AjaxResult.error("上传图片异常，请联系管理员");
    }


}
